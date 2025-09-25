package world.xuewei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import world.xuewei.dto.RespResult;
import world.xuewei.entity.DocHistory;
import world.xuewei.entity.User;
import world.xuewei.service.DocHistoryService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/docHistory")
public class DocHistoryController {
    @Autowired
    private DocHistoryService docHistoryService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/my-consult")
    public RespResult getMyConsults(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null || user.getRoleStatus() != 2) {
            return RespResult.fail("无权限");
        }
        List<DocHistory> list = docHistoryService.findByDoctorId(user.getId());
        return RespResult.success("查询成功", list);
    }

    @PostMapping("/create")
    @ResponseBody
    public RespResult createConsult(@RequestParam Integer doctorId, @RequestParam String doctorName, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return RespResult.fail("请先登录");
        }

        // 1. 这里加唯一性校验
        int count = docHistoryService.countByUserIdAndDoctorIdAndStatus(user.getId(), doctorId, 0);
        if (count > 0) {
            // 已有未结束的会诊，不能再新建
            return RespResult.fail("已有未结束的会诊，不能重复发起！");
        }

        // 2. 没有未结束的会诊，才允许新建
        DocHistory history = new DocHistory();
        history.setUserId(user.getId());
        history.setUserName(user.getUserName());
        history.setDoctorId(doctorId);
        history.setDoctorName(doctorName);
        history.setAppointTime(new Date());
        history.setStatus(0); // 0=待问诊
        docHistoryService.save(history);
        return RespResult.success("创建成功", history);
    }

    @PostMapping("/endConsult")
    public RespResult endConsult(@RequestParam Integer doctorId, @RequestParam Integer userId) {
        // 查找对应的问诊记录，status=0
        List<DocHistory> list = docHistoryService.query(DocHistory.builder().doctorId(doctorId).userId(userId).status(0).build());
        if (list == null || list.isEmpty()) {
            return RespResult.fail("未找到待结束的问诊记录");
        }
        DocHistory history = list.get(0);
        UpdateWrapper<DocHistory> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", history.getId());
        DocHistory update = new DocHistory();
        update.setStatus(1);
        docHistoryService.update(update, wrapper);

        // 推送会诊结束消息
        Map<String, Object> endMsg = new HashMap<>();
        endMsg.put("type", "end");
        endMsg.put("content", "此次会诊已结束");
        endMsg.put("doctorId", doctorId);
        endMsg.put("userId", userId);
        messagingTemplate.convertAndSend("/topic/chat/" + doctorId + "_" + userId, endMsg);

        return RespResult.success("会诊已结束");
    }

    @PostMapping("/saveMedicalRecord")
    public RespResult saveMedicalRecord(@RequestParam Long id, @RequestParam String medicalRecord) {
        System.out.println("收到保存病历请求，id=" + id);
        DocHistory history = docHistoryService.get(id);
        if (history == null) {
            return RespResult.fail("未找到对应的会诊记录");
        }
        history.setMedicalRecord(medicalRecord);
        docHistoryService.updateById(history);
        return RespResult.success("保存成功");
    }
} 
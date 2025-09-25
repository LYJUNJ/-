package world.xuewei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import world.xuewei.entity.User;
import world.xuewei.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
public class UserManageController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin-info")
    public String adminInfo(Map<String, Object> map) {
        List<User> adminList = userService.query(User.builder().roleStatus(1).build());
        map.put("adminList", adminList);
        return "admin-info";
    }

    @GetMapping("/doctor-info")
    public String doctorInfo(Map<String, Object> map) {
        List<User> doctorList = userService.query(User.builder().roleStatus(2).build());
        map.put("doctorList", doctorList);
        return "doctor-info";
    }

    @GetMapping("/user-info")
    public String userInfo(Map<String, Object> map) {
        List<User> userList = userService.query(User.builder().roleStatus(0).build());
        map.put("userList", userList);
        return "user-info";
    }
}

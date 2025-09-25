package world.xuewei.service;

import world.xuewei.entity.DocHistory;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.extension.service.IService;

public interface DocHistoryService extends IService<DocHistory> {
    List<DocHistory> findByDoctorId(Integer doctorId);
    DocHistory get(Long id);
    List<DocHistory> getByDoctorId(Long doctorId);
    @Select("SELECT * FROM doc_history WHERE doctor_id = #{doctorId}")
    List<DocHistory> selectByDoctorId(Long doctorId);
    List<DocHistory> query(DocHistory condition);
    int countByUserIdAndDoctorIdAndStatus(Integer userId, Integer doctorId, Integer status);
    List<DocHistory> findByUserId(Integer userId);
}


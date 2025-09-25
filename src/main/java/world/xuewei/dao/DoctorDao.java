package world.xuewei.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import world.xuewei.entity.Doctor;
import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface DoctorDao extends BaseMapper<Doctor> {
    @Select("SELECT * FROM doctor")
    List<Doctor> findAll();
    Doctor findById(Integer id);
    // 如需 findByUserId
    Doctor findByUserId(Integer userId);
}
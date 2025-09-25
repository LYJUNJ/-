package world.xuewei.dao;

import world.xuewei.entity.DoctorSchedule;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface DoctorScheduleDao {
    List<DoctorSchedule> findAll();
    @Select("SELECT ds.*, d.name AS doctorName, dep.name AS departmentName " +
            "FROM doctor_schedule ds " +
            "LEFT JOIN doctor d ON ds.doctor_id = d.id " +
            "LEFT JOIN department dep ON ds.department_id = dep.id " +
            "WHERE (#{keyword} IS NULL OR d.name LIKE CONCAT('%', #{keyword}, '%') OR dep.name LIKE CONCAT('%', #{keyword}, '%')) " +
            "ORDER BY ds.id DESC LIMIT #{offset}, #{limit}")
    List<DoctorSchedule> findByPage(@Param("offset") int offset, @Param("limit") int limit, @Param("keyword") String keyword);

    @Select("SELECT COUNT(*) " +
            "FROM doctor_schedule ds " +
            "LEFT JOIN doctor d ON ds.doctor_id = d.id " +
            "LEFT JOIN department dep ON ds.department_id = dep.id " +
            "WHERE (#{keyword} IS NULL OR d.name LIKE CONCAT('%', #{keyword}, '%') OR dep.name LIKE CONCAT('%', #{keyword}, '%'))")
    int count(@Param("keyword") String keyword);
    @Insert("INSERT INTO doctor_schedule (doctor_id, department_id, visit_count, week_day) " +
            "VALUES (#{doctorId}, #{departmentId}, #{visitCount}, #{weekDay})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DoctorSchedule schedule);
    int update(DoctorSchedule schedule);
    int deleteById(Integer id);
    int batchDelete(List<Integer> ids);
    DoctorSchedule findById(Integer id);
}

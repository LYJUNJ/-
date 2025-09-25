package world.xuewei.service;

import world.xuewei.entity.DoctorSchedule;
import java.util.List;

public interface DoctorScheduleService {
    List<DoctorSchedule> findByPage(int page, int size, String keyword);
    int count(String keyword);
    int insert(DoctorSchedule schedule);
    int update(DoctorSchedule schedule);
    int deleteById(Integer id);
    int batchDelete(List<Integer> ids);
    DoctorSchedule findById(Integer id);
}

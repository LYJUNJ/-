package world.xuewei.service.impl;

import world.xuewei.dao.DoctorScheduleDao;
import world.xuewei.entity.DoctorSchedule;
import world.xuewei.service.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    @Autowired
    private DoctorScheduleDao doctorScheduleDao;

    @Override
    public List<DoctorSchedule> findByPage(int page, int size, String keyword) {
        int offset = (page - 1) * size;
        return doctorScheduleDao.findByPage(offset, size, keyword);
    }

    @Override
    public int count(String keyword) {
        return doctorScheduleDao.count(keyword);
    }

    @Override
    public int insert(DoctorSchedule schedule) {
        return doctorScheduleDao.insert(schedule);
    }

    @Override
    public int update(DoctorSchedule schedule) {
        return doctorScheduleDao.update(schedule);
    }

    @Override
    public int deleteById(Integer id) {
        return doctorScheduleDao.deleteById(id);
    }

    @Override
    public int batchDelete(List<Integer> ids) {
        return doctorScheduleDao.batchDelete(ids);
    }

    @Override
    public DoctorSchedule findById(Integer id) {
        return doctorScheduleDao.findById(id);
    }
}

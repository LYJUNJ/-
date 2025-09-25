package world.xuewei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.xuewei.dao.DoctorDao;
import world.xuewei.entity.Doctor;
import world.xuewei.service.DoctorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorDao doctorDao;

    @Override
    public List<Doctor> all() {
        return doctorDao.selectList(null);
    }

    @Override
    public Doctor get(Integer id) {
        return doctorDao.selectById(id);
    }

    @Override
    public Doctor save(Doctor doctor) {
        if (doctor.getId() == null) {
            doctorDao.insert(doctor);
        } else {
            doctorDao.updateById(doctor);
        }
        return doctor;
    }

    @Override
    public boolean delete(Integer id) {
        return doctorDao.deleteById(id) > 0;
    }

    @Override
    public List<Doctor> findByDepartment(Integer departmentId) {
        return doctorDao.selectList(new QueryWrapper<Doctor>().eq("department_id", departmentId));
    }

    @Override
    public Doctor findByUserId(Integer id) {
        return doctorDao.findByUserId(id);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorDao.findAll();
    }

    @Override
    public Doctor findById(Integer id) {
        return doctorDao.findById(id);
    }
}
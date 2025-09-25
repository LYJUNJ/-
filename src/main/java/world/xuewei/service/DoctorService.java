package world.xuewei.service;

import world.xuewei.entity.Doctor;
import java.util.List;

public interface DoctorService {
    List<Doctor> all();
    Doctor get(Integer id);
    Doctor save(Doctor doctor);
    boolean delete(Integer id);
    List<Doctor> findByDepartment(Integer departmentId);

    Doctor findByUserId(Integer id);
    List<Doctor> findAll();
    Doctor findById(Integer id);
}
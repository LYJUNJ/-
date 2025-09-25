package world.xuewei.service;

import world.xuewei.entity.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> all();
    Department get(Integer id);
    Department save(Department department);
    boolean delete(Integer id);
    List<Department> findAll();
    Department findById(Integer id);
}
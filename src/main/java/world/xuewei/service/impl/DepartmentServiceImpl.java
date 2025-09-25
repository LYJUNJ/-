package world.xuewei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.xuewei.dao.DepartmentDao;
import world.xuewei.entity.Department;
import world.xuewei.service.DepartmentService;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<Department> all() {
        return departmentDao.selectList(null);
    }

    @Override
    public Department get(Integer id) {
        return departmentDao.selectById(id);
    }

    @Override
    public Department save(Department department) {
        if (department.getId() == null) {
            departmentDao.insert(department);
        } else {
            departmentDao.updateById(department);
        }
        return department;
    }

    @Override
    public boolean delete(Integer id) {
        return departmentDao.deleteById(id) > 0;
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public Department findById(Integer id) {
        return departmentDao.findById(id);
    }
}
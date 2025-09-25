package world.xuewei.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import world.xuewei.entity.Department;
import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface DepartmentDao extends BaseMapper<Department> {
    @Select("SELECT * FROM department")
    List<Department> findAll();
    Department findById(Integer id);
}
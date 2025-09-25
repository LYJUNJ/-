 package world.xuewei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.xuewei.entity.Department;
import world.xuewei.service.DepartmentService;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public List<Department> all() {
        return departmentService.all();
    }

    @GetMapping("/{id}")
    public Department get(@PathVariable Integer id) {
        return departmentService.get(id);
    }

    @PostMapping("/save")
    public Department save(@RequestBody Department department) {
        return departmentService.save(department);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return departmentService.delete(id);
    }
}
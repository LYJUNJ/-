package world.xuewei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.xuewei.dto.RespResult;
import world.xuewei.entity.Doctor;
import world.xuewei.service.DoctorService;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/all")
    public List<Doctor> all() {
        return doctorService.all();
    }

    @GetMapping("/{id}")
    public Doctor get(@PathVariable Integer id) {
        return doctorService.get(id);
    }

    @PostMapping("/save")
    public RespResult save(@RequestBody Doctor doctor) {
        Doctor saved = doctorService.save(doctor);
        return RespResult.success("保存成功", saved);
    }

    @DeleteMapping("/delete/{id}")
    public RespResult delete(@PathVariable Integer id) {
        boolean result = doctorService.delete(id);
        return result ? RespResult.success("删除成功") : RespResult.fail("删除失败");
    }

    @GetMapping("/byDepartment")
    public List<Doctor> byDepartment(@RequestParam Integer departmentId) {
        return doctorService.findByDepartment(departmentId);
    }
}
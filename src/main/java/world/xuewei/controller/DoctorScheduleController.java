package world.xuewei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import world.xuewei.entity.DoctorSchedule;
import world.xuewei.service.DoctorScheduleService;
import world.xuewei.service.DoctorService;
import world.xuewei.service.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DoctorScheduleController {

    @Autowired
    private DoctorScheduleService doctorScheduleService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all-schedule")
    public String allSchedule(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            Model model) {
        List<DoctorSchedule> schedules = doctorScheduleService.findByPage(page, size, keyword);
        int total = doctorScheduleService.count(keyword);
        model.addAttribute("schedules", schedules);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);
        model.addAttribute("doctors", doctorService.findAll());
        return "all-schedule";
    }

    @GetMapping("/add-schedule")
    public String addSchedulePage(Model model) {
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "add-schedule";
    }

    @PostMapping("/add-schedule")
    public String addSchedule(DoctorSchedule schedule) {
        doctorScheduleService.insert(schedule);
        return "redirect:/all-schedule";
    }

    @GetMapping("/edit-schedule/{id}")
    public String editSchedulePage(@PathVariable Integer id, Model model) {
        DoctorSchedule schedule = doctorScheduleService.findById(id);
        model.addAttribute("schedule", schedule);
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "add-schedule";
    }

    @PostMapping("/edit-schedule")
    public String editSchedule(DoctorSchedule schedule) {
        doctorScheduleService.update(schedule);
        return "redirect:/all-schedule";
    }

    @PostMapping("/delete-schedule")
    @ResponseBody
    public String deleteSchedule(@RequestParam Integer id) {
        doctorScheduleService.deleteById(id);
        return "success";
    }

    @PostMapping("/batch-delete-schedule")
    @ResponseBody
    public String batchDeleteSchedule(@RequestParam List<Integer> ids) {
        doctorScheduleService.batchDelete(ids);
        return "success";
    }
}

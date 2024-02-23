package tech.xuedong.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.xuedong.Utils.R;
import tech.xuedong.pojo.Schedule;
import tech.xuedong.service.ScheduleService;

@CrossOrigin //允许跨域访问
@RestController
@RequestMapping("/schedule")
@Slf4j
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{pageSize}/{currentPage}")
    public R page(@PathVariable int pageSize, @PathVariable int currentPage) {
        R r = scheduleService.page(pageSize, currentPage);
        log.info("当前页数据：{}", r);
        return r;
    }

    @DeleteMapping("/{id}")
    public R remove(@PathVariable int id) {
        R r = scheduleService.remove(id);
        return r;
    }

    @PostMapping
    public R save(@RequestBody @Validated Schedule schedule, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail("参数不合法");
        }
        R r = scheduleService.save(schedule);
        return r;
    }

    @PutMapping
    public R update(@RequestBody @Validated Schedule schedule, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail("参数不合法");
        }
        R r = scheduleService.update(schedule);
        return r;
    }
}

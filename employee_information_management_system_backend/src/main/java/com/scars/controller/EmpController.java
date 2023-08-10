package com.scars.controller;

import com.scars.anno.Log;
import com.scars.pojo.Emp;
import com.scars.pojo.PageBean;
import com.scars.pojo.Result;
import com.scars.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//        if (page == null)
//            page = 1;
//        if (pageSize == null)
//            page = 10;

        log.info("分页查询，参数：{}，{}，{}，{}，{}，{}", page, pageSize, name, gender, begin, end);

        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);

        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量输出员工，id为：{}", ids);

        empService.delete(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增了员工：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getByID(@PathVariable Integer id) {
        log.info("查询到ID为{}的员工信息", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新的员工信息：{}", emp);
        empService.update(emp);
        return Result.success();
    }
}

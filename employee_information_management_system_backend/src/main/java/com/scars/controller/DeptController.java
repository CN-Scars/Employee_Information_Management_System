package com.scars.controller;

import com.scars.anno.Log;
import com.scars.pojo.Dept;
import com.scars.pojo.Result;
import com.scars.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/depts")   // 抽取公共路径
public class DeptController {

    @Autowired
    private DeptService deptServce;


//    private static Logger log = LoggerFactory.getLogger(DeptController.class);

    // 指定请求方式为GET
//    @RequestMapping(value = "/depts",method = RequestMethod.GET);
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");

        List<Dept> deptList = deptServce.list();

        return Result.success(deptList);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询到的部门ID为{}", id);

        Dept dept = deptServce.getById(id);
        return Result.success(dept);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result delete (@PathVariable Integer id) throws Exception {
        log.info("已删除id为{}的部门", id);

        deptServce.delete(id);

        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增了部门{}", dept.getName());

        deptServce.add(dept);
        return Result.success();
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改了部门{}", dept);

        deptServce.update(dept);
        return Result.success();
    }
}

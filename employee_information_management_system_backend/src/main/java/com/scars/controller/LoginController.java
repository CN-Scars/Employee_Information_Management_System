package com.scars.controller;

import com.scars.pojo.Emp;
import com.scars.pojo.Result;
import com.scars.service.EmpService;
import com.scars.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;


    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工进行了登录，账号：{}，密码：{}", emp.getUsername(), emp.getPassword());

        Emp e = empService.login(emp);

        // 登录成功，生成令牌，下发令牌
        if (e != null)
        {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());

            String jwt = JwtUtils.generateJwt(claims);  // 令牌包含了当前登录的员工信息
            return Result.success(jwt);
        }
        // 登录失败，返回错误信息
        return Result.error("用户名或密码错误！");
    }
}

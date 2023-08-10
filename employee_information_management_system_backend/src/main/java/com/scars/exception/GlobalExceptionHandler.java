package com.scars.exception;

import com.scars.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {   // 全局异常处理器
    @ExceptionHandler(Exception.class)
    private Result ex(Exception ex) {
        ex.printStackTrace();
        return Result.error("操作失败，请联系系统管理员！");
    }
}

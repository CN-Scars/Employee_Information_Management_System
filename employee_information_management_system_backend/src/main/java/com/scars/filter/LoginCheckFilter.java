package com.scars.filter;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.scars.pojo.Result;
import com.scars.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取请求url
        String url = request.getRequestURI().toString();
        log.info("请求URL为：{}", url);

        if (url.contains("login")) {
            log.info(("登录操作，直接放行"));
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 获取令牌
        String jwt = request.getHeader("token");

        // 检查令牌是否存在
        if (!StringUtils.hasLength(jwt))
        {
            log.info("token为空");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);   // 把未登录的信息响应给前端
            return;
        }

        // 校验令牌
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("登录失败");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);   // 把未登录的信息响应给前端
            throw new RuntimeException(e);
        }

        // 放行
        log.info("令牌存在且合法，放行");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}

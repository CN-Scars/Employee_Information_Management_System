package com.scars.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.scars.pojo.Result;
import com.scars.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    // 目标资源方法运行前运行，true放行，false拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求url
        String url = request.getRequestURI().toString();
        log.info("请求URL为：{}", url);

        if (url.contains("login")) {
            log.info(("登录操作，直接放行"));
            return true;
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
            return false;
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

        return true;
    }

    // 目标资源方法运行后运行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    // 视图渲染完毕后运行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

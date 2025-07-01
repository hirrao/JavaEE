package com.hirrao.javaee.interceptors;

import com.hirrao.javaee.entity.User;
import com.hirrao.javaee.service.UserService;
import com.hirrao.javaee.utils.Jwt;
import com.hirrao.javaee.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


//拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoginInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        System.out.println(request.getHeader("Authorization"));
        //获取请求头中的token
        String token = request.getHeader("Authorization");
        logger.debug("token={}", token);
        //先验证token
        try {
            String Uid = Jwt.parseToken(token);
            System.out.println(Uid);
            User user = userService.findByUid(Long.parseLong(Uid));
            ThreadLocalUtil.set(user);
            return true;
        } catch (Exception e) {
            //响应状态码为401
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //清除ThreadLocal中的用户信息
        ThreadLocalUtil.remove();
    }
}
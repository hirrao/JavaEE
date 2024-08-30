package cn.hirrao.javaee.interceptors;

import cn.hirrao.javaee.utils.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


//拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取请求头中的token
        String token = request.getHeader("Authorization");

        //先验证token
        try {
            String Uid = Jwt.parseToken(token);
            return true;
        } catch (Exception e) {
            //响应状态码为401
            response.setStatus(401);
            return false;
        }
    }
}
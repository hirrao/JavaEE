package cn.hirrao.javaee.interceptors;

import cn.hirrao.javaee.entity.User;
import cn.hirrao.javaee.service.UserService;
import cn.hirrao.javaee.utils.Jwt;
import cn.hirrao.javaee.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


//拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final UserService userService;

    @Autowired
    private LoginInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        //获取请求头中的token
        String token = request.getHeader("Authorization");

        //先验证token
        try {
            String Uid = Jwt.parseToken(token);
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
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) {
        //清除ThreadLocal中的用户信息
        ThreadLocalUtil.remove();
    }
}
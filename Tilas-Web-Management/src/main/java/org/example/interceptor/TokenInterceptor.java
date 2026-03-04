package org.example.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.CurrentHolder;
import org.example.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 校验JWT令牌：从请求头中获取JWT令牌，若为空或不合理，则返回错误401
        String token = request.getHeader("token");
        if(token == null || token.isEmpty()){
            log.info("请求头中无JWT令牌，返回401");
            response.setStatus(401); // 401也可以写作HttpServletResponse.SC_UNAUTHORIZED
            return false;
        }
        try{
            // 解析JWT令牌，并通过ThreadLocal保存起来，将员工ID传递给AOP程序
            Claims claims = JwtUtils.parseJwt(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前用户ID为：{}", empId);

        }catch (Exception e){
            log.info("JWT令牌解析失败，返回401");
            response.setStatus(401);
            return false;
        }

        // 校验通过，放行
        log.info("JWT令牌校验通过，放行");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 删除当前用户ID，避免内存泄漏
        CurrentHolder.remove();
    }
}

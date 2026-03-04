package org.example.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.CurrentHolder;
import org.example.utils.JwtUtils;

import java.io.IOException;

// @WebFilter("/*")
@Slf4j
public class TokenFilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 准备工作：因为这里处理的一定是HTTP请求，所以将ServletRequest和ServletResponse强转为HttpServletRequest和HttpServletResponse方便处理
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 从请求中获取请求路径，以此判断是否是登录请求
        // 若是登录请求，则放行，否则校验JWT令牌
        String requestURI = request.getRequestURI();
        if(requestURI.startsWith("/login")){
            log.info("登录请求：{}，正常放行", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // 校验JWT令牌：从请求头中获取JWT令牌，若为空或不合理，则返回错误401
        String token = request.getHeader("token");
        if(token == null || token.isEmpty()){
            log.info("请求头中无JWT令牌，返回401");
            response.setStatus(401); // 401也可以写作HttpServletResponse.SC_UNAUTHORIZED
            return;
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
            return;
        }

        // 校验通过，放行
        log.info("JWT令牌校验通过，放行");
        filterChain.doFilter(request, response);

        // 删除当前用户ID，避免内存泄漏
        CurrentHolder.remove();
    }
}

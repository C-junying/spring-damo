package jmu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object ID = request.getSession().getAttribute("userID");
        Object permission = request.getSession().getAttribute("permission");
        if(ID == null || permission.toString() == "1"){
            //说明用户没有登录过系统，则重定向到ogin.html页面
            response.sendRedirect("/user/login");
            //结束后续调用
            return false;
        }
        //请求放行
        return true;
    }
}

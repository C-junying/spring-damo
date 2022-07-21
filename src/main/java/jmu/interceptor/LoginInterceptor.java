package jmu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义一个登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    //在调用所有处理请求的方法之前被自动调用执行的方法

    /**
     * 检测全局session对象中是否有userID数据，如果有则放行，如果没有重定向到登录页面。
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器(url+Controller:映射)
     * @return 如果返回值为trve表示放行当前的请求，如果返回值alse则表示拦截当前的请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object object = request.getSession().getAttribute("userID");
        if(object == null){
            //说明用户没有登录过系统，则重定向到ogin.html页面
            response.sendRedirect("/user/login");
            //结束后续调用
            return false;
        }
        //请求放行
        return true;
    }
    //在Mode1AndView对象返回之后被调用的方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    //在整个请求所有关联的资源被执行完毕最后所执行的方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

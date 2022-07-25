package jmu.config;

import jmu.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册过滤器
 * 添加白名单(哪些资源可以在不登录的情况下访问)
 * 添加黑名单(在用户登录的状态才可以访问)
 */
//处理器拦截器的注册
//@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    //白名单添加
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义的拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        //配置白名单:存放在一个List集合
        List<String> patterns = new ArrayList<>();
        //静态资源
        patterns.add("/component/**");
        patterns.add("/css/**");
        patterns.add("/img/**");
        patterns.add("/js/**");

        //页面
        patterns.add("/user/login");
        patterns.add("/user/register");
        patterns.add("/user/home");
        registry.addInterceptor(interceptor)
                .excludePathPatterns(patterns);
    }
}

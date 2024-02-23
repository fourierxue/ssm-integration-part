package tech.xuedong.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 控制层配置类
 * 1、controller
 * 2、全局异常处理
 * 3、handlermapping handlerAdapter
 * 4、静态资源处理
 * 5、jsp 视图解析器的前后缀
 * 6、json解析器
 * 7、拦截器。。。
 */
@Configuration
@ComponentScan({"tech.xuedong.controller", "tech.xuedong.exceptionhandler"})
@EnableWebMvc  // handlerMapping handlerAdapter json解析器
public class WebMvcConfig implements WebMvcConfigurer {
    // 静态资源处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    // 拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new XX()).addPathPatterns().excludePathPatterns()
    }
}

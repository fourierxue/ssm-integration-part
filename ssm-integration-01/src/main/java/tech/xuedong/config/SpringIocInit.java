package tech.xuedong.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.sql.DataSource;

public class SpringIocInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    // root ioc容器的配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{DataSourceJavaConfig.class, MapperJavaConfigNew.class, ServiceJavaConfig.class};
    }

    //web ioc容器的配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class};
    }

    // dispatcherServlet拦截路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

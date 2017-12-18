package com.lakala.mini.config;

import com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Description: servlet configuration
 * User: xiao_dingo
 * Date: 2017-12-14
 * Time: 下午3:10
 */
@Configuration
public class ServletConfig {

    /**
     * register servlet for wsdl
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean restServlet() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/*");
        registrationBean.setName("dubbo");
        return registrationBean;
    }
}

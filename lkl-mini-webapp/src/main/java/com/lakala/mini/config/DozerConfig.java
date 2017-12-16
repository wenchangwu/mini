package com.lakala.mini.config;

import com.lakala.core.factory.ApplicationContextFactory;
import com.lakala.core.objmapper.dozer.ObjectMapperDozerImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;
import java.util.List;


/**
 * Description: dozer configuration
 * User: xiao_dingo
 * Date: 2017-12-14
 * Time: 下午3:10
 */

@Configuration
public class DozerConfig {


    @Value("${ctx.systemCode}")
    private String systemCode;

    /**
     * @return
     */
    @Bean(name = "mapper")
    @Scope("singleton")
    public ObjectMapperDozerImpl objectMapperDozerImpl() {
        List<String> mappingFiles = Arrays.asList(
                "classpath:META-INF/mini/server/core/dtomap/*.xml"
        );
        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(mappingFiles);
        ObjectMapperDozerImpl objectMapperDozer = new ObjectMapperDozerImpl();
        objectMapperDozer.setMapper(dozerBean);
        return objectMapperDozer;
    }

    /**
     * @return
     */
    @Bean(name = "applicationContextFactory")
    public ApplicationContextFactory applicationContextFactory() {
        ApplicationContextFactory applicationContextFactory = new ApplicationContextFactory();
        applicationContextFactory.setSystemCode(systemCode);
        return applicationContextFactory;
    }

    /**
     * @return
     */
    @Bean(name = "jsonObjectMapper")
    @Scope("singleton")
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

}

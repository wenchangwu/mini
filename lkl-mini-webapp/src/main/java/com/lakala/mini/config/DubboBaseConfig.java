package com.lakala.mini.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.rpc.Exporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Description: dubbo base configuration
 * User: xiao_dingo
 * Date: 2017-12-14
 * Time: 下午3:10
 */

public class DubboBaseConfig {

    @Value("${dubbo.registry.address}")
    private String address;
    @Value("${dubbo.application.name}")
    private String name;
    @Value("${dubbo.application.owner}")
    private String owner;
    @Value("${dubbo.application.organization}")
    private String organization;
    @Value("${dubbo.service.port}")
    private int port;
    @Value("${dobbo.container.serviceContext}")
    private String contextpath;


    /**
     * application config
     *
     * @return
     */
    @Bean
    public ApplicationConfig application() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(name);
        applicationConfig.setOwner(owner);
        applicationConfig.setOrganization(organization);
        return applicationConfig;
    }

    /**
     * registry config
     *
     * @return
     */
    @Bean
    public RegistryConfig registry() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(address);
        registryConfig.setProtocol("zookeeper");
        return registryConfig;
    }

    /**
     * protocol config
     *
     * @return
     */
    @Bean
    public ProtocolConfig protocol() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(port);
        protocolConfig.setServer("servlet");
        protocolConfig.setName("webservice");
        protocolConfig.setContextpath(contextpath);
        return protocolConfig;
    }



}

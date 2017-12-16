package com.lakala.mini.config;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.lakala.mini.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: dubbo export config
 * User: xiao_dingo
 * Date: 2017-12-14
 * Time: 下午3:10
 */
@Configuration
public class DubboExportConfig extends DubboBaseConfig {


    /**
     * export IEnquireService by dubbo
     *
     * @param enquireService
     * @return
     */

    @Bean
    public ServiceBean enquireServiceBean(IEnquireService enquireService) {
        ServiceBean<IEnquireService> serviceBean = new ServiceBean();
        serviceBean.setRef(enquireService);
        serviceBean.setInterface(IEnquireService.class);
        return serviceBean;
    }


    /**
     * export IEWalletService by dubbo
     *
     * @param eWalletService
     * @return
     */

    @Bean
    public ServiceBean eWalletServiceBean(IEWalletService eWalletService) {
        ServiceBean<IEWalletService> serviceBean = new ServiceBean();
        serviceBean.setRef(eWalletService);
        serviceBean.setInterface(IEWalletService.class);
        return serviceBean;
    }

    /**
     * export IMiniService by dubbo
     *
     * @param miniService
     * @return
     */

    @Bean
    public ServiceBean miniServiceBean(IMiniService miniService) {
        ServiceBean<IMiniService> serviceBean = new ServiceBean();
        serviceBean.setRef(miniService);
        serviceBean.setInterface(IMiniService.class);
        return serviceBean;
    }

    /**
     * export IThridPartyService by dubbo
     *
     * @param thridPartyService
     * @return
     */
    @Bean
    public ServiceBean thridPartyBean(IThridPartyService thridPartyService) {
        ServiceBean<IThridPartyService> serviceBean = new ServiceBean();
        serviceBean.setRef(thridPartyService);
        serviceBean.setInterface(IThridPartyService.class);
        return serviceBean;
    }


    /**
     * export IUDService by dubbo
     *
     * @param uDService
     * @return
     */
    @Bean
    public ServiceBean uDserBean(IUDService uDService) {
        ServiceBean<IUDService> serviceBean = new ServiceBean();
        serviceBean.setRef(uDService);
        serviceBean.setInterface(IUDService.class);
        return serviceBean;
    }

}

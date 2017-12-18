package com.lakala.mini.config;

import com.lakala.mini.server.core.manager.ICardManager;
import com.lakala.mini.server.core.manager.ICardOrganizationManager;
import com.lakala.mini.server.core.manager.IMiniUserManager;
import com.lakala.mini.server.core.manager.IPsamInfoManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Description: export bean by java base configuration
 * User: xiao_dingo
 * Date: 2017-12-18
 * Time: 下午2:29
 */
@Configuration
public class BeanConfig {


    /**
     * export rmi service: ICardManager
     *
     * @param cardManager
     * @param registryPort
     * @return
     */
    @Bean
    public RmiServiceExporter cardManagerServiceExporter(ICardManager cardManager, @Value("${rmi.registryPort}") int registryPort) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setService(cardManager);
        rmiServiceExporter.setServiceName("CardManager");
        rmiServiceExporter.setRegistryPort(registryPort);
        rmiServiceExporter.setServiceInterface(ICardManager.class);
        return rmiServiceExporter;
    }


    /**
     * export rmi service: ICardOrganizationManager
     *
     * @param cardOrganizationManager
     * @param registryPort
     * @return
     */
    @Bean
    public RmiServiceExporter cardOrganizationManagerExport(ICardOrganizationManager cardOrganizationManager, @Value("${rmi.registryPort}") int registryPort) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setService(cardOrganizationManager);
        rmiServiceExporter.setServiceName("CardOrganizationManager");
        rmiServiceExporter.setRegistryPort(registryPort);
        rmiServiceExporter.setServiceInterface(ICardOrganizationManager.class);
        return rmiServiceExporter;
    }


    /**
     * export rmi service: IPsamInfoManager
     *
     * @param psamInfoManager
     * @param registryPort
     * @return
     */
    @Bean
    public RmiServiceExporter psamInfoManagerExport(IPsamInfoManager psamInfoManager, @Value("${rmi.registryPort}") int registryPort) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setService(psamInfoManager);
        rmiServiceExporter.setServiceName("PsamInfoManager");
        rmiServiceExporter.setRegistryPort(registryPort);
        rmiServiceExporter.setServiceInterface(IPsamInfoManager.class);
        return rmiServiceExporter;
    }


    /**
     * export rmi service: IMiniUserManager
     *
     * @param miniUserManager
     * @param registryPort
     * @return
     */
    @Bean
    public RmiServiceExporter miniUserManagerExport(IMiniUserManager miniUserManager, @Value("${rmi.registryPort}") int registryPort) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setService(miniUserManager);
        rmiServiceExporter.setServiceName("MiniUserManager");
        rmiServiceExporter.setRegistryPort(registryPort);
        rmiServiceExporter.setServiceInterface(IMiniUserManager.class);
        return rmiServiceExporter;
    }


}

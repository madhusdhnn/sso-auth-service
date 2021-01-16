package com.thetechmaddy.authservice.configs;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InternalAuthServerConfig {

    private final Integer internalServerPort;

    public InternalAuthServerConfig(@Value("${internal.server.port}") Integer internalServerPort) {
        this.internalServerPort = internalServerPort;
    }

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        TomcatServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
        Connector internalServerConnector = createAndGetInternalServerConnector();
        webServerFactory.addAdditionalTomcatConnectors(internalServerConnector);
        return webServerFactory;
    }

    private Connector createAndGetInternalServerConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(this.internalServerPort);
        return connector;
    }
}

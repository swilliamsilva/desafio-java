package com.kipho.AppRestauranteDJ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        logger.debug("Iniciando o aplicativo...");
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        logger.debug("Aplicativo iniciado com sucesso.");

        // Abrir a página index.html no navegador padrão
        String url = "http://localhost:8081/index";
        logger.debug("Abrindo a página index.html no navegador padrão: " + url);
        try {
            // Verificar se o ambiente suporta operações de desktop
            if (!java.awt.GraphicsEnvironment.isHeadless()) {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            } else {
                logger.warn("O ambiente não suporta operações de desktop. Não é possível abrir o navegador.");
            }
        } catch (java.io.IOException e) {
            logger.error("Erro ao abrir a página no navegador", e);
        }
    }
}



package com.kipho.AppRestauranteDJ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        logger.debug("Iniciando o aplicativo...");

        // Certifique-se de que o aplicativo está configurado para se conectar ao banco de dados
        SpringApplication.run(DemoApplication.class, args);

        logger.debug("Aplicativo iniciado com sucesso.");
    }
}


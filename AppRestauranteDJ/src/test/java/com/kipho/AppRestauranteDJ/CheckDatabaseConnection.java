package com.kipho.AppRestauranteDJ;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckDatabaseConnection {
    private static final Logger logger = LoggerFactory.getLogger(CheckDatabaseConnection.class);

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Carrega o arquivo application.yml
            Yaml yaml = new Yaml();
            InputStream inputStream = new FileInputStream("src/main/resources/templates/application.yml");
            Map<String, Object> yamlData = yaml.load(inputStream);

            // Obter configurações do banco de dados do YAML
            Map<String, Object> springConfig = (Map<String, Object>) yamlData.get("spring");
            Map<String, Object> datasourceConfig = (Map<String, Object>) springConfig.get("datasource");

            String url = (String) datasourceConfig.get("url");
            String username = (String) datasourceConfig.get("username");
            String password = (String) datasourceConfig.get("password");

            // Conectar ao banco de dados PostgreSQL
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();

            // Executa uma consulta para verificar a versão do PostgreSQL
            rs = stmt.executeQuery("SELECT version();");

            if (rs.next()) {
                logger.info("Versão do PostgreSQL: " + rs.getString(1));
            }

        } catch (java.io.FileNotFoundException fnfe) {
            logger.error("Arquivo 'application.yml' não encontrado. Certifique-se de que ele está no diretório 'src/main/resources'.", fnfe);
        } catch (SQLException sqle) {
            logger.error("Falha ao conectar ao banco de dados. Verifique as configurações no arquivo 'application.yml'.", sqle);
        } catch (Exception e) {
            logger.error("Erro desconhecido: " + e.getMessage(), e);
        } finally {
            // Fechamento seguro de recursos para evitar vazamentos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqle) {
                logger.error("Erro ao fechar recursos do banco de dados.", sqle);
            }
        }
    }
}

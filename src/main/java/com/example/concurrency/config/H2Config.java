package com.example.concurrency.config;

import java.sql.DatabaseMetaData;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class H2Config {

    private static final String HOST_NAME = "localhost:8080";
    private static final String LOG_FORMAT = """
        :: Loading Database With InMemory Database ::
            Database Name: {}
            Connect Database with this url: {}
            URL for access to Database: {}
        """;

    @Bean
    ApplicationRunner applicationRunner(final DataSource dataSource,
        final H2ConsoleProperties h2ConsoleProperties) {
        return args -> {
            DatabaseMetaData metaData = dataSource.getConnection().getMetaData();

            log.info(LOG_FORMAT,
                metaData.getDatabaseProductName(),
                metaData.getURL(),
                HOST_NAME + h2ConsoleProperties.getPath());
        };
    }
}
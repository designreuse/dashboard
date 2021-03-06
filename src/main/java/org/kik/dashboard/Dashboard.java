package org.kik.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Dashboard {

    private static final Logger LOGGER = LoggerFactory.getLogger(Dashboard.class);

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Dashboard.class);

        if (args != null && args.length == 1 && "init".equals(args[0])) {
            LOGGER.info("Add 'initDatabase' profile");
            app.setAdditionalProfiles("initDatabase");
        }

        app.run(args);

    }

}
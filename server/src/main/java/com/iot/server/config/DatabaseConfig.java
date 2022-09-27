package com.iot.server.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Rishikesh
 * @project IoT
 */
@Configuration
public class DatabaseConfig {
    @Value("${db.url}")
    public String databaseURL;
    @Value("${db.userName}")
    public String userName;
    @Value("${db.password}")
    private String password;

    @Bean
    public InfluxDBClient connection() {
        return InfluxDBClientFactory.create(databaseURL, userName, password.toCharArray());
    }

}

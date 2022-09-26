package com.iot.server.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Rishikesh
 * @project IoT
 */
@Configuration
public class DatabaseConfig {
    @Value("${db.databaseURL}")
    public String databaseURL;
    @Value("${db.userName}")
    public String userName;
    @Value("${db.password")
    private String password;
    @Bean
    public InfluxDB connection(){
        return InfluxDBFactory.connect(databaseURL, userName, password);
    }

}

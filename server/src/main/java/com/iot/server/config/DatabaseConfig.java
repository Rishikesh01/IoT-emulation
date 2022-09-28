package com.iot.server.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.domain.Bucket;
import com.influxdb.client.domain.BucketRetentionRules;
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

    @Value("${db.token}")
    public String token;

    @Bean
    public InfluxDBClient connection() {
        return InfluxDBClientFactory.create(databaseURL, token.toCharArray());
    }

}

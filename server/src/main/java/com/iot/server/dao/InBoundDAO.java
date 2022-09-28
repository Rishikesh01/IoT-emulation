package com.iot.server.dao;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.iot.server.model.Xenergy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rishikesh
 * @project IoT
 */
@Repository
public class InBoundDAO {
    public final InfluxDBClient client;

    public InBoundDAO(InfluxDBClient client) {
        this.client = client;
    }

    public void save(Xenergy xenergy) {
        try (WriteApi writeApi = client.makeWriteApi()) {
            writeApi.writeMeasurement(WritePrecision.NS, xenergy);
        }
        client.close();
    }

    public List<Xenergy> realTimeData() {
        String query = "from(bucket:\"iot-bucker\")|> range(start:1h)";
        QueryApi queryApi = client.getQueryApi();
        List<Xenergy> xenergies = queryApi.query(query, Xenergy.class);
        client.close();
        return xenergies;
    }
}

package com.iot.server.model;


import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.Instant;

/**
 * @author Rishikesh
 * @project IoT
 */
@Data
@Measurement(name = "xenergy")
public class Xenergy {
    @Column(timestamp = true)
    private Instant time;
    @Column(name = "vid")
    private String vid;
    @Column(name = "dataVia")
    private String dataVia;
    @Column(name = "tdata")
    private String tdata;
    @Column(name = "created")
    private SimpleDateFormat created;
}

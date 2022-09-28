package com.iot.client.dto;

import lombok.Data;

import java.text.SimpleDateFormat;

/**
 * @author Rishikesh
 * @project IoT
 */
@Data
public class Records {
    private String vid;
    private String dataVia;
    private String tdata;
    private SimpleDateFormat created;
}

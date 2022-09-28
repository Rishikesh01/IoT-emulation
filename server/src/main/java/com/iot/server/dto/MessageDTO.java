package com.iot.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.SimpleDateFormat;

/**
 * @author Rishikesh
 * @project IoT
 */
@Getter
@AllArgsConstructor
public class MessageDTO {
    private String vid;
    private String dataVia;
    private String tdata;
    private SimpleDateFormat created;
}

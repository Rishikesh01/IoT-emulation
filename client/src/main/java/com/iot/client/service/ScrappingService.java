package com.iot.client.service;

import com.iot.client.dto.Records;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Rishikesh
 * @project IoT
 */

@Service
public class ScrappingService {
    public final MessageChannel messageChannel;
    public final WebClient client;
    private List<Records> records = new ArrayList<>();

    public ScrappingService(MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
        this.client = WebClient.builder().baseUrl("http://3.109.76.78:2222/xenergyData.json").build();
    }

    @Scheduled(fixedRate = 2000)
    public void getIoTData() {
        List<Records> newRecords = List.of(Objects.requireNonNull(client.get().retrieve().bodyToMono(Records[].class).block()));
        if (!this.records.isEmpty() && CollectionUtils.isEqualCollection(newRecords, this.records)) {
            List<Records> diff = new ArrayList<>(CollectionUtils.removeAll(newRecords, this.records));
            this.records = newRecords;
            diff.stream().map(x -> MessageBuilder.withPayload(x).build()).forEach(messageChannel::send);
            return;
        }
        if (this.records.isEmpty())
            newRecords.stream().map(x -> MessageBuilder.withPayload(x).build()).forEach(messageChannel::send);
    }
}

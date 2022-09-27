package com.iot.server.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;

/**
 * @author Rishikesh
 * @project IoT
 */

@Configuration
public class MqttConfig {
    @Bean
    public MqttPahoClientFactory clientFactory(@Value("${mqtt.broker.url}") String url) {
        var factory = new DefaultMqttPahoClientFactory();
        var options = new MqttConnectOptions();
        options.setServerURIs(new String[]{url});
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel out() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MqttPahoMessageHandler outBound(MqttPahoClientFactory clientFactory, @Value("${mqtt.broker.produce}") String topic) {
        var messageHandler = new MqttPahoMessageHandler("producer", clientFactory);
        messageHandler.setDefaultTopic(topic);
        return messageHandler;
    }

    @Bean
    public IntegrationFlow outBoundFlow(MessageChannel out, MqttPahoMessageHandler outBound) {
        return IntegrationFlows.from(out).handle(outBound).get();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter inBound(
            @Value("${mqtt.broker.subscribe}") String topic,
            MqttPahoClientFactory clientFactory) {
        return new MqttPahoMessageDrivenChannelAdapter("consumer", clientFactory, topic);
    }

    @Bean
    public IntegrationFlow inBoundFlow(MqttPahoMessageDrivenChannelAdapter inBound) {
        return IntegrationFlows.from(inBound).handle(System.out::println).get();
    }


}

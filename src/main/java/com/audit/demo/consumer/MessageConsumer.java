package com.audit.demo.consumer;

import com.audit.demo.model.Message;
import com.audit.demo.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumer {

    private final MessageService messageService;

    @RabbitListener(queues = "${rabbitmq.queue.name1}")
    public void consumeQueue1(String messageJson) {
        log.info("Raw message received from queue2: {}", messageJson);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Message message = objectMapper.readValue(messageJson, Message.class);
            log.info("Deserialized message: {}", message);
            messageService.saveMessage(message);
        } catch (Exception e) {
            log.error("Error deserializing message: {}", e.getMessage());
        }
    }

    @RabbitListener(queues = "${rabbitmq.queue.name2}")
    public void consumeQueue2(String messageJson) {
        log.info("Raw message received from queue: {}", messageJson);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Message message = objectMapper.readValue(messageJson, Message.class);
            log.info("Deserialized message: {}", message);
            messageService.saveMessage(message);

        } catch (Exception e) {
            log.error("Error deserializing message: {}", e.getMessage());
        }
    }

    @RabbitListener(queues = "${rabbitmq.queue.name3}")
    public void consumeQueue3(String messageJson) {
        log.info("Raw message received from queue2: {}", messageJson);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Message message = objectMapper.readValue(messageJson, Message.class);
            log.info("Deserialized message: {}", message);
            messageService.saveMessage(message);
        } catch (Exception e) {
            log.error("Error deserializing message: {}", e.getMessage());
        }
    }

}

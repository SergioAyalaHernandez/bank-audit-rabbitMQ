package com.audit.demo.consumer;

import com.audit.demo.model.Message;
import com.audit.demo.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class MessageConsumerTest {

    @Mock
    private MessageService messageService;


    @InjectMocks
    private MessageConsumer messageConsumer;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void consumeQueue1_InvalidJson_ShouldLogError() {
        // Arrange
        String invalidJson = "invalid json";

        // Act
        messageConsumer.consumeQueue1(invalidJson);

        // Assert
        verify(messageService, never()).saveMessage(any(Message.class));
    }


    @Test
    void consumeQueue2_InvalidJson_ShouldLogError() {
        // Arrange
        String invalidJson = "invalid json";

        // Act
        messageConsumer.consumeQueue2(invalidJson);

        // Assert
        verify(messageService, never()).saveMessage(any(Message.class));
    }

    @Test
    void consumeQueue3_InvalidJson_ShouldLogError() {
        // Arrange
        String invalidJson = "invalid json";

        // Act
        messageConsumer.consumeQueue3(invalidJson);

        // Assert
        verify(messageService, never()).saveMessage(any(Message.class));
    }

    @Test
    void allQueues_NullMessage_ShouldLogError() {
        // Arrange
        String nullMessage = null;

        // Act
        messageConsumer.consumeQueue1(nullMessage);
        messageConsumer.consumeQueue2(nullMessage);
        messageConsumer.consumeQueue3(nullMessage);

        // Assert
        verify(messageService, never()).saveMessage(any(Message.class));
    }

    @Test
    void allQueues_CompleteMessage_ShouldSaveMessage() throws Exception {
        // Arrange
        Message message = new Message();
        message.setId("test-id");
        message.setIdEntidad("test-entidad");
        message.setFecha("2024-02-11");
        message.setMensaje("Test mensaje completo");
        message.setRecurso("test-recurso");
        message.setEstado(true);

        String messageJson = objectMapper.writeValueAsString(message);

        // Act
        messageConsumer.consumeQueue1(messageJson);
        messageConsumer.consumeQueue2(messageJson);
        messageConsumer.consumeQueue3(messageJson);

        // Assert
        verify(messageService, times(3)).saveMessage(any(Message.class));
    }
}
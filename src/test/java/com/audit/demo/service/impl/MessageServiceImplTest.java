package com.audit.demo.service.impl;

import com.audit.demo.dto.PageResponse;
import com.audit.demo.model.Message;
import com.audit.demo.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @Test
    void testGetMessagesByIdEntidad() {
        // Arrange
        String idEntidad = "test-id";
        Pageable pageable = PageRequest.of(0, 10);
        List<Message> messages = Arrays.asList(new Message(), new Message());
        long total = 20;

        when(messageRepository.findByIdEntidad(idEntidad, pageable)).thenReturn(Flux.fromIterable(messages));
        when(messageRepository.countByIdEntidad(idEntidad)).thenReturn(Mono.just(total));

        // Act
        Mono<PageResponse<Message>> result = messageService.getMessagesByIdEntidad(idEntidad, pageable);

        StepVerifier.create(result)
                .assertNext(pageResponse -> {
                    assertNotNull(pageResponse);
                    assertEquals(2, pageResponse.getContent().size());
                    assertEquals(0, pageResponse.getPage());
                    assertEquals(10, pageResponse.getSize());
                    assertEquals(total, pageResponse.getTotalElements());
                    assertEquals(2, pageResponse.getTotalPages());
                })
                .verifyComplete();
    }

    @Test
    void testGetMessagesByRecurso() {
        // Arrange
        String recurso = "test-recurso";
        Pageable pageable = PageRequest.of(0, 10);
        List<Message> messages = Arrays.asList(new Message(), new Message());
        long total = 20;

        when(messageRepository.findByRecurso(recurso, pageable)).thenReturn(Flux.fromIterable(messages));
        when(messageRepository.countByRecurso(recurso)).thenReturn(Mono.just(total));

        // Act
        Mono<PageResponse<Message>> result = messageService.getMessagesByRecurso(recurso, pageable);

        // Assert
        StepVerifier.create(result)
                .assertNext(pageResponse -> {
                    assertNotNull(pageResponse);
                    assertEquals(2, pageResponse.getContent().size());
                    assertEquals(0, pageResponse.getPage());
                    assertEquals(10, pageResponse.getSize());
                    assertEquals(total, pageResponse.getTotalElements());
                    assertEquals(2, pageResponse.getTotalPages());
                })
                .verifyComplete();
    }

    @Test
    void testSaveMessage() {
        // Arrange
        Message message = new Message();
        when(messageRepository.save(message)).thenReturn(Mono.just(message));

        // Act
        messageService.saveMessage(message);

        // Assert
        verify(messageRepository, times(1)).save(message);
    }


}

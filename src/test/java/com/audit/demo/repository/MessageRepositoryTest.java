package com.audit.demo.repository;

import com.audit.demo.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class MessageRepositoryTest {

    @Mock
    private MessageRepository messageRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdEntidad_ShouldReturnMessages() {
        // Arrange
        String idEntidad = "123";
        Pageable pageable = PageRequest.of(0, 10);
        Message message1 = new Message();
        message1.setId("1");
        message1.setIdEntidad(idEntidad);
        message1.setMensaje("Test mensaje 1");

        Message message2 = new Message();
        message2.setId("2");
        message2.setIdEntidad(idEntidad);
        message2.setMensaje("Test mensaje 2");

        when(messageRepository.findByIdEntidad(eq(idEntidad), any(Pageable.class)))
                .thenReturn(Flux.just(message1, message2));

        // Act & Assert
        StepVerifier.create(messageRepository.findByIdEntidad(idEntidad, pageable))
                .expectNext(message1)
                .expectNext(message2)
                .verifyComplete();
    }

    @Test
    void findByRecurso_ShouldReturnMessages() {
        // Arrange
        String recurso = "recurso1";
        Pageable pageable = PageRequest.of(0, 10);
        Message message = new Message();
        message.setId("1");
        message.setRecurso(recurso);
        message.setMensaje("Test mensaje");

        when(messageRepository.findByRecurso(eq(recurso), any(Pageable.class)))
                .thenReturn(Flux.just(message));

        // Act & Assert
        StepVerifier.create(messageRepository.findByRecurso(recurso, pageable))
                .expectNext(message)
                .verifyComplete();
    }

    @Test
    void countByIdEntidad_ShouldReturnCount() {
        // Arrange
        String idEntidad = "123";
        Long expectedCount = 5L;

        when(messageRepository.countByIdEntidad(idEntidad))
                .thenReturn(Mono.just(expectedCount));

        // Act & Assert
        StepVerifier.create(messageRepository.countByIdEntidad(idEntidad))
                .expectNext(expectedCount)
                .verifyComplete();
    }

    @Test
    void countByRecurso_ShouldReturnCount() {
        // Arrange
        String recurso = "recurso1";
        Long expectedCount = 3L;

        when(messageRepository.countByRecurso(recurso))
                .thenReturn(Mono.just(expectedCount));

        // Act & Assert
        StepVerifier.create(messageRepository.countByRecurso(recurso))
                .expectNext(expectedCount)
                .verifyComplete();
    }

    @Test
    void findByIdEntidad_ShouldReturnEmptyFlux() {
        // Arrange
        String idEntidad = "nonexistent";
        Pageable pageable = PageRequest.of(0, 10);

        when(messageRepository.findByIdEntidad(eq(idEntidad), any(Pageable.class)))
                .thenReturn(Flux.empty());

        // Act & Assert
        StepVerifier.create(messageRepository.findByIdEntidad(idEntidad, pageable))
                .verifyComplete();
    }
}

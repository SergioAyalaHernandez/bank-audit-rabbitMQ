package com.audit.demo.service;

import com.audit.demo.dto.PageResponse;
import com.audit.demo.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageService {

    void saveMessage(Message message);

    Mono<PageResponse<Message>> getMessagesByIdEntidad(String idEntidad, Pageable pageable);

    Mono<PageResponse<Message>> getMessagesByRecurso(String recurso, Pageable pageable);
}
package com.audit.demo.service.impl;

import com.audit.demo.dto.PageResponse;
import com.audit.demo.model.Message;
import com.audit.demo.repository.MessageRepository;
import com.audit.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Mono<PageResponse<Message>> getMessagesByIdEntidad(String idEntidad, Pageable pageable) {
        return messageRepository.findByIdEntidad(idEntidad, pageable)
                .collectList()
                .flatMap(messages -> messageRepository.countByIdEntidad(idEntidad)
                        .map(total -> {
                            int totalPages = (int) Math.ceil((double) total / pageable.getPageSize());
                            return new PageResponse<>(messages, pageable.getPageNumber(), pageable.getPageSize(), total, totalPages);
                        }));
    }

    @Override
    public Mono<PageResponse<Message>> getMessagesByRecurso(String recurso, Pageable pageable) {
        return messageRepository.findByRecurso(recurso, pageable)
                .collectList()
                .flatMap(messages -> messageRepository.countByRecurso(recurso)
                        .map(total -> {
                            int totalPages = (int) Math.ceil((double) total / pageable.getPageSize());
                            return new PageResponse<>(messages, pageable.getPageNumber(), pageable.getPageSize(), total, totalPages);
                        }));
    }

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message).subscribe();
    }
}

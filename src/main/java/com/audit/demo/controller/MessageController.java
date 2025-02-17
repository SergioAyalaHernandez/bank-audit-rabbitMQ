package com.audit.demo.controller;

import com.audit.demo.dto.PageResponse;
import com.audit.demo.model.Message;
import com.audit.demo.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/byIdEntidad/{idEntidad}")
    public Mono<PageResponse<Message>> getMessagesByIdEntidad(@PathVariable String idEntidad, @RequestParam Pageable pageable) {
        return messageService.getMessagesByIdEntidad(idEntidad, pageable);
    }

    @GetMapping("/byRecurso/{recurso}")
    public Mono<PageResponse<Message>> getMessagesByRecurso(@PathVariable String recurso, @RequestParam Pageable pageable) {
        return messageService.getMessagesByRecurso(recurso, pageable);
    }

}

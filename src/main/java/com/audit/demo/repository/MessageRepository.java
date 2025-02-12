package com.audit.demo.repository;

import com.audit.demo.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageRepository extends ReactiveMongoRepository<Message, String> {
    Flux<Message> findByIdEntidad(String idEntidad, Pageable pageable);
    Flux<Message> findByRecurso(String recurso, Pageable pageable);
    Mono<Long> countByIdEntidad(String idEntidad);
    Mono<Long> countByRecurso(String recurso);
}

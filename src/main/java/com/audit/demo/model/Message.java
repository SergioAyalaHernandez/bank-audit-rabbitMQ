package com.audit.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection = "messages")
public class Message {
    @Id
    private String id;

    @JsonProperty("idEntidad")
    private String idEntidad;

    @JsonProperty("fecha")
    @NonNull
    private String fecha;

    @JsonProperty("mensaje")
    private String mensaje;

    @JsonProperty("recurso")
    private String recurso;

    @JsonProperty("estado")
    private boolean estado;
}

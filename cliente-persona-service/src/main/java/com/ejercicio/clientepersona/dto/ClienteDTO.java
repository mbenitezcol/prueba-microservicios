package com.ejercicio.clientepersona.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteDTO extends PersonaDTO {
    private String clienteId;
    private String contrasena;
    private Boolean estado;
}

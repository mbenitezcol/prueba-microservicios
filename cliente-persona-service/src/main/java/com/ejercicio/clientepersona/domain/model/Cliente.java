package com.ejercicio.clientepersona.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CLIENTE")
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {

    @Column(name = "CLIENTE_ID", nullable = false, unique = true)
    private String clienteId;

    @Column(name = "CONTRASENA", nullable = false, unique = false)    
    private String contrasena;

    @Column(name = "ESTADO", nullable = false, unique = false)    
    private String estado;
}
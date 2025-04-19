package com.prueba.clienteservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "CLIENTE")
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {

    @Column(name = "CLIENTE_ID", nullable = false, unique = true)
    private int clienteId;

    @Column(name = "CONTRASENA", nullable = false, unique = false)    
    private String contrasena;

    @Column(name = "ESTADO", nullable = false, unique = false)    
    private String estado = "TRUE";
}

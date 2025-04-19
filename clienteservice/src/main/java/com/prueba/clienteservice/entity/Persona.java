package com.prueba.clienteservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@MappedSuperclass
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSONA_ID", nullable = false, unique = true)    
    private Long personaId;

    @Column(name = "NOMBRE", nullable = false, unique = false)    
    private String nombre;

    @Column(name = "GENERO", nullable = true, unique = false)    
    private String genero;

    @Column(name = "EDAD", nullable = true, unique = false)    
    private Integer edad;

    @Column(name = "IDENTIFICACION", nullable = false, unique = true)    
    private String identificacion;

    @Column(name = "DIRECCION", nullable = true, unique = false)    
    private String direccion;

    @Column(name = "TELEFONO", nullable = true, unique = false)     
    private String telefono;
}

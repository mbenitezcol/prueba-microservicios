package com.ejercicio.cuentamovimiento.dto;

import lombok.*;

@Data
public class ClienteDTO {
    private String clienteId;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
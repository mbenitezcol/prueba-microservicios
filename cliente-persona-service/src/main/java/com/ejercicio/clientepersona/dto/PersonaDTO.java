package com.ejercicio.clientepersona.dto;

import lombok.Data;

@Data
public class PersonaDTO {
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}

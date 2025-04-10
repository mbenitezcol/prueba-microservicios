package com.ejercicio.cuentamovimiento.dto;

import lombok.*;

@Data
public class CuentaDTO {
    private String numeroCuenta;
    private Long personaId;
    private String tipoCuenta;
    private Double saldoInicial;
    private String estado;
}

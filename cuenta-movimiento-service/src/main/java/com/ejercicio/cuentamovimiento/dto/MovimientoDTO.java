package com.ejercicio.cuentamovimiento.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovimientoDTO {
    private Long movimientoId;   
    private String numeroCuenta;    
    private LocalDate fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
}

package com.ejercicio.cuentamovimiento.dto;

import java.util.*;

import lombok.*;

@Data
public class CuentaConMovimientosDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private String estado;
    private List<MovimientoDTO> movimientos;
}
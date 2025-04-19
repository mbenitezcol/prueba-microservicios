package com.prueba.reporteservice.dto;

import java.math.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaSaldoDTO {
    private String numeroCuenta;
    private BigDecimal saldo;
}
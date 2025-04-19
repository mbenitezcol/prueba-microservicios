package com.prueba.reporteservice.dto;

import java.math.*;
import java.time.*;
import java.time.format.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {
    private LocalDateTime fecha;
    private String tipo;
    private BigDecimal valor;
    private String numeroCuenta;

    public String getFecha() {
        return fecha.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
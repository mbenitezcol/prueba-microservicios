package com.prueba.reporteservice.dto;

import java.util.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteEstadoCuentaDTO {
    private String nombreCliente;
    private List<CuentaSaldoDTO> cuentas;
    private List<MovimientoDTO> movimientos;
}
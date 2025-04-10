package com.ejercicio.cuentamovimiento.dto;

import java.util.*;

import lombok.*;

@Data
public class ReporteEstadoCuentaDTO {
    private ClienteDTO cliente;
    private List<CuentaConMovimientosDTO> cuentas;
}
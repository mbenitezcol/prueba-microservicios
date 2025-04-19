package com.prueba.reporteservice.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.prueba.reporteservice.dto.*;
import com.prueba.reporteservice.service.*;

import lombok.*;

@RestController
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping("/reporte")
    public ResponseEntity<ReporteEstadoCuentaDTO> generarReporteEstadoCuenta(
            @RequestParam("fecha") String rangoFechas,
            @RequestParam("cliente") Integer clienteId) {
        ReporteEstadoCuentaDTO reporte = reporteService.generarReporte(rangoFechas, clienteId);
        return ResponseEntity.ok(reporte);
    }
}
package com.ejercicio.cuentamovimiento.controller;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import org.springframework.format.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.ejercicio.cuentamovimiento.domain.model.*;
import com.ejercicio.cuentamovimiento.dto.*;
import com.ejercicio.cuentamovimiento.infrastructure.repository.*;
import com.ejercicio.cuentamovimiento.integration.*;

@RestController
@RequestMapping("/reporte")
public class ReporteController {

    private final ClienteRestClient clienteRestClient;
    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public ReporteController(ClienteRestClient clienteRestClient,
                             CuentaRepository cuentaRepository,
                             MovimientoRepository movimientoRepository) {
        this.clienteRestClient = clienteRestClient;
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @GetMapping
    public ReporteEstadoCuentaDTO generarReporte(
            @RequestParam String cliente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) {
        ClienteDTO clienteDTO = clienteRestClient.obtenerCliente(cliente);

        List<Cuenta> cuentas = cuentaRepository.findByClienteId(cliente);
        List<CuentaConMovimientosDTO> cuentasConMovimientos = cuentas.stream().map(cuenta -> {
            List<Movimiento> movimientos = movimientoRepository.findByCuentaNumeroCuentaAndFechaBetween(
                    cuenta.getNumeroCuenta(), fechaInicio, fechaFin);

            List<MovimientoDTO> movimientosDTO = movimientos.stream().map(mov -> {
                MovimientoDTO dto = new MovimientoDTO();
                dto.setFecha(mov.getFecha());
                dto.setTipoMovimiento(mov.getTipoMovimiento());
                dto.setValor(mov.getValor());
                dto.setSaldo(mov.getSaldo());
                return dto;
            }).collect(Collectors.toList());

            CuentaConMovimientosDTO cuentaDTO = new CuentaConMovimientosDTO();
            cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());
            cuentaDTO.setEstado(cuenta.getEstado());
            cuentaDTO.setMovimientos(movimientosDTO);

            return cuentaDTO;
        }).collect(Collectors.toList());

        ReporteEstadoCuentaDTO reporte = new ReporteEstadoCuentaDTO();
        reporte.setCliente(clienteDTO);
        reporte.setCuentas(cuentasConMovimientos);
        return reporte;
    }
}

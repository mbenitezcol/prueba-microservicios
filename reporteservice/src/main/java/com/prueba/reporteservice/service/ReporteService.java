package com.prueba.reporteservice.service;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.prueba.clienteservice.entity.*;
import com.prueba.clienteservice.repository.*;
import com.prueba.cuentaservice.entity.*;
import com.prueba.cuentaservice.repository.*;
import com.prueba.cuentaservice.service.*;
import com.prueba.reporteservice.dto.*;
import com.prueba.reporteservice.exception.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;
    private final CuentaService cuentaService; // Inyecta el servicio de cuentas para obtener el saldo actual

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Transactional(readOnly = true)
    public ReporteEstadoCuentaDTO generarReporte(String rangoFechas, Integer clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ReporteException("Cliente no encontrado con ID: " + clienteId));

        String[] fechas = rangoFechas.split("-");
        if (fechas.length != 2) {
            throw new ReporteException("Formato de fecha incorrecto. Use YYYY-MM-DD-YYYY-MM-DD");
        }

        try {
            LocalDate fechaDesde = LocalDate.parse(fechas[0], DATE_FORMATTER);
            LocalDate fechaHasta = LocalDate.parse(fechas[1], DATE_FORMATTER);
            
            LocalDateTime desde = fechaDesde.atStartOfDay();
            LocalDateTime hasta = fechaHasta.atTime(LocalTime.MAX);

            List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
            if (cuentas.isEmpty()) {
                return new ReporteEstadoCuentaDTO(cliente.getNombre(), List.of(), List.of());
            }

            List<CuentaSaldoDTO> cuentasSaldo = cuentas.stream()
            		.map(cuenta -> new CuentaSaldoDTO(cuenta.getNumeroCuenta(), cuentaService.findById(cuenta.getCuentaId()).getSaldoInicial()))            
                    .collect(Collectors.toList());

            List<String> numeroCuentas = cuentas.stream()
                    .map(Cuenta::getNumeroCuenta)
                    .collect(Collectors.toList());

            List<Movimiento> movimientos = movimientoRepository.findByCuenta_NumeroCuentaInAndFechaBetween(numeroCuentas, desde, hasta);

            List<MovimientoDTO> movimientosDTO = movimientos.stream()
                    .map(movimiento -> new MovimientoDTO(
                            movimiento.getFecha(),
                            movimiento.getTipoMovimiento(),
                            movimiento.getValor(),
                            movimiento.getCuenta().getNumeroCuenta()
                    ))
                    .collect(Collectors.toList());

            return new ReporteEstadoCuentaDTO(cliente.getNombre(), cuentasSaldo, movimientosDTO);

        } catch (DateTimeParseException e) {
            throw new ReporteException("Error al parsear la fecha: " + e.getMessage());
        }
    }
}
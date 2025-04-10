package com.ejercicio.cuentamovimiento.infrastructure.controller;

import com.ejercicio.cuentamovimiento.application.MovimientoService;
import com.ejercicio.cuentamovimiento.domain.model.Movimiento;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping("/{numeroCuenta}")
    public Movimiento registrar(@PathVariable String numeroCuenta, @RequestBody Movimiento movimiento) {
        return movimientoService.registrarMovimiento(numeroCuenta, movimiento);
    }

    @GetMapping("/cuenta/{numeroCuenta}")
    public List<Movimiento> porCuenta(@PathVariable String numeroCuenta) {
        return movimientoService.obtenerPorCuenta(numeroCuenta);
    }

    @GetMapping("/cuenta/{numeroCuenta}/rango")
    public List<Movimiento> porRangoFechas(@PathVariable String numeroCuenta,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return movimientoService.obtenerPorRangoFechas(numeroCuenta, desde, hasta);
    }
}

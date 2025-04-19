package com.prueba.cuentaservice.controller;

import java.time.*;
import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.prueba.cuentaservice.entity.*;
import com.prueba.cuentaservice.service.*;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable long id) {
        Movimiento movimiento = movimientoService.findById(id);
        return ResponseEntity.ok(movimiento);
    }

    @PostMapping("/{numeroCuenta}")
    public ResponseEntity<Movimiento> registrarMovimiento(
            @PathVariable @NotNull String numeroCuenta,
            @RequestBody @Valid Movimiento movimiento) {
        Movimiento movimientoRegistrado = movimientoService.registrarMovimiento(numeroCuenta, movimiento);
        return new ResponseEntity<>(movimientoRegistrado, HttpStatus.CREATED);
    }

    @GetMapping("/cuenta/{numeroCuenta}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorCuenta(@PathVariable String numeroCuenta) {
        List<Movimiento> movimientos = movimientoService.obtenerPorCuenta(numeroCuenta);
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/cuenta/{numeroCuenta}/rango")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorRangoFechas(
            @PathVariable @NotNull String numeroCuenta,
            @RequestParam @NotNull LocalDate desde,
            @RequestParam @NotNull LocalDate hasta) {
        List<Movimiento> movimientos = movimientoService.obtenerPorRangoFechas(numeroCuenta, desde, hasta);
        return ResponseEntity.ok(movimientos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizarMovimiento(
            @PathVariable long id,
            @RequestBody @Valid Movimiento movimientoNuevo) {
        Movimiento movimientoActualizado = movimientoService.update(id, movimientoNuevo);
        return ResponseEntity.ok(movimientoActualizado);
    }
}
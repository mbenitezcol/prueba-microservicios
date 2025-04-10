package com.ejercicio.cuentamovimiento.application;

import java.time.*;
import java.util.*;

import org.springframework.stereotype.*;

import com.ejercicio.cuentamovimiento.domain.model.*;
import com.ejercicio.cuentamovimiento.exception.*;
import com.ejercicio.cuentamovimiento.infrastructure.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public Movimiento registrarMovimiento(String numeroCuenta, Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(numeroCuenta)
            .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));

        Double nuevoSaldo = cuenta.getSaldoInicial();
        if ("debito".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            if (cuenta.getSaldoInicial() < movimiento.getValor()) {
                throw new IllegalArgumentException("Saldo insuficiente");
            }
            nuevoSaldo -= movimiento.getValor();
        } else {
            nuevoSaldo += movimiento.getValor();
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);
        cuentaRepository.save(cuenta);
        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> obtenerPorCuenta(String numeroCuenta) {
        return movimientoRepository.findByCuenta_NumeroCuenta(numeroCuenta);
    }

    public List<Movimiento> obtenerPorRangoFechas(String numeroCuenta, LocalDate desde, LocalDate hasta) {
        return movimientoRepository.findByCuenta_NumeroCuentaAndFechaBetween(numeroCuenta, desde, hasta);
    }
}

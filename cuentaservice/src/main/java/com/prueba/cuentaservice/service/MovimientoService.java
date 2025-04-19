package com.prueba.cuentaservice.service;

import java.lang.IllegalArgumentException;
import java.time.*;
import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.validation.annotation.*;

import com.prueba.cuentaservice.entity.*;
import com.prueba.cuentaservice.exception.*;
import com.prueba.cuentaservice.repository.*;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Service
@RequiredArgsConstructor
@Validated
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaService cuentaService;

    
    public Movimiento findById(long id) {
        return movimientoRepository.findByMovimientoId(id)
                .orElseThrow(() -> new ResourceNotFoundException("movimiento no encontrado con id: " + id));
    }    
    
    @Transactional
    public Movimiento registrarMovimiento(@NotNull String numeroCuenta, @Valid Movimiento movimiento) {
        Cuenta cuenta = cuentaService.findByNumeroCuenta(numeroCuenta);
        String tipoMovimiento = movimiento.getTipoMovimiento();

        if ("DEBITO".equalsIgnoreCase(tipoMovimiento)) {
            cuentaService.debitarCuenta(numeroCuenta, movimiento.getValor());
        } else if ("CREDITO".equalsIgnoreCase(tipoMovimiento)) {
            cuentaService.acreditarCuenta(numeroCuenta, movimiento.getValor());
        } else {
            throw new IllegalArgumentException("Tipo de movimiento: " + tipoMovimiento + " Errado");
        }

        movimiento.setCuenta(cuenta);
        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> obtenerPorCuenta(String numeroCuenta) {
        return movimientoRepository.findByCuenta_NumeroCuenta(numeroCuenta);
    }

    public List<Movimiento> obtenerPorRangoFechas(@NotNull String numeroCuenta, @NotNull LocalDate desde, @NotNull LocalDate hasta) {
        return movimientoRepository.findByCuenta_NumeroCuentaAndFechaBetween(numeroCuenta, desde, hasta);
    }

    @Transactional
    public Movimiento update(long id, @Valid Movimiento movimientoNuevo) {
        // se actualiza sobre el movimiento anterior
        Movimiento movimientoAnterior = movimientoRepository.findByMovimientoId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con id: " + id));

        Cuenta cuentaAsociada = movimientoAnterior.getCuenta();
        cuentaService.validarCuentaActiva(cuentaAsociada.getNumeroCuenta());

        actualizarCamposBasicosMovimiento(movimientoAnterior, movimientoNuevo);
        cuentaService.ajustarSaldo(cuentaAsociada.getNumeroCuenta(), movimientoAnterior, movimientoNuevo);

        return movimientoRepository.save(movimientoAnterior); 
    }

    private void actualizarCamposBasicosMovimiento(Movimiento movimientoAnterior, Movimiento movimientoNuevo) {
        Optional.ofNullable(movimientoNuevo.getCuenta()).ifPresent(movimientoAnterior::setCuenta);
        Optional.ofNullable(movimientoNuevo.getFecha()).ifPresent(movimientoAnterior::setFecha);
        Optional.ofNullable(movimientoNuevo.getTipoMovimiento()).ifPresent(movimientoAnterior::setTipoMovimiento);
        Optional.ofNullable(movimientoNuevo.getValor()).ifPresent(movimientoAnterior::setValor);
    }
}
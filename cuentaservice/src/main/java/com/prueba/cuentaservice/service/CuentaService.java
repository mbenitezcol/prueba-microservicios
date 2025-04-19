package com.prueba.cuentaservice.service;

import java.math.*;
import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.validation.annotation.*;

import com.prueba.cuentaservice.entity.*;
import com.prueba.cuentaservice.exception.*;
import com.prueba.cuentaservice.repository.*;

import jakarta.validation.*;
import lombok.*;

@Service
@RequiredArgsConstructor
@Validated 
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    @Transactional
    public Cuenta create(@Valid Cuenta cuenta) {
        if (cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta()).isPresent()) {
            throw new CuentaExistenteException("Ya existe una cuenta con el número: " + cuenta.getNumeroCuenta());
        }
        return cuentaRepository.save(cuenta);
    }

    public List<Cuenta> getAll() {
        return cuentaRepository.findAll();
    }

    public List<Cuenta> getCuentasByPersonaId() {
        return cuentaRepository.findAll();
    }
    
    public Cuenta findByPersonaId(long id) {
        return cuentaRepository.findByPersonaId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + id));
    }

    public Cuenta findByNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con número: " + numeroCuenta));
    }

    @Transactional
    public Cuenta update(int id, @Valid Cuenta dataCuenta) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + id));

        Optional.ofNullable(dataCuenta.getPersonaId()).ifPresent(cuenta::setPersonaId);
        Optional.ofNullable(dataCuenta.getNumeroCuenta()).ifPresent(cuenta::setNumeroCuenta);
        Optional.ofNullable(dataCuenta.getSaldoInicial()).ifPresent(cuenta::setSaldoInicial);
        Optional.ofNullable(dataCuenta.getTipoCuenta()).ifPresent(cuenta::setTipoCuenta);

        return cuentaRepository.save(cuenta);
    }
       
    public void validarCuentaActiva (String numeroCuenta) {
        Cuenta cuenta = findByNumeroCuenta(numeroCuenta);
        if (!cuenta.isCuentaActiva()) { 
            throw new AccountInactiveException("La cuenta " + numeroCuenta + " esta inactiva");
        }
    }
    
    @Transactional
    public void debitarCuenta(String numeroCuenta, BigDecimal valor) {
    	Cuenta cuenta = findByNumeroCuenta(numeroCuenta);
    	
    	BigDecimal saldo = cuenta.getSaldoInicial();
    	 
    	if (saldo.compareTo(valor) < 0) {
    		throw new InsufficientBalanceException("Saldo insuficiente al registrar movimiento");	
    	}
    	
    	cuenta.setSaldoInicial(saldo.subtract(valor));
    	
    	cuentaRepository.save(cuenta);
    }
    
    @Transactional
    public void acreditarCuenta(String numeroCuenta, BigDecimal valor) {
    	Cuenta cuenta = findByNumeroCuenta(numeroCuenta);
    	
    	BigDecimal saldoInicial = cuenta.getSaldoInicial();
    	 
    	cuenta.setSaldoInicial(saldoInicial.add(valor));
    	
    	cuentaRepository.save(cuenta);
    }    
    
    @Transactional
    public void ajustarSaldo (String numeroCuenta, Movimiento movimientoAnterior, Movimiento movimientoNuevo) {
        String tipoAnterior      = movimientoAnterior.getTipoMovimiento();
        String tipoNuevo         = movimientoNuevo.getTipoMovimiento();
        BigDecimal valorAnterior = movimientoAnterior.getValor();
        BigDecimal valorNuevo    = movimientoNuevo.getValor();

        if (!tipoNuevo.equalsIgnoreCase(tipoAnterior)) {
        	// reversar movimiento anterior 
            if ("DEBITO".equalsIgnoreCase(tipoAnterior)) { 
              acreditarCuenta(numeroCuenta, valorAnterior);
            } else if ("CREDITO".equalsIgnoreCase(tipoAnterior)) { 
              debitarCuenta(numeroCuenta, valorAnterior);
            }
            
            // aplicar nuevo movimiento
            if ("DEBITO".equalsIgnoreCase(tipoNuevo)) {
              debitarCuenta(numeroCuenta, valorNuevo);
            } else if ("CREDITO".equalsIgnoreCase(tipoNuevo)) {
              acreditarCuenta(numeroCuenta, valorNuevo);
            }
        } else if (valorNuevo.compareTo(valorAnterior) != 0) {
            BigDecimal diferencia = valorNuevo.subtract(valorAnterior);
            if ("DEBITO".equalsIgnoreCase(tipoNuevo)) {
              debitarCuenta(numeroCuenta, diferencia);
            } else if ("CREDITO".equalsIgnoreCase(tipoNuevo)) {
              acreditarCuenta(numeroCuenta, diferencia);
            }
        }    	
    }
}
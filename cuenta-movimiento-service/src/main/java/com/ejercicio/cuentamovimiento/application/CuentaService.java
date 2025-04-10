package com.ejercicio.cuentamovimiento.application;

import com.ejercicio.cuentamovimiento.domain.model.Cuenta;
import com.ejercicio.cuentamovimiento.infrastructure.repository.CuentaRepository;
import com.ejercicio.cuentamovimiento.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public Cuenta create(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta update(String numeroCuenta, Cuenta cuenta) {
        Cuenta existing = cuentaRepository.findById(numeroCuenta)
            .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));
        cuenta.setNumeroCuenta(existing.getNumeroCuenta());
        return cuentaRepository.save(cuenta);
    }

    public void delete(String numeroCuenta) {
        cuentaRepository.deleteById(numeroCuenta);
    }

    public Cuenta get(String numeroCuenta) {
        return cuentaRepository.findById(numeroCuenta)
            .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));
    }

    public List<Cuenta> getAll() {
        return cuentaRepository.findAll();
    }
}

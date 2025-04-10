package com.ejercicio.cuentamovimiento.infrastructure.controller;

import com.ejercicio.cuentamovimiento.application.CuentaService;
import com.ejercicio.cuentamovimiento.domain.model.Cuenta;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @PostMapping
    public Cuenta create(@RequestBody Cuenta cuenta) {
        return cuentaService.create(cuenta);
    }

    @PutMapping("/{id}")
    public Cuenta update(@PathVariable String id, @RequestBody Cuenta cuenta) {
        return cuentaService.update(id, cuenta);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        cuentaService.delete(id);
    }

    @GetMapping("/{id}")
    public Cuenta get(@PathVariable String id) {
        return cuentaService.get(id);
    }

    @GetMapping
    public List<Cuenta> getAll() {
        return cuentaService.getAll();
    }
}

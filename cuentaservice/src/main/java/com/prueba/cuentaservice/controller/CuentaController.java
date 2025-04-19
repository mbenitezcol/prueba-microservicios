package com.prueba.cuentaservice.controller;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.prueba.cuentaservice.entity.*;
import com.prueba.cuentaservice.service.*;

import lombok.*;

@CrossOrigin("*")
@RequestMapping(value = "/cuentas")
@RestController
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.create(cuenta);
    }
    
    @GetMapping
    public List<Cuenta> getAllCuentas(){
        return  cuentaService.getAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable int id){
        Cuenta cuenta = cuentaService.findByPersonaId(id);    
        return ResponseEntity.ok(cuenta);
    }   

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> obtenerCuentaPorNumeroCuenta(@PathVariable String numeroCuenta) {
        Cuenta cuenta = cuentaService.findByNumeroCuenta(numeroCuenta);
        return ResponseEntity.ok(cuenta);
    }    

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable int id, @RequestBody Cuenta cuenta) {
        Cuenta cuentaUpdated = cuentaService.update(id, cuenta);
        return ResponseEntity.ok(cuentaUpdated);
    }
        
}

package com.ejercicio.clientepersona.infrastructure.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import com.ejercicio.clientepersona.application.*;
import com.ejercicio.clientepersona.domain.model.*;

import lombok.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteService.create(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable String id, @RequestBody Cliente cliente) {
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        clienteService.delete(id);
    }

    @GetMapping("/{id}")
    public Cliente get(@PathVariable String id) {
        return clienteService.get(id);
    }

    @GetMapping
    public List<Cliente> getAll() {
        return clienteService.getAll();
    }
}

package com.prueba.clienteservice.controller;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.prueba.clienteservice.entity.*;
import com.prueba.clienteservice.service.*;

import lombok.*;

@CrossOrigin("*")
@RequestMapping(value = "/clientes")
@RestController
@RequiredArgsConstructor
public class ClienteController {
	
    private final ClienteService clienteService;

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente){
        return clienteService.create(cliente);
    }
    
    @GetMapping
    public List<Cliente> getAllClientes(){
        return  clienteService.getAll();
    }
    
    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable int id){
        return clienteService.findById(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        Cliente updated = clienteService.update(id, cliente);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) {
        clienteService.delete(id);
        return ResponseEntity.ok().build(); 
    }
    
    
}
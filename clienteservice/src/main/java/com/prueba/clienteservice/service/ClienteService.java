package com.prueba.clienteservice.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.prueba.clienteservice.entity.*;
import com.prueba.clienteservice.exception.*;
import com.prueba.clienteservice.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;
	
	
	public Cliente create (Cliente cliente) {
	    Integer ultimoClienteId = clienteRepository.findTopByOrderByClienteIdDesc()
	            .map(Cliente::getClienteId)
	            .orElse(1); // Si no hay clientes, empieza en 1 
	    cliente.setClienteId(ultimoClienteId + 1);
		return clienteRepository.save(cliente);
	}
	
	public List<Cliente> getAll(){
		return clienteRepository.findAll();
	}
	
	public Cliente findById(int id) {	
		Cliente	cliente = clienteRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
		return cliente;
	}
	
    public Cliente update(int id, Cliente dataCliente) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        
        
        Optional.ofNullable(dataCliente.getNombre()).ifPresent(nombre -> cliente.setNombre(nombre));
        Optional.ofNullable(dataCliente.getContrasena()).ifPresent(contrasena -> cliente.setContrasena(contrasena));
        Optional.ofNullable(dataCliente.getEstado()).ifPresent(estado -> cliente.setEstado(estado));
        Optional.ofNullable(dataCliente.getIdentificacion()).ifPresent(indentificacion -> cliente.setIdentificacion(indentificacion));

        cliente.setDireccion(dataCliente.getDireccion());
        cliente.setEdad(dataCliente.getEdad());
        cliente.setGenero(dataCliente.getGenero());
        cliente.setTelefono(dataCliente.getTelefono());

        return clienteRepository.save(cliente);
    }	
	
	public void delete (int id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        
        clienteRepository.delete(cliente);
	}
}

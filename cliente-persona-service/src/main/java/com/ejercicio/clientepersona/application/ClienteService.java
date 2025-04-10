package com.ejercicio.clientepersona.application;

import java.util.*;

import org.springframework.stereotype.*;

import com.ejercicio.clientepersona.domain.model.*;
import com.ejercicio.clientepersona.events.*;
import com.ejercicio.clientepersona.exception.*;
import com.ejercicio.clientepersona.infrastructure.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteEventPublisher eventPublisher;

    public Cliente create(Cliente cliente) {
        Cliente saved = clienteRepository.save(cliente);
        eventPublisher.publishClienteCreado(saved.getClienteId());
        return saved;
    }

    public Cliente update(String id, Cliente updated) {
        Cliente existing = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        updated.setPersonaId(existing.getPersonaId());
        return clienteRepository.save(updated);
    }

    public void delete(String id) {
        Cliente existing = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        clienteRepository.delete(existing);
    }

    public Cliente get(String id) {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }
}

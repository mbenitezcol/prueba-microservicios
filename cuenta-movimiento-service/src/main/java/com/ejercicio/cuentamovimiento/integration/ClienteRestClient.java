package com.ejercicio.cuentamovimiento.integration;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import com.ejercicio.cuentamovimiento.dto.*;

@Component
public class ClienteRestClient {

    private final RestTemplate restTemplate;

    @Value("${servicio.cliente-persona.url}")
    private String clienteServiceUrl;

    public ClienteRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClienteDTO obtenerCliente(String clienteId) {
        return restTemplate.getForObject(clienteServiceUrl + "/clientes/" + clienteId, ClienteDTO.class);
    }
}
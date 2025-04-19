package com.prueba.clienteservice.event;

import org.springframework.context.*;
import org.springframework.stereotype.*;

@Component
public class ClienteEventPublisher {

    private final ApplicationEventPublisher publisher;

    public ClienteEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishClienteCreado(String clienteId) {
        publisher.publishEvent(new ClienteCreadoEvent(clienteId));
    }
}
package com.ejercicio.clientepersona.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

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

package com.prueba.clienteservice.event;

import lombok.*;

@Data
@AllArgsConstructor
public class ClienteCreadoEvent {
    private String clienteId;
    
    public ClienteCreadoEvent(Object source, String clienteId) {
        super();
        this.clienteId = clienteId;
    }

    public String getClienteId() {
        return clienteId;
    }    
}

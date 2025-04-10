package com.ejercicio.cuentamovimiento.events;

import org.springframework.context.annotation.*;
import org.springframework.context.event.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import com.ejercicio.cuentamovimiento.domain.model.*;
import com.ejercicio.cuentamovimiento.infrastructure.repository.*;

import lombok.*;

@Component
@RequiredArgsConstructor
public class ClienteCreadoListener {

    private final CuentaRepository cuentaRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @EventListener
    public void handleClienteCreado(ClienteCreadoRemoteEvent event) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(event.getClienteId());
        cuenta.setSaldoInicial(0.0);
        cuenta.setEstado("AC");
        cuenta.setTipoCuenta("AHORROS");

        cuentaRepository.save(cuenta);
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return restTemplate;
    }    
}

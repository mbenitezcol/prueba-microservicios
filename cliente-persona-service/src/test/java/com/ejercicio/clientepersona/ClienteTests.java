package com.ejercicio.clientepersona;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import com.ejercicio.clientepersona.domain.model.*;

public class ClienteTests {

    @Test
    void crearClienteTest() {
        Cliente cliente = new Cliente();
        cliente.setClienteId("C123");
        cliente.setContrasena("1234");
        cliente.setEstado("AC");
        cliente.setNombre("Juan Perez");

        assertEquals("C123", cliente.getClienteId());
        assertEquals("Juan Perez", cliente.getNombre());
    }
}

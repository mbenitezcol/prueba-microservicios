package com.prueba.clienteservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import com.prueba.clienteservice.entity.*;
import com.prueba.clienteservice.repository.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setUp() {
        cliente1 = new Cliente();
        cliente1.setNombre("Carlos López");
        cliente1.setIdentificacion("112233445");
        cliente1.setContrasena("securePass");
        cliente1.setDireccion("Calle Falsa 123");
        cliente1.setTelefono("555-1234");
        cliente1.setGenero("Masculino");
        cliente1.setEdad(30);
        cliente1.setEstado("TRUE");

        cliente2 = new Cliente();
        cliente2.setNombre("María Gómez");
        cliente2.setIdentificacion("987654321");
        cliente2.setContrasena("anotherPass");
        cliente2.setDireccion("Avenida Siempreviva 742");
        cliente2.setTelefono("555-5678");
        cliente2.setGenero("Femenino");
        cliente2.setEdad(25);
        cliente2.setEstado("FALSE");
    }

    @Test
    void create_nuevoCliente_clienteCreado() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente1);

        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre("Carlos López");
        nuevoCliente.setIdentificacion("112233445");

        Cliente creado = clienteService.create(nuevoCliente);

        assertNotNull(creado);
        assertEquals(nuevoCliente.getNombre(), creado.getNombre());
        assertEquals(nuevoCliente.getIdentificacion(), creado.getIdentificacion());
        verify(clienteRepository, times(1)).save(nuevoCliente);
    }
}
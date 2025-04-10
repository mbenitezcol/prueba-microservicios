package com.ejercicio.clientepersona.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.http.*;
import org.springframework.test.context.*;

import com.ejercicio.clientepersona.*;
import com.ejercicio.clientepersona.domain.model.*;
import com.ejercicio.clientepersona.infrastructure.repository.*;


@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
   ,classes = ClientePersonaApplication.class
)
    
@TestPropertySource(locations = "classpath:application-test.properties")

class ClientePersonaIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setup() {
        clienteRepository.deleteAll();
    }

    @Test
    void crearYObtenerCliente_exitoso() {
        Cliente nuevo = new Cliente();
        nuevo.setNombre("Ana Gomez");
        nuevo.setEdad(29);
        nuevo.setGenero("F");
        nuevo.setIdentificacion("CC12345");
        nuevo.setDireccion("Calle 123");
        nuevo.setTelefono("3101234567");
        nuevo.setContrasena("secreta");
        nuevo.setEstado("AC");

        ResponseEntity<Cliente> postResponse = restTemplate.postForEntity("/clientes", nuevo, Cliente.class);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        Cliente creado = postResponse.getBody();
        assertNotNull(creado);
        assertNotNull(creado.getClienteId());

        ResponseEntity<Cliente> getResponse = restTemplate.getForEntity("/clientes/" + creado.getClienteId(), Cliente.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Ana Gomez", getResponse.getBody().getNombre());
    }

    @Test
    void eliminarCliente_exitoso() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos Test");
        cliente.setEdad(40);
        cliente.setGenero("M");
        cliente.setIdentificacion("CC77777");
        cliente.setDireccion("Av Test 77");
        cliente.setTelefono("3100007777");
        cliente.setContrasena("clave");
        cliente.setEstado("AC");

        Cliente guardado = clienteRepository.save(cliente);

        ResponseEntity<Void> deleteResponse = restTemplate.exchange("/clientes/" + guardado.getClienteId(), HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), Void.class);
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
        assertFalse(clienteRepository.findById(guardado.getClienteId()).isPresent());
    }

    @Test
    void generarEstadoCuenta_exitoso() {
        String clienteId = "1";
        String fechaInicio = "2024-01-01";
        String fechaFin = "2024-12-31";

        String url = String.format("/reporte?cliente=%s&fechaInicio=%s&fechaFin=%s", clienteId, fechaInicio, fechaFin);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("cuentas"));
        assertTrue(response.getBody().contains("movimientos"));
    }
}

package com.ejercicio.cuentamovimiento.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.boot.test.web.server.*;
import org.springframework.http.*;
import org.springframework.test.context.*;

import com.ejercicio.cuentamovimiento.*;
import com.ejercicio.cuentamovimiento.dto.*;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
   ,classes = CuentaMovimientoApplication.class 
)

@TestPropertySource(locations = "classpath:application-test.properties")

public class ReporteControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGenerarReporte_exitoso() {
        // Valores de prueba (ajustalos si usas datos reales)
        String clienteId = "1";
        String fechaInicio = "2024-01-01";
        String fechaFin = "2024-12-31";

        String url = String.format("http://localhost:%d/reporte?cliente=%s&fechaInicio=%s&fechaFin=%s",
                port, clienteId, fechaInicio, fechaFin);

        ResponseEntity<ReporteEstadoCuentaDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                ReporteEstadoCuentaDTO.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        ReporteEstadoCuentaDTO reporte = response.getBody();
        assertNotNull(reporte.getCliente());
        assertNotNull(reporte.getCuentas());

        System.out.println("Cliente: " + reporte.getCliente().getNombre());
        System.out.println("Cuentas encontradas: " + reporte.getCuentas().size());
    }
}

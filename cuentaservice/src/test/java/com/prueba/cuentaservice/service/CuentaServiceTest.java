package com.prueba.cuentaservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import com.prueba.cuentaservice.entity.*;
import com.prueba.cuentaservice.repository.*;

@ExtendWith(MockitoExtension.class)
class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private CuentaService cuentaService;

    private Cuenta cuentaActiva;
    private Cuenta cuentaInactiva;

    @BeforeEach
    void setUp() {
        cuentaActiva = new Cuenta();
        cuentaActiva.setCuentaId(7L);
        cuentaActiva.setPersonaId(7L);        
        cuentaActiva.setNumeroCuenta("789012");
        cuentaActiva.setTipoCuenta("Ahorros");
        cuentaActiva.setSaldoInicial(new BigDecimal("10005200.00"));
        cuentaActiva.setEstado("TRUE");

        cuentaInactiva = new Cuenta();
        cuentaInactiva.setCuentaId(8L);
        cuentaInactiva.setPersonaId(8L);        
        cuentaInactiva.setNumeroCuenta("654321");
        cuentaActiva.setTipoCuenta("Corriente");
        cuentaInactiva.setSaldoInicial(new BigDecimal("5000000.00"));
        cuentaInactiva.setEstado("FALSE");
        
    }

    @Test
    void create_nuevaCuenta_cuentaCreada() {
        when(cuentaRepository.findByNumeroCuenta(anyString())).thenReturn(Optional.empty());
        when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuentaActiva);

        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setCuentaId(9L);
        nuevaCuenta.setPersonaId(8L);        
        nuevaCuenta.setNumeroCuenta("789012");
        nuevaCuenta.setTipoCuenta("Ahorros");
        nuevaCuenta.setSaldoInicial(new BigDecimal("10005200.00"));
        nuevaCuenta.setEstado("TRUE");        


        Cuenta creada = cuentaService.create(nuevaCuenta);

        assertNotNull(creada);
        assertEquals(nuevaCuenta.getNumeroCuenta(), creada.getNumeroCuenta());
        verify(cuentaRepository, times(1)).findByNumeroCuenta(nuevaCuenta.getNumeroCuenta());
        verify(cuentaRepository, times(1)).save(nuevaCuenta);
    }
}



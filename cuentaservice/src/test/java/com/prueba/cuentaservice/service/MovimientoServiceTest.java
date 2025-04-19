package com.prueba.cuentaservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.*;
import java.time.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import com.prueba.cuentaservice.entity.*;
import com.prueba.cuentaservice.repository.*;

@ExtendWith(MockitoExtension.class)
class MovimientoServiceTest {

    @Mock
    private MovimientoRepository movimientoRepository;

    @Mock
    private CuentaService cuentaService;

    @InjectMocks
    private MovimientoService movimientoService;

    private Cuenta cuentaActiva;
    private Movimiento movimientoDebito;
    private Movimiento movimientoCredito;
    private Movimiento movimientoExistente;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        cuentaActiva = new Cuenta();    
        cuentaActiva.setCuentaId(10L);
        cuentaActiva.setPersonaId(8L);        
        cuentaActiva.setNumeroCuenta("CTA789012753");
        cuentaActiva.setTipoCuenta("Corriente");
        cuentaActiva.setSaldoInicial(new BigDecimal("2800000.00"));
        cuentaActiva.setEstado("TRUE");          
        
        now = LocalDateTime.now();

        movimientoDebito = new Movimiento();
        movimientoDebito.setMovimientoId(11L);
        movimientoDebito.setCuenta(cuentaActiva);
        movimientoDebito.setTipoMovimiento("DEBITO");
        movimientoDebito.setValor(new BigDecimal("500000.00"));
        movimientoDebito.setFecha(now);

        movimientoCredito = new Movimiento();
        movimientoCredito.setMovimientoId(12L);
        movimientoCredito.setCuenta(cuentaActiva);
        movimientoCredito.setTipoMovimiento("CREDITO");
        movimientoCredito.setValor(new BigDecimal("100000.00"));
        movimientoCredito.setFecha(now.plusHours(1));

        movimientoExistente = new Movimiento();
        movimientoExistente.setMovimientoId(12L);
        movimientoExistente.setCuenta(cuentaActiva);
        movimientoExistente.setTipoMovimiento("DEBITO");
        movimientoExistente.setValor(new BigDecimal("100000.00"));
        movimientoExistente.setFecha(now.minusDays(2));
    }

    @Test
    void findById_movimientoExistente_retornaMovimiento() {
        when(movimientoRepository.findByMovimientoId(10L)).thenReturn(Optional.of(movimientoDebito));

        Movimiento encontrado = movimientoService.findById(10L);

        assertNotNull(encontrado);
        assertEquals(movimientoDebito.getMovimientoId(), encontrado.getMovimientoId());
        verify(movimientoRepository, times(1)).findByMovimientoId(10L);
    }

}
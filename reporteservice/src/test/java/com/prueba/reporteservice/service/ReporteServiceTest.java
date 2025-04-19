package com.prueba.reporteservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import com.prueba.clienteservice.entity.*;
import com.prueba.clienteservice.repository.*;
import com.prueba.cuentaservice.entity.*;
import com.prueba.cuentaservice.repository.*;
import com.prueba.cuentaservice.service.*;
import com.prueba.reporteservice.dto.*;

@ExtendWith(MockitoExtension.class)
class ReporteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private MovimientoRepository movimientoRepository;

    @Mock
    private CuentaService cuentaService;

    @InjectMocks
    private ReporteService reporteService;

    private Cliente cliente;
    private Cuenta cuenta1;
    private Cuenta cuenta2;
    private Movimiento movimiento1;
    private Movimiento movimiento2;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setClienteId(1);
        cliente.setNombre("Test Cliente");
        
        cliente = new Cliente();
        cliente.setPersonaId(9L);        
        cliente.setNombre("María Gómez");
        cliente.setIdentificacion("987654321");
        cliente.setContrasena("anotherPass");
        cliente.setDireccion("Avenida Siempreviva 742");
        cliente.setTelefono("555-5678");
        cliente.setGenero("Femenino");
        cliente.setEdad(25);
        cliente.setEstado("FALSE");        

        cuenta1 = new Cuenta(); 
        cuenta1.setCuentaId(11L);
        cuenta1.setPersonaId(9L);                
        cuenta1.setNumeroCuenta("CTA789012753");
        cuenta1.setTipoCuenta("Corriente");
        cuenta1.setSaldoInicial(new BigDecimal("2800000.00"));
        cuenta1.setEstado("TRUE"); 
        
        cuenta2 = new Cuenta();
        cuenta2.setCuentaId(12L);
        cuenta2.setPersonaId(9L);   
        cuenta2.setNumeroCuenta("654321");
        cuenta1.setTipoCuenta("Ahorros");        
        cuenta2.setSaldoInicial(new BigDecimal("5300000.00"));
        cuenta1.setEstado("TRUE"); 

        LocalDateTime now = LocalDateTime.now();
        movimiento1 = new Movimiento();
        movimiento1.setMovimientoId(13L);
        movimiento1.setCuenta(cuenta1);
        movimiento1.setFecha(now.minusDays(2));
        movimiento1.setTipoMovimiento("DEBITO");
        movimiento1.setValor(new BigDecimal("1000050.00"));

        movimiento2 = new Movimiento();
        movimiento2.setMovimientoId(14L);
        movimiento2.setCuenta(cuenta2);
        movimiento2.setFecha(now.minusDays(1));
        movimiento2.setTipoMovimiento("CREDITO");
        movimiento2.setValor(new BigDecimal("1500000.00"));
    }


    @Test
    void generarReporte_clienteConCuentasYMovimientos_retornaReporteCompleto() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(cuentaRepository.findByClienteId(1)).thenReturn(Arrays.asList(cuenta1, cuenta2));
        when(cuentaService.findById(10L)).thenReturn(cuenta1);
        when(cuentaService.findById(11L)).thenReturn(cuenta2);

        LocalDate fechaDesde = LocalDate.parse("2025-04-15", DATE_FORMATTER);
        LocalDate fechaHasta = LocalDate.parse("2025-04-17", DATE_FORMATTER);
        LocalDateTime desde = fechaDesde.atStartOfDay();
        LocalDateTime hasta = fechaHasta.atTime(LocalTime.MAX);

        when(movimientoRepository.findByCuenta_NumeroCuentaInAndFechaBetween(Arrays.asList("123456", "654321"), desde, hasta))
                .thenReturn(Arrays.asList(movimiento1, movimiento2));

        ReporteEstadoCuentaDTO reporte = reporteService.generarReporte("2025-04-15-2025-04-17", 1);

        assertNotNull(reporte);
        assertEquals(cliente.getNombre(), reporte.getNombreCliente());
        assertEquals(2, reporte.getCuentas().size());
        assertEquals("CTA789012753", reporte.getCuentas().get(0).getNumeroCuenta());
        assertEquals(cuenta1.getSaldoInicial(), reporte.getCuentas().get(0).getSaldo());
        assertEquals("654321", reporte.getCuentas().get(1).getNumeroCuenta());
        assertEquals(cuenta2.getSaldoInicial(), reporte.getCuentas().get(1).getSaldo());

        assertEquals(2, reporte.getMovimientos().size());
        assertEquals(movimiento1.getFecha().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), reporte.getMovimientos().get(0).getFecha());
        assertEquals(movimiento1.getTipoMovimiento(), reporte.getMovimientos().get(0).getTipo());
        assertEquals(movimiento1.getValor(), reporte.getMovimientos().get(0).getValor());
        assertEquals(cuenta1.getNumeroCuenta(), reporte.getMovimientos().get(0).getNumeroCuenta());

        assertEquals(movimiento2.getFecha().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), reporte.getMovimientos().get(1).getFecha());
        assertEquals(movimiento2.getTipoMovimiento(), reporte.getMovimientos().get(1).getTipo());
        assertEquals(movimiento2.getValor(), reporte.getMovimientos().get(1).getValor());
        assertEquals(cuenta2.getNumeroCuenta(), reporte.getMovimientos().get(1).getNumeroCuenta());
    }

}
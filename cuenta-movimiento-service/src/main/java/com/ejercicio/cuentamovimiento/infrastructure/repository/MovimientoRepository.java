package com.ejercicio.cuentamovimiento.infrastructure.repository;

import java.time.*;
import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.ejercicio.cuentamovimiento.domain.model.*;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuenta_NumeroCuenta(String numeroCuenta);
    List<Movimiento> findByCuenta_NumeroCuentaAndFechaBetween(String numeroCuenta, LocalDate startDate, LocalDate endDate);
	List<Movimiento> findByCuentaNumeroCuentaAndFechaBetween(String numeroCuenta, LocalDate fechaInicio, LocalDate fechaFin);
}

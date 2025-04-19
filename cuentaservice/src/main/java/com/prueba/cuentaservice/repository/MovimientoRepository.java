package com.prueba.cuentaservice.repository;

import java.time.*;
import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.prueba.cuentaservice.entity.*;

import jakarta.validation.constraints.*;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{
	
	public Optional<Movimiento> findByMovimientoId(long id);
	
	public List<Movimiento> findByCuenta_NumeroCuenta(String numeroCuenta);
	
	public List<Movimiento> findByCuenta_NumeroCuentaAndFechaBetween(
		@NotNull String numeroCuenta, @NotNull LocalDate desde,	@NotNull LocalDate hasta
    );
	
	public List<Movimiento> findByCuenta_NumeroCuentaAndFechaBetween(
		String numeroCuenta, LocalDateTime desde, LocalDateTime hasta
	);
	
	public List<Movimiento> findByCuenta_NumeroCuentaInAndFechaBetween(
		List<String> numeroCuentas, LocalDateTime desde, LocalDateTime hasta
	);
}	



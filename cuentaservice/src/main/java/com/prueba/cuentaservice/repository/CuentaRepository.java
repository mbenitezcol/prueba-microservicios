package com.prueba.cuentaservice.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.prueba.cuentaservice.entity.*;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer>{
    Optional<Cuenta> findByPersonaId(long personaId);
	Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
	
	List<Cuenta> getCuentasByPersonaId(long personaId);
}
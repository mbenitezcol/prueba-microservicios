package com.ejercicio.cuentamovimiento.infrastructure.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.ejercicio.cuentamovimiento.domain.model.*;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {

	List<Cuenta> findByClienteId(String cliente);
}

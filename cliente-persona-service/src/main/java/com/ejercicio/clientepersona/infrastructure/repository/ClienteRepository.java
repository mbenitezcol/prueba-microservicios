package com.ejercicio.clientepersona.infrastructure.repository;

import com.ejercicio.clientepersona.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}

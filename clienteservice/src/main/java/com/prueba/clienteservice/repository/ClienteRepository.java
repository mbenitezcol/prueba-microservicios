package com.prueba.clienteservice.repository;


import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.prueba.clienteservice.entity.*;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	Optional<Cliente> findTopByOrderByClienteIdDesc();
}

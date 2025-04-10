package com.ejercicio.cuentamovimiento.domain.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "NUMERO_CUENTA", nullable = false, unique = true)	
    private String numeroCuenta;

	@Column(name = "CLIENTE_ID", nullable = false, unique = false)
    private long clienteId;
    
    @Column(name = "TIPO_CUENTA", nullable = false, unique = false)    
    private String tipoCuenta;
    
    @Column(name = "SALDO_INICIAL", nullable = false, unique = false)    
    private Double saldoInicial;
    
    @Column(name = "ESTADO", nullable = false, unique = false)    
    private String estado;
}

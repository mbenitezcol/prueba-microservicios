package com.prueba.cuentaservice.entity;

import java.math.*;
import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "CUENTA")
@Data
public class Cuenta {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "CUENTA_ID", nullable = false, unique = true)    
	 private Long cuentaId;	
	
	 @NotNull(message = "El ID del cliente no puede ser nulo")
	 @Column(name = "PERSONA_ID", nullable = false, unique = false)
	 private long personaId;    

	 @NotBlank(message = "El número de cuenta no puede estar en blanco")
	 @Column(name = "NUMERO_CUENTA", nullable = false, unique = true)
	 private String numeroCuenta;
	 
	 @NotBlank(message = "El tipo de cuenta no puede estar en blanco")
	 @Column(name = "TIPO_CUENTA", nullable = false)
	 private String tipoCuenta = "AHORROS";      
	    
	 @NotNull(message = "El saldo no puede ser nulo")
	 @DecimalMin(value = "0.00", inclusive = true, message = "El saldo debe ser mayor o igual a 0.00")
	 @Column(name = "SALDO_INICIAL", precision = 15, scale = 2 , nullable = false)
	 private BigDecimal saldoInicial = new BigDecimal("0.00");	
	 
	 @NotBlank(message = "El estado no puede estar en blanco")
	 @Column(name = "ESTADO", nullable = false, unique = false)    
	 private String estado = "TRUE";	
	 
     @OneToMany(mappedBy = "cuenta", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	 private List<Movimiento> movimientos;
	 
	 public boolean isCuentaActiva () {
	  	return estado.equalsIgnoreCase("TRUE");
	 }
}

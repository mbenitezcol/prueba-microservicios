package com.prueba.cuentaservice.entity;

import java.math.*;
import java.time.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "MOVIMIENTO")
@Data
public class Movimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MOVIMIENTO_ID", nullable = false, unique = true)    
	private Long movimientoId; 
	   
	@ManyToOne 
	@JoinColumn(name="CUENTA_ID", referencedColumnName = "CUENTA_ID",  nullable=false)
	private Cuenta cuenta;
	   
	@Column(name = "FECHA", nullable = false)
	private LocalDateTime fecha;	   
	  
	@NotBlank(message = "El tipo de movimiento no puede estar en blanco")
	@Column(name = "TIPO_MOVIMIENTO", nullable = false)
	private String tipoMovimiento = "DEBITO";
	   
	@NotNull(message = "El valor no puede ser nulo")
	@DecimalMin(value = "0.01", inclusive = false, message = "El valor debe ser mayor que 0")
	@Column(name = "VALOR", precision = 15, scale = 2, nullable = false)
	private BigDecimal valor = new BigDecimal("0.00");	
		
}

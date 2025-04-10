package com.ejercicio.cuentamovimiento.domain.model;

import java.time.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Movimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIMIENTO_ID", nullable = false, unique = true) 	
    private Long movimientoId;
  
    @ManyToOne
    @JoinColumn(name = "NUMERO_CUENTA", referencedColumnName = "NUMERO_CUENTA",  nullable=false, unique=false)    
    @Column(name = "NUMERO_CUENTA", nullable = false, unique = false)    
    private Cuenta cuenta;

    
    @Column(name = "FECHA", nullable = false, unique = false)    
    private LocalDate fecha;
    
    @Column(name = "TIPO_MOVIMIENTO", nullable = false, unique = false)    
    private String tipoMovimiento;
    
    @Column(name = "VALOR", nullable = false, unique = false)       
    private Double valor;
    
    @Column(name = "SALDO", nullable = false, unique = false)    
    private Double saldo;

}

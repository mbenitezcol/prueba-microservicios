package com.prueba.cuentaservice.exception;

public class CuentaExistenteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CuentaExistenteException(String msg) {
        super(msg);
    }
}
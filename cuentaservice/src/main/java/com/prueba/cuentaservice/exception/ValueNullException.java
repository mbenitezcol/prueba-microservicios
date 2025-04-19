package com.prueba.cuentaservice.exception;

public class ValueNullException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ValueNullException(String msg) {
        super(msg);
    }
}
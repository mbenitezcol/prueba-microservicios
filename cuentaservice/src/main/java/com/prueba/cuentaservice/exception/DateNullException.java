package com.prueba.cuentaservice.exception;

public class DateNullException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DateNullException(String msg) {
        super(msg);
    }
}
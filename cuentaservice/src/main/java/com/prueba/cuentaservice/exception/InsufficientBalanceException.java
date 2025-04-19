package com.prueba.cuentaservice.exception;

public class InsufficientBalanceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException(String msg) {
        super(msg);
    }
}
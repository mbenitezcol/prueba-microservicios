package com.prueba.cuentaservice.exception;

public class AccountInactiveException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AccountInactiveException(String msg) {
        super(msg);
    }
}
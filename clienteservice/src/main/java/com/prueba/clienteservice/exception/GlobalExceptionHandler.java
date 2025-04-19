package com.prueba.clienteservice.exception;

import java.time.*;
import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    /* 
     * método captura cualquier excepción genérica (java.lang.Exception) no controlada específicamente
     */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericException(Exception ex) {
	    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno", ex.getMessage(), ex);
	}

    /*
     *  método que captura una excepción personalizada llamada ResourceNotFoundException
     */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
	    return buildResponse(HttpStatus.NOT_FOUND, "Recurso no encontrado", ex.getMessage(), ex);
	}

    /*
     * métodp reutilizado por todos los handlers y construye una respuesta de esta forma
     * 
     * {
          "timestamp": "2025-04-15T20:45:12.134",
          "status": 500,
          "error": "Error interno",
          "message": "NullPointerException",
          "location": "com.prueba.clienteservice.fetchSchoolById (línea 27)"
       }
     * 
     * 
     */
    private ResponseEntity<Object> buildResponse(HttpStatus status, String error, String message, Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", error);
        response.put("message", message);

        // Obtener el primer elemento del stack trace (donde ocurrió la excepción)
        if (ex.getStackTrace().length > 0) {
            StackTraceElement element = ex.getStackTrace()[0];
            response.put("location", element.getClassName() + "." + element.getMethodName() + 
                " (línea " + element.getLineNumber() + ")");
        }

        return new ResponseEntity<>(response, status);
    }
    
}    

package org.jcr.excepciones;

// Extendemos Exception y no RuntimeException para que el compilador
// obligue a quien programa una cita a manejar el rechazo.
public class CitaException extends Exception {

    public CitaException(String message) {
        super(message);
    }

    public CitaException(String message, Throwable cause) {
        super(message, cause);
    }
}

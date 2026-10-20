package mx.edu.utez.proyectoU2_A8.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
            super(message);
        }
    }


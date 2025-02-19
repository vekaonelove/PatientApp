package com.orion.patient.util.exception;

public class PatientApplicationException extends RuntimeException {

    public PatientApplicationException(String message) {
        super(message);
    }

    public PatientApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatientApplicationException(Throwable cause) {
        super(cause);
    }
}

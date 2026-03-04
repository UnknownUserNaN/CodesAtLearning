package org.example.exception.customizedExceptions;

public class ClazzNotEmptyException extends RuntimeException {
    public ClazzNotEmptyException(String message) {
        super(message);
    }

    public ClazzNotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}


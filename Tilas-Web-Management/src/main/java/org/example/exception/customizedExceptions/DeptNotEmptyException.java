package org.example.exception.customizedExceptions;

public class DeptNotEmptyException extends RuntimeException {
    public DeptNotEmptyException(String message) {
        super(message);
    }

    public DeptNotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}


package com.proyecto.jirabeta.exceptions;

public class RollbackException extends DAOException{
    public RollbackException() {
    }

    public RollbackException(String message, Throwable e) {
        super(message, e);
    }

    public RollbackException(String message) {
        super(message);
    }
}

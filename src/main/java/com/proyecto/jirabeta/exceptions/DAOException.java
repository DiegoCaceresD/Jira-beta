package com.proyecto.jirabeta.exceptions;

public class DAOException extends Exception{
    public DAOException() {
    }
    public DAOException(String message, Throwable e){super(message, e);};
}

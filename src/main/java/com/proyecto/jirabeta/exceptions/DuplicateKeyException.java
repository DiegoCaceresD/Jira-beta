package com.proyecto.jirabeta.exceptions;

public class DuplicateKeyException extends Exception {
    public DuplicateKeyException (){};

    public DuplicateKeyException (String s) { super(s); }
}

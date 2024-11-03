package com.proyecto.jirabeta.exceptions;

public class EntityNotFoundExcepcion extends Exception{

    public EntityNotFoundExcepcion(String entity, long id){ super(entity + " con ID " + id + " no fue encontrado.");}
    public EntityNotFoundExcepcion (String entity, String dni){super(entity + " con Dni " + dni + " no fue encontrado.");}
}

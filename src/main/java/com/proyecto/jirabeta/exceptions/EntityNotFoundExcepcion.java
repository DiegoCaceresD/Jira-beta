package com.proyecto.jirabeta.exceptions;

public class EntityNotFoundExcepcion extends Exception{

    public EntityNotFoundExcepcion(String entity, Integer id){ super(entity + " con ID " + id + " no fue encontrado.");}
    public EntityNotFoundExcepcion (String entity, String key){super("Entidad " + entity + " no encontrada - Key: " + key );}
    public EntityNotFoundExcepcion (String string){super(string);}
}

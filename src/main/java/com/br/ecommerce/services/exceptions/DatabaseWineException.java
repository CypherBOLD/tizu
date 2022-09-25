package com.br.ecommerce.services.exceptions;

public class DatabaseWineException extends RuntimeException{
    public static final long serialVersionUID = 1L;

    public DatabaseWineException(String msg){
        super(msg);
    }

}

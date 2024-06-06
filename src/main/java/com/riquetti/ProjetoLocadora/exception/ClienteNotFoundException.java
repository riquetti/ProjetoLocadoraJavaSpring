package com.riquetti.ProjetoLocadora.exception;

public class ClienteNotFoundException  extends RuntimeException {
    public ClienteNotFoundException(Long id) {
        super("Cliente with id " + id + " not found.");
    }
}

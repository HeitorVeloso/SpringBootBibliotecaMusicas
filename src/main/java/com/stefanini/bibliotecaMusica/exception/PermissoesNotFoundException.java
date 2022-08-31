package com.stefanini.bibliotecaMusica.exception;

public class PermissoesNotFoundException extends Exception{
    public PermissoesNotFoundException(Long id) {
       super("Permissão não encontrada com id:" +id);
    }
}

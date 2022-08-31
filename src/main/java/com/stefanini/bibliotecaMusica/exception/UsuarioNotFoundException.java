package com.stefanini.bibliotecaMusica.exception;

public class UsuarioNotFoundException extends Exception{
    public UsuarioNotFoundException(Long id) {
       super("Usuario não encontrado com id:" +id);
    }
}

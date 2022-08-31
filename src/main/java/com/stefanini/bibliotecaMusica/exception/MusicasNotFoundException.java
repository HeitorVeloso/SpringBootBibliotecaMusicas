package com.stefanini.bibliotecaMusica.exception;

public class MusicasNotFoundException extends Exception{
    public MusicasNotFoundException(Long id) {
       super("Musica não encontrada com id:" +id);
    }
}

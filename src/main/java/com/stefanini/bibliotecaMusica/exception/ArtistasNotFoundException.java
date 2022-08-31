package com.stefanini.bibliotecaMusica.exception;

public class ArtistasNotFoundException extends Exception{
    public ArtistasNotFoundException(Long id) {
       super("Artista não encontrado com id:" +id);
    }
}

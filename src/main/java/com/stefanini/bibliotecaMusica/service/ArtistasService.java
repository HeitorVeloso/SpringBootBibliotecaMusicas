 package com.stefanini.bibliotecaMusica.service;


import com.stefanini.bibliotecaMusica.exception.ArtistasNotFoundException;
import com.stefanini.bibliotecaMusica.model.Artistas;
import com.stefanini.bibliotecaMusica.repository.ArtistasRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ArtistasService {

    private final ArtistasRepository artistasRepository;

    public ArtistasService(ArtistasRepository artistasRepository) {
        this.artistasRepository = artistasRepository;
    }

    public List<Artistas> findAllArtistas(){
        return artistasRepository.findAll();
    }

    public Artistas findById(Long id) throws ArtistasNotFoundException {
        return artistasRepository.findById(id)
                .orElseThrow(()-> new ArtistasNotFoundException(id));
    }

    public Artistas save(Artistas artistas){
        return artistasRepository.save(artistas);
    }
    
    public void delete(Artistas artistas){
        artistasRepository.delete(artistas);
    }

}

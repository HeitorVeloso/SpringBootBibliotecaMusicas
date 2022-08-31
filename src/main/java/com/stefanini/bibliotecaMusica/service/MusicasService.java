 package com.stefanini.bibliotecaMusica.service;


import com.stefanini.bibliotecaMusica.exception.MusicasNotFoundException;
import com.stefanini.bibliotecaMusica.model.Musicas;
import com.stefanini.bibliotecaMusica.repository.MusicasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service

public class MusicasService {

    private final MusicasRepository musicasRepository;

    public MusicasService(MusicasRepository musicasRepository) {
        this.musicasRepository = musicasRepository;
    }

    public List<Musicas> findAllMusicas(){
        return musicasRepository.findAll();
    }
    
    public Page<Musicas> findAllMusicas(Pageable paginacao){
        return musicasRepository.findAll(paginacao);
    }

    public Optional<Musicas> findById(Long id) throws MusicasNotFoundException {
        return musicasRepository.findById(id);
    }

    public Musicas save(Musicas musicas){
        return musicasRepository.save(musicas);
    }
    
    public void delete(Musicas musicas){
        musicasRepository.delete(musicas);
    }

}

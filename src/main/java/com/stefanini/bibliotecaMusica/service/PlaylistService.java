package com.stefanini.bibliotecaMusica.service;


import com.stefanini.bibliotecaMusica.exception.PlaylistNotFoundException;
import com.stefanini.bibliotecaMusica.model.Playlist;
import com.stefanini.bibliotecaMusica.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service

public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public List<Playlist> findAllPlaylists(){
        return playlistRepository.findAll();
    }
    
    public Page<Playlist> findAllPlaylists(Pageable paginacao){
        return playlistRepository.findAll(paginacao);
    }

    public Optional<Playlist> findById(Long id) throws PlaylistNotFoundException {
        return playlistRepository.findById(id);                
    }

    public Playlist save(Playlist playlist){
        return playlistRepository.save(playlist);
    }
    
    public void delete(Playlist playlist){
        playlistRepository.delete(playlist);
    }
    
    /*public List<Playlist> findAllByUsuario(String usuario){
        return playlistRepository.findAllByUsuario(usuario);
    }*/

}

package com.stefanini.bibliotecaMusica.mapper;

import com.stefanini.bibliotecaMusica.dto.PlaylistDTO;
import com.stefanini.bibliotecaMusica.exception.PlaylistNotFoundException;
import com.stefanini.bibliotecaMusica.model.Playlist;
import com.stefanini.bibliotecaMusica.service.MusicasService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PlaylistDTOService {

    //@Autowired
    public PlaylistDTOService() {        
        
    }
    
    public Playlist mapPlaylist(PlaylistDTO playlistDTO) throws PlaylistNotFoundException {
        Playlist newPlaylist = new Playlist(playlistDTO);
        return newPlaylist;
    }
    
    public PlaylistDTO mapPlaylistDTO(Playlist playlist) throws PlaylistNotFoundException {
        PlaylistDTO newPlaylistDTO = new PlaylistDTO(playlist);
        return newPlaylistDTO;
    }
    
    public List<PlaylistDTO> mapTodosPlaylist(List<Playlist> listaPlaylist) {

        List<PlaylistDTO> listaPlaylistDTO = PlaylistDTO.converter(listaPlaylist);
        return listaPlaylistDTO;
        /*List<PlaylistDTO> listaPlaylistDTO = new ArrayList();
        for (Playlist playlist : listaPlaylist) {
            PlaylistDTO playlistDTO = new PlaylistDTO(playlist);
            listaPlaylistDTO.add(playlistDTO);
        }
        
        return listaPlaylistDTO;*/
    }
    public Page<PlaylistDTO> mapTodosPlaylist(Page<Playlist> paginaPlaylist) {
        Page<PlaylistDTO> listaPlaylistDTO = PlaylistDTO.converter(paginaPlaylist);
        return listaPlaylistDTO;        
    }
  
}

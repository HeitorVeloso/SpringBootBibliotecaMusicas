package com.stefanini.bibliotecaMusica.controller;

import com.stefanini.bibliotecaMusica.dto.PlaylistDTO;
import com.stefanini.bibliotecaMusica.exception.MusicasNotFoundException;
import com.stefanini.bibliotecaMusica.exception.PlaylistNotFoundException;
import com.stefanini.bibliotecaMusica.mapper.PlaylistDTOService;
import com.stefanini.bibliotecaMusica.model.Musicas;
import com.stefanini.bibliotecaMusica.model.Playlist;
import com.stefanini.bibliotecaMusica.model.Usuario;
import com.stefanini.bibliotecaMusica.service.MusicasService;
import com.stefanini.bibliotecaMusica.service.PlaylistService;
import com.stefanini.bibliotecaMusica.service.UsuarioService;
import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

//@Controller
@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;
    private final PlaylistDTOService playlistDTOService;
    private final UsuarioService usuarioService;
    private final MusicasService musicasService;

    @Autowired
    public PlaylistController(PlaylistService playlistService, PlaylistDTOService playlistDTOService,UsuarioService usuarioService,MusicasService musicasService) {
        this.playlistService = playlistService;
        this.playlistDTOService = playlistDTOService;
        this.usuarioService = usuarioService;
        this.musicasService = musicasService;
    }   
    
    @GetMapping()
    //@Cacheable(value = "listaDePlaylist")
    public Page<PlaylistDTO> home(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable paginacao) throws PlaylistNotFoundException {
        //Pageable paginacao = PageRequest.of(numeroDaPagina, quantidadePorPagina);
        
        Page<PlaylistDTO> pagePlaylistDTO;        
        pagePlaylistDTO = playlistDTOService.mapTodosPlaylist(playlistService.findAllPlaylists(paginacao));    	
        return pagePlaylistDTO;
        
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDTO> detalhar(@PathVariable Long id) throws PlaylistNotFoundException {
		Optional<Playlist> playlistOptional = playlistService.findById(id);
		if (playlistOptional.isPresent()) {
			return ResponseEntity.ok(new PlaylistDTO(playlistOptional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}

    @PostMapping()
    //@CacheEvict(value = "listaDePlaylist", allEntries = true)
    public ResponseEntity<PlaylistDTO> adicionarPlaylist(@RequestBody @Valid PlaylistDTO playlistDTO, UriComponentsBuilder uriBuilder) throws PlaylistNotFoundException, MusicasNotFoundException{
        /*if(playlistDTO.getListaMusicas()!=null){
            for(Musicas musicas : playlistDTO.getListaMusicas()){
                if(musicas.onlyId()){
                    musicas = musicasService.findById(musicas.getId());
                }                    
            }
        }*/
        Playlist newPlaylist = playlistDTOService.mapPlaylist(playlistDTO);
        newPlaylist = playlistService.save(newPlaylist);
        URI uri = uriBuilder.path("/playlist/{id}").buildAndExpand(newPlaylist.getId()).toUri();
        return ResponseEntity.created(uri).body(new PlaylistDTO(newPlaylist));
    } 

    @PutMapping("/{id}")
    //@CacheEvict(value = "listaDePlaylist", allEntries = true)
    public ResponseEntity<PlaylistDTO> alterarPlaylist(@PathVariable Long id, @RequestBody PlaylistDTO playlistDTO) throws PlaylistNotFoundException{
        Optional<Playlist> playlistOptional = playlistService.findById(id);
        if (playlistOptional.isPresent()) {
            Playlist playlist = playlistDTOService.mapPlaylist(playlistDTO);
            playlist.setId(id);
            playlist = playlistService.save(playlist);

            return ResponseEntity.ok(new PlaylistDTO(playlist));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    } 
    
    @DeleteMapping("/{id}")
    //@CacheEvict(value = "listaDePlaylist", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) throws PlaylistNotFoundException {
        Optional<Playlist> playlistOptional = playlistService.findById(id);
        Playlist playlist;        
        if (playlistOptional.isPresent()) {
            playlist = playlistOptional.get();
            playlistService.delete(playlist);
            return ResponseEntity.ok().build();
			//return ResponseEntity.ok(new PlaylistDTO(playlist.get()));
	}
        else{
            return ResponseEntity.notFound().build();
        }
    }    
    
}

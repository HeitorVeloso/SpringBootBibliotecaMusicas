package com.stefanini.bibliotecaMusica.controller;

import com.stefanini.bibliotecaMusica.dto.MusicasDTO;
import com.stefanini.bibliotecaMusica.exception.MusicasNotFoundException;
import com.stefanini.bibliotecaMusica.mapper.MusicasDTOService;
import com.stefanini.bibliotecaMusica.model.Musicas;
import com.stefanini.bibliotecaMusica.service.MusicasService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

//@Controller
@RestController
@RequestMapping("/musicas")
public class MusicasController {

    private final MusicasService musicasService;
    private final MusicasDTOService musicasDTOService;

    @Autowired
    public MusicasController(MusicasService musicasService, MusicasDTOService musicasDTOService) {
        this.musicasService = musicasService;
        this.musicasDTOService = musicasDTOService;
    }    
    
    @GetMapping()
    @Cacheable(value = "listaDeMusicas")
    public Page<MusicasDTO> home(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable paginacao) throws MusicasNotFoundException {
        //Pageable paginacao = PageRequest.of(numeroDaPagina, quantidadePorPagina);
        
        Page<MusicasDTO> pageMusicasDTO;        
        pageMusicasDTO = musicasDTOService.mapTodosMusicas(musicasService.findAllMusicas(paginacao));    	
        return pageMusicasDTO;
        
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MusicasDTO> detalhar(@PathVariable Long id) throws MusicasNotFoundException {
		Optional<Musicas> musicasOptional = musicasService.findById(id);
		if (musicasOptional.isPresent()) {
			return ResponseEntity.ok(new MusicasDTO(musicasOptional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}

    @PostMapping()
    @CacheEvict(value = "listaDeMusicas", allEntries = true)
    public ResponseEntity<MusicasDTO> adicionarMusicas(@RequestBody @Valid MusicasDTO musicasDTO, UriComponentsBuilder uriBuilder) throws MusicasNotFoundException, MusicasNotFoundException{
        /*if(musicasDTO.getListaMusicas()!=null){
            for(Musicas musicas : musicasDTO.getListaMusicas()){
                if(musicas.onlyId()){
                    musicas = musicasService.findById(musicas.getId());
                }                    
            }
        }*/
        Musicas newMusicas = musicasDTOService.mapMusicas(musicasDTO);
        newMusicas = musicasService.save(newMusicas);
        URI uri = uriBuilder.path("/musicas/{id}").buildAndExpand(newMusicas.getId()).toUri();
        return ResponseEntity.created(uri).body(new MusicasDTO(newMusicas));
    } 

    @PutMapping("/{id}")
    @CacheEvict(value = "listaDeMusicas", allEntries = true)
    public ResponseEntity<MusicasDTO> alterarMusicas(@PathVariable Long id, @RequestBody MusicasDTO musicasDTO) throws MusicasNotFoundException{
        Optional<Musicas> musicasOptional = musicasService.findById(id);
        if (musicasOptional.isPresent()) {
            Musicas musicas = musicasDTOService.mapMusicas(musicasDTO);
            musicas.setId(id);
            musicas = musicasService.save(musicas);

            return ResponseEntity.ok(new MusicasDTO(musicas));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    } 
    
    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeMusicas", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) throws MusicasNotFoundException {
        Optional<Musicas> musicasOptional = musicasService.findById(id);
        Musicas musicas;        
        if (musicasOptional.isPresent()) {
            musicas = musicasOptional.get();
            musicasService.delete(musicas);
            return ResponseEntity.ok().build();
			//return ResponseEntity.ok(new MusicasDTO(musicas.get()));
	}
        else{
            return ResponseEntity.notFound().build();
        }
    } 
}

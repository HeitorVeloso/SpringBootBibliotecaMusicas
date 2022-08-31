package com.stefanini.bibliotecaMusica.controller;

import com.stefanini.bibliotecaMusica.dto.ArtistasDTO;
import com.stefanini.bibliotecaMusica.exception.ArtistasNotFoundException;
import com.stefanini.bibliotecaMusica.mapper.ArtistasDTOService;
import com.stefanini.bibliotecaMusica.model.Artistas;
import com.stefanini.bibliotecaMusica.model.Musicas;
import com.stefanini.bibliotecaMusica.service.ArtistasService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

//@Controller
@RestController
//@RequestMapping("/artistas")
public class ArtistasController {

    private final ArtistasService artistasService;
    private final ArtistasDTOService artistasDTOService;

    @Autowired
    public ArtistasController(ArtistasService artistasService, ArtistasDTOService artistasDTOService) {
        this.artistasService = artistasService;
        this.artistasDTOService = artistasDTOService;
    }   
    
    @GetMapping("/artistas")
    public List<ArtistasDTO> home(Model model) {        
    	List<ArtistasDTO> listaArtistasDTO = artistasDTOService.mapTodosArtistas(artistasService.findAllArtistas());    	
        model.addAttribute("listaArtistas", listaArtistasDTO);
        return listaArtistasDTO;
        //return "artistas";
    }
    
    @PostMapping("/artistas")
    @ResponseStatus
    public Artistas adicionarArtistas(@RequestBody ArtistasDTO artistasDTO) throws ArtistasNotFoundException{
        Artistas newArtistas = artistasDTOService.mapArtistas(artistasDTO);
        return artistasService.save(newArtistas);         
    } 

    @PutMapping("/artistas/{id}")
    @ResponseStatus
    public Artistas alterarArtistas(@PathVariable Long id, @RequestBody ArtistasDTO artistasDTO) throws ArtistasNotFoundException{
        Artistas artistas = artistasDTOService.mapArtistas(artistasDTO);
        artistas.setId(id);
        return artistasService.save(artistas);         
    } 
    
    @DeleteMapping("artistas/{id}")
    public String remover(@PathVariable Long id) {
        Artistas artistas;

        try {
            artistas = artistasService.findById(id);
            artistasService.delete(artistas);
        } catch (ArtistasNotFoundException ex) {
            Logger.getLogger(ArtistasController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "deletado com sucesso";
    }
    
    /*@GetMapping
	@Cacheable(value = "listaDeArtistas")
	public Page<ArtistasDTO> lista(@RequestParam(required = false) String nomeCurso, 
			@PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		
		if (nomeCurso == null) {
			Page<Artistas> artistass = artistasService.findAll(paginacao);
			return ArtistasDTO.converter(artistass);
		} else {
			Page<Artistas> artistass = artistasService.findByCursoNome(nomeCurso, paginacao);
			return ArtistasDTO.converter(artistass);
		}
	}*/
	
	/*@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeArtistas", allEntries = true)
	public ResponseEntity<ArtistasDTO> cadastrar(@RequestBody @Valid ArtistasForm form, UriComponentsBuilder uriBuilder) {
		Artistas artistas = form.converter(cursoRepository);
		artistasService.save(artistas);
		
		URI uri = uriBuilder.path("/artistass/{id}").buildAndExpand(artistas.getId()).toUri();
		return ResponseEntity.created(uri).body(new ArtistasDTO(artistas));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoArtistasDTO> detalhar(@PathVariable Long id) {
		Optional<Artistas> artistas = artistasService.findById(id);
		if (artistas.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoArtistasDTO(artistas.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeArtistas", allEntries = true)
	public ResponseEntity<ArtistasDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoArtistasForm form) {
		Optional<Artistas> optional = artistasService.findById(id);
		if (optional.isPresent()) {
			Artistas artistas = form.atualizar(id, artistasService);
			return ResponseEntity.ok(new ArtistasDTO(artistas));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeArtistas", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Artistas> optional = artistasService.findById(id);
		if (optional.isPresent()) {
			artistasService.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
    
    
    
    
    @GetMapping("/formArtistas")
    public String artistasForm(ArtistasDTO artistasDTO) {
        return "addArtistasForm";
    }

    // Adiciona novo artistas
    @PostMapping("/addArtistas")
    public String novo(@Valid ArtistasDTO artistasDTO, BindingResult result) throws ArtistasNotFoundException {

        if (result.hasFieldErrors()) {
            return "redirect:/formArtistas";
        }

        Artistas newArtistas = artistasDTOService.mapArtistas(artistasDTO);
        artistasService.save(newArtistas);
        

        return "redirect:/artistas";
    }

    // Acessa o formulario de edição
    @GetMapping("formArtistas/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") Long id) {
        ArtistasDTO artistasDTO;
        Artistas artistas;
        try {
            artistas = artistasService.findById(id);
            artistasDTO=new ArtistasDTO(artistas);
            model.addAttribute("artistasDTO", artistasDTO);
        } catch (ArtistasNotFoundException ex) {
            Logger.getLogger(ArtistasController.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return "atualizaArtistas";
    }

    // Atualiza artistas
    @PostMapping("updateArtistas/{id}")
    public String alterarArtistas(@Valid ArtistasDTO artistasDTO, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return "redirect:/formArtistas";
        }
        
        Artistas newArtistas;
        try {
            newArtistas = artistasDTOService.mapArtistas(artistasDTO);
            newArtistas.setId(id);
            artistasService.save(newArtistas);
        } catch (ArtistasNotFoundException ex) {
            Logger.getLogger(ArtistasController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return "redirect:/artistas";
    }

    @GetMapping("deleteArtistas/{id}")
    @CacheEvict(value = "artistas", allEntries = true)
    public String delete(@PathVariable(name = "id") Long id, Model model) {

        Artistas artistas;
        
        try {
            artistas = artistasService.findById(id);
            artistasService.delete(artistas);
        } catch (ArtistasNotFoundException ex) {
            Logger.getLogger(ArtistasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/artistas";
    }  */  
}

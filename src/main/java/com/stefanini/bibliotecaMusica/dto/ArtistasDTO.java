package com.stefanini.bibliotecaMusica.dto;

import com.stefanini.bibliotecaMusica.model.Artistas;
import com.stefanini.bibliotecaMusica.model.Artistas;
import com.stefanini.bibliotecaMusica.model.Artistas;
import com.stefanini.bibliotecaMusica.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;

public class ArtistasDTO {

    private Long id;
    
    private String nome;
    
    
    public ArtistasDTO() {
    }

    public ArtistasDTO(String nome) {
        this.nome = nome;        
    }
    
    public ArtistasDTO(Long id,String nome) {
        this.id=id;
        this.nome = nome;       
    }
    
    public ArtistasDTO(Artistas artistas) {
        this.id = artistas.getId();
        this.nome = artistas.getNome();
    }
    
    public List<Artistas> transformarListaArtistas(List<ArtistasDTO> listaArtistasDTO){
        List<Artistas> listaArtistas = new ArrayList();
        for(int i = 0; i < listaArtistasDTO.size() ;i++){
            Artistas artistas = new Artistas(listaArtistasDTO.get(i));
            listaArtistas.add(artistas);
        }
        return listaArtistas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public static Page<ArtistasDTO> converter(Page<Artistas> artistas) {
		return artistas.map(ArtistasDTO::new);
    }
}

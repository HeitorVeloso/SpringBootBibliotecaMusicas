package com.stefanini.bibliotecaMusica.dto;

import com.stefanini.bibliotecaMusica.model.Musicas;
import com.stefanini.bibliotecaMusica.model.Playlist;
import com.stefanini.bibliotecaMusica.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

@Data
public class PlaylistDTO {

    private Long id;
    
    @NotNull @NotEmpty @Length(min = 5, max = 255)
    private String nome;
    
    private List<Musicas> listaMusicas;

    public PlaylistDTO() {
    }

    public PlaylistDTO(String nome, List<Musicas> listaMusicas) {
        this.nome = nome;
        this.listaMusicas = listaMusicas;        
    }
    
    public PlaylistDTO(Long id,String nome, List<Musicas> listaMusicas) {
        this.id=id;
        this.nome = nome;
        this.listaMusicas = listaMusicas;       
    }
    
    public PlaylistDTO(Playlist playlist) {
        this.id=playlist.getId();
        this.nome = playlist.getNome();
        if(playlist.getListaMusicas()==null){
            playlist.setListaMusicas(new ArrayList());
        }
        this.listaMusicas = playlist.getListaMusicas();       
    }    
    
    public boolean onlyId(){
         return nome.isBlank();
    }
    
    public static List<PlaylistDTO> converter(List<Playlist> playlist){
        return playlist.stream().map(PlaylistDTO::new).collect(Collectors.toList());
    }
    
    public static Page<PlaylistDTO> converter(Page<Playlist> playlist){
        return playlist.map(PlaylistDTO::new);
    }
        
}

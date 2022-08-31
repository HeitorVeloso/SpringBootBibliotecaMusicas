package com.stefanini.bibliotecaMusica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stefanini.bibliotecaMusica.dto.PlaylistDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@Data
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    @NotNull
    
    private String nome; 
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Musicas> listaMusicas;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Usuario usuario;

    public Playlist(String nome, List<Musicas> listaMusicas) {
        this.nome=nome;
        this.listaMusicas=listaMusicas;
    }

    public Playlist(PlaylistDTO playlistDTO) {
        this.id = playlistDTO.getId();
        this.nome = playlistDTO.getNome();
        if(playlistDTO.getListaMusicas()==null){
            playlistDTO.setListaMusicas(new ArrayList());
        }
        this.listaMusicas=playlistDTO.getListaMusicas();
    }
    public boolean onlyId() {
        if (!nome.isBlank()) {
            return false;
        } else {
            return id == null;
        }
    }
    @Override
    public String toString() {
        return nome;
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

    public List<Musicas> getListaMusicas() {
        return listaMusicas;
    }

    public void setListaMusicas(List<Musicas> listaMusicas) {
        this.listaMusicas = listaMusicas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
}

package com.stefanini.bibliotecaMusica.dto;

import com.stefanini.bibliotecaMusica.model.Artistas;
import com.stefanini.bibliotecaMusica.model.Musicas;
import com.stefanini.bibliotecaMusica.model.Musicas;
import com.stefanini.bibliotecaMusica.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

public class MusicasDTO {

    private Long id;
    
    private String caminhoImagem;
    
    private String caminhoMusica;
    
    private String nomeMusica;
    
    private List<Artistas> listaArtistas;
    
    public MusicasDTO() {
    }

    public MusicasDTO(String caminhoImagem, String caminhoMusica, String nomeMusica, List<Artistas> listaArtistas) {
        this.caminhoImagem = caminhoImagem;
        this.caminhoMusica = caminhoMusica;
        this.nomeMusica = nomeMusica;
        this.listaArtistas = listaArtistas;        
    }
    
    public MusicasDTO(Long id,String caminhoImagem, String caminhoMusica, String nomeMusica, List<Artistas> listaArtistas) {
        this.id=id;
        this.caminhoImagem = caminhoImagem;
        this.caminhoMusica = caminhoMusica;
        this.nomeMusica = nomeMusica;
        this.listaArtistas = listaArtistas;       
    }
    
    public MusicasDTO(Musicas musicas) {
        this.id=musicas.getId();
        this.caminhoImagem = musicas.getCaminhoImagem();
        this.caminhoMusica = musicas.getCaminhoMusica();
        this.nomeMusica = musicas.getNomeMusica();
        if(musicas.getListaArtistas()==null){
            musicas.setListaArtistas(new ArrayList());
        }
        this.listaArtistas = musicas.getListaArtistas();       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public String getCaminhoMusica() {
        return caminhoMusica;
    }

    public void setCaminhoMusica(String caminhoMusica) {
        this.caminhoMusica = caminhoMusica;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public List<Artistas> getListaArtistas() {
        return listaArtistas;
    }

    public void setListaArtistas(List<Artistas> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }
    
    public static List<MusicasDTO> converter(List<Musicas> musicas){
        return musicas.stream().map(MusicasDTO::new).collect(Collectors.toList());
    }
    
    public static Page<MusicasDTO> converter(Page<Musicas> musicas){
        return musicas.map(MusicasDTO::new);
    }
}

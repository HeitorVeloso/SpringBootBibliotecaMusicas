/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stefanini.bibliotecaMusica.model;


import com.stefanini.bibliotecaMusica.dto.MusicasDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@XmlRootElement
public class Musicas implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String caminhoImagem;
    private String caminhoMusica;
    private String nomeMusica;
    @OneToMany
    private List<Artistas> listaArtistas;

    public Musicas(String caminhoImagem, String caminhoMusica, String nomeMusica) {
        this.caminhoImagem = caminhoImagem;
        this.caminhoMusica = caminhoMusica;
        this.nomeMusica = nomeMusica;
    }

    public Musicas(MusicasDTO musicasDTO){
        this.id=musicasDTO.getId();
        this.caminhoImagem = musicasDTO.getCaminhoImagem();
        this.caminhoMusica = musicasDTO.getCaminhoMusica();
        this.nomeMusica = musicasDTO.getNomeMusica();
        if(musicasDTO.getListaArtistas()==null){
            musicasDTO.setListaArtistas(new ArrayList());
        }
        this.listaArtistas = musicasDTO.getListaArtistas(); 
    }

    public boolean onlyId(){
        if(caminhoImagem!=null&&!caminhoImagem.isBlank()){
            return false;
        }else if(caminhoMusica!=null&&!caminhoMusica.isBlank()){
            return false;
        }
        else if(nomeMusica!=null&&!nomeMusica.isBlank()){
            return false;
        }
        else return id==null;
    }   
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Musicas imagemMusica = (Musicas) o;
        return id == imagemMusica.id
                && Objects.equals(caminhoImagem, imagemMusica.caminhoImagem)
                && Objects.equals(caminhoMusica, imagemMusica.caminhoMusica)
                && Objects.equals(nomeMusica, imagemMusica.nomeMusica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, caminhoImagem, caminhoMusica,nomeMusica);
    }
    
    @Override
    public String toString() {
        return caminhoImagem + ' ' + caminhoMusica;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stefanini.bibliotecaMusica.model;


import com.stefanini.bibliotecaMusica.dto.ArtistasDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@XmlRootElement
public class Artistas implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;    
    
    public Artistas(String nome) {
        this.nome = nome;
    }

    public Artistas(ArtistasDTO artistasDTO){
        this.id = artistasDTO.getId();
        this.nome = artistasDTO.getNome();
    }
    
    public List<ArtistasDTO> transformarListaArtistasDTO(List<Artistas> listaArtistas){
        List<ArtistasDTO> listaArtistasDTO = new ArrayList();
        for(int i = 0; i < listaArtistas.size() ;i++){
            ArtistasDTO artistasDTO = new ArtistasDTO(listaArtistas.get(i));
            listaArtistasDTO.add(artistasDTO);
        }
        return listaArtistasDTO;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Artistas artistas = (Artistas) o;
        return id == artistas.id
                && Objects.equals(nome, artistas.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
}

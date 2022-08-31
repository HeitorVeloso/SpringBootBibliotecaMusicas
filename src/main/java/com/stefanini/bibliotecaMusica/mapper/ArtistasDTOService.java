package com.stefanini.bibliotecaMusica.mapper;

import com.stefanini.bibliotecaMusica.dto.ArtistasDTO;
import com.stefanini.bibliotecaMusica.exception.ArtistasNotFoundException;
import com.stefanini.bibliotecaMusica.model.Artistas;
import com.stefanini.bibliotecaMusica.service.ArtistasService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistasDTOService {

    @Autowired
    public ArtistasDTOService() {        
        
    }
    
    public Artistas mapArtistas(ArtistasDTO artistasDTO) throws ArtistasNotFoundException {
   

        Artistas newArtistas = new Artistas(artistasDTO);
        return newArtistas;
    }
    
    public List<ArtistasDTO> mapTodosArtistas(List<Artistas> listaArtistas) {

        List<ArtistasDTO> listaArtistasDTO = new ArrayList();
        for (Artistas artistas : listaArtistas) {
            ArtistasDTO artistasDTO = new ArtistasDTO(artistas);
            listaArtistasDTO.add(artistasDTO);
        }
        
        return listaArtistasDTO;
    }

    
}

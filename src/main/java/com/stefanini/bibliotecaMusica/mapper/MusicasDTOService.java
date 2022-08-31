package com.stefanini.bibliotecaMusica.mapper;

import com.stefanini.bibliotecaMusica.dto.MusicasDTO;
import com.stefanini.bibliotecaMusica.exception.MusicasNotFoundException;
import com.stefanini.bibliotecaMusica.model.Musicas;
import com.stefanini.bibliotecaMusica.service.MusicasService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class MusicasDTOService {

    @Autowired
    public MusicasDTOService() {        
        
    }
    
    public Musicas mapMusicas(MusicasDTO musicasDTO) throws MusicasNotFoundException {
   

        Musicas newMusicas = new Musicas(musicasDTO);
        return newMusicas;
    }
    
    public List<MusicasDTO> mapTodosMusicas(List<Musicas> listaMusicas) {

        List<MusicasDTO> listaMusicasDTO = MusicasDTO.converter(listaMusicas);
        return listaMusicasDTO;
        /*List<MusicasDTO> listaMusicasDTO = new ArrayList();
        for (Musicas musicas : listaMusicas) {
            MusicasDTO musicasDTO = new MusicasDTO(musicas);
            listaMusicasDTO.add(musicasDTO);
        }
        
        return listaMusicasDTO;*/
    }
    
    public Page<MusicasDTO> mapTodosMusicas(Page<Musicas> paginaMusicas) {
        Page<MusicasDTO> listaMusicasDTO = MusicasDTO.converter(paginaMusicas);
        return listaMusicasDTO;        
    }

    
}

package com.stefanini.bibliotecaMusica.repository;

import com.stefanini.bibliotecaMusica.model.Musicas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicasRepository extends JpaRepository<Musicas, Long> {
    
}

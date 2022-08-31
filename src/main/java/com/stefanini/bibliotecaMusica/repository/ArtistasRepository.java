package com.stefanini.bibliotecaMusica.repository;

import com.stefanini.bibliotecaMusica.model.Artistas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistasRepository extends JpaRepository<Artistas, Long> {
}

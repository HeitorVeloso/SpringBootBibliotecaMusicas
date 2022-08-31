package com.stefanini.bibliotecaMusica.repository;

import com.stefanini.bibliotecaMusica.model.Playlist;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    
    /*@Query("select p from Playlist p join p.usuario u where u.usuario = :usuario")
    List<Playlist> findAllByUsuario(@Param("usuario")String usuario);*/

    /*@Query("select p from Playlist p join p.usuario u where u.usuario = :usuario and p.status = :status")
    List<Playlist> findByStatusEUsuario(@Param("status")StatusPedido status, @Param("usuario")String username);*/
}

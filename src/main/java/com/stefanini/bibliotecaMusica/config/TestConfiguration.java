package com.stefanini.bibliotecaMusica.config;


import com.stefanini.bibliotecaMusica.repository.ArtistasRepository;
import com.stefanini.bibliotecaMusica.repository.PlaylistRepository;
import com.stefanini.bibliotecaMusica.repository.UsuarioRepository;
import com.stefanini.bibliotecaMusica.repository.MusicasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;

@Configuration
@Profile("test")
public class TestConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(MusicasRepository musicasRepository,
                                        ArtistasRepository artistasRepository,
                                        UsuarioRepository usuarioRepository,
                                        PlaylistRepository playlistRepository
                                        ) {
        return args -> {
            /*Turma turma = new Turma();
            turmaRepository.save(turma);
            DadosPessoais dadosPessoais = new DadosPessoais(null,
                    "156-456-41",
                    "joao@gmail.com");
            Aluno joao = new Aluno(null,
                    "Joao",
                    "1234567",
                   dadosPessoais,
                    turma
            );
            alunoRepository.save(joao);
            Curso curso = new Curso(null, "medicina",
                    new ArrayList<Disciplina>(),
                    370);
            cursoRepository.save(curso);
            Disciplina disciplina = new Disciplina(null,
                    "Disciplina1",
                    "A045cod",
                    "xxxxxxxxxxxxxxxxxxxxxx",
                    15,
                    80,
                    turma,
                    curso);
            disciplinaRepository.save(disciplina);*/
        };
    }

}

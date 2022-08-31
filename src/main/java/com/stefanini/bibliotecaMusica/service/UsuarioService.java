package com.stefanini.bibliotecaMusica.service;


import com.stefanini.bibliotecaMusica.exception.UsuarioNotFoundException;
import com.stefanini.bibliotecaMusica.model.Usuario;
import com.stefanini.bibliotecaMusica.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new UsuarioNotFoundException(id));
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    
    public void delete(Usuario usuario){
        usuarioRepository.delete(usuario);
    }
    
    public Optional<Usuario> getUsuario(String username){
        return usuarioRepository.findByUsuario(username);
    }
    /*public Usuario getUsuarioSenha(String login,String senha){
        return usuarioRepository.findLoginSenha(login, senha);
    }*/

}

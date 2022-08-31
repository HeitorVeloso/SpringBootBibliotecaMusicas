package com.stefanini.bibliotecaMusica.config.Seguranca;

import com.stefanini.bibliotecaMusica.model.Usuario;
import com.stefanini.bibliotecaMusica.repository.UsuarioRepository;
import com.stefanini.bibliotecaMusica.service.UsuarioService;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class AutenticacaoService implements UserDetailsService {
	
	   
    private final UsuarioService usuarioService;

    @Autowired
    public AutenticacaoService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioService.getUsuario(username);
        if (usuario.isPresent()) {
            return usuario.get();
        }

        throw new UsernameNotFoundException("Dados inválidos!");
    }

}

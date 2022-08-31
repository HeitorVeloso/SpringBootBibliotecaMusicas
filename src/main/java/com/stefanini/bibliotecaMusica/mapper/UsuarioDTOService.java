package com.stefanini.bibliotecaMusica.mapper;

import com.stefanini.bibliotecaMusica.dto.UsuarioDTO;
import com.stefanini.bibliotecaMusica.exception.UsuarioNotFoundException;
import com.stefanini.bibliotecaMusica.model.Usuario;
import com.stefanini.bibliotecaMusica.service.PermissoesService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDTOService {    

    //private final PermissoesService permissoesService;
    
    @Autowired
    public UsuarioDTOService(/*PermissoesService permissoesService*/) {
        //this.permissoesService = permissoesService;
    }
    
    public Usuario mapUsuario(UsuarioDTO usuarioDTO) throws UsuarioNotFoundException {        
        Usuario newUsuario = new Usuario(usuarioDTO); 
        //permissoesService.save(newUsuario.getPermissoes());
        return newUsuario;
    }
    
    public List<UsuarioDTO> mapTodosUsuario(List<Usuario> listaUsuario) {

        List<UsuarioDTO> listaUsuarioDTO = new ArrayList();
        for (Usuario usuario : listaUsuario) {
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
            listaUsuarioDTO.add(usuarioDTO);
        }
        
        return listaUsuarioDTO;
    }    
}

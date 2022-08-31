package com.stefanini.bibliotecaMusica.controller;

import com.stefanini.bibliotecaMusica.dto.TokenDTO;
import com.stefanini.bibliotecaMusica.config.Seguranca.TokenService;
import com.stefanini.bibliotecaMusica.dto.UsuarioDTO;
import com.stefanini.bibliotecaMusica.exception.PermissoesNotFoundException;
import com.stefanini.bibliotecaMusica.exception.UsuarioNotFoundException;
import com.stefanini.bibliotecaMusica.model.Usuario;
import com.stefanini.bibliotecaMusica.mapper.UsuarioDTOService;


import com.stefanini.bibliotecaMusica.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioDTOService usuarioDTOService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioDTOService usuarioDTOService) {
        this.usuarioService = usuarioService;
        this.usuarioDTOService = usuarioDTOService;
    }    
    
    //@Qualifier("authenticationManagerBean")
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    

    @PostMapping
    public ResponseEntity<UsuarioDTO> autenticar(@RequestBody @Valid UsuarioDTO usuarioDTO) throws UsuarioNotFoundException {        
        Optional<Usuario> usuarioExiste = usuarioService.getUsuario(usuarioDTO.getUsuario());
	if (usuarioExiste.isPresent()) {//Verifica se já existe o usuario para não deixar cadastrar novamente
            return null;// criar metodo para retornar o erro
            //return ResponseEntity.ok();
	}
        Usuario newUsuario = usuarioDTOService.mapUsuario(usuarioDTO);
	newUsuario.criptografarSenha();
        newUsuario = usuarioService.save(newUsuario);
	return ResponseEntity.ok(new UsuarioDTO(newUsuario));
        /*UsernamePasswordAuthenticationToken dadosLogin = usuarioDTO.converter();
        
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }*/
    }
}

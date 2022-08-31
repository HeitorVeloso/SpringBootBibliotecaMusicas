package com.stefanini.bibliotecaMusica.controller;

import com.stefanini.bibliotecaMusica.dto.TokenDTO;
import com.stefanini.bibliotecaMusica.config.Seguranca.TokenService;
import com.stefanini.bibliotecaMusica.dto.UsuarioDTO;
import com.stefanini.bibliotecaMusica.exception.PermissoesNotFoundException;
import com.stefanini.bibliotecaMusica.exception.UsuarioNotFoundException;

import com.stefanini.bibliotecaMusica.mapper.UsuarioDTOService;

import com.stefanini.bibliotecaMusica.service.UsuarioService;
import java.util.List;
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
@RequestMapping("/auth")
public class AutenticacaoController {

    private final UsuarioService usuarioService;
    private final UsuarioDTOService usuarioDTOService;

    @Autowired
    public AutenticacaoController(UsuarioService usuarioService, UsuarioDTOService usuarioDTOService) {
        this.usuarioService = usuarioService;
        this.usuarioDTOService = usuarioDTOService;
    }    
    
    //@Qualifier("authenticationManagerBean")
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        UsernamePasswordAuthenticationToken dadosLogin = usuarioDTO.converter();
        
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}

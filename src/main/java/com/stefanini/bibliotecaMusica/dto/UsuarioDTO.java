package com.stefanini.bibliotecaMusica.dto;

import com.stefanini.bibliotecaMusica.model.Usuario;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsuarioDTO {

   private String nome;

    private String usuario;

    private String senha; 
    
    private String tipo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nome, String usuario, String senha) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;        
    }
    
    /*public UsuarioDTO(Long id,String nome, String usuario, String senha) {
        this.id=id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;        
    }*/
    
    public UsuarioDTO(Usuario usuario) {        
        this.usuario = usuario.getUsuario();
        this.senha = usuario.getSenha();
        /*if(usuario.getPermissoes()!=null){
            this.tipo = usuario.getPermissoes().getTipo();
        }*/
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public static List<UsuarioDTO> converter(List<Usuario> usuario){
        return usuario.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }
    
    public static Page<UsuarioDTO> converter(Page<Usuario> usuario){
        return usuario.map(UsuarioDTO::new);
    }
    
    public UsernamePasswordAuthenticationToken converter() {
	return new UsernamePasswordAuthenticationToken(usuario, senha);
    }
        
}

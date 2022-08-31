/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stefanini.bibliotecaMusica.model;

import com.stefanini.bibliotecaMusica.dto.UsuarioDTO;
import com.stefanini.bibliotecaMusica.metodos.LoginUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
@Entity
@Data
@XmlRootElement
@Table(name="users")
/*@NamedQueries({
    @NamedQuery(name = "Usuario.findLoginSenha", query = "SELECT u FROM Usuario u where u.login =:login and u.senha=:senha")})*/
public class Usuario implements UserDetails,Serializable {

    @Id
    @Column(name="username",nullable = false,length = 50,unique = true)
    private String usuario;
    @Column(name="password",nullable = false,length = 200)
    private String senha;
    @Column(name="enabled")
    private Boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Playlist> playlist;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permissoes> permissoes;
    private static final Logger LOG = Logger.getLogger(Usuario.class.getName());

    public Usuario(String usuario, String senha, boolean enabled) {
        this.usuario = usuario;
        this.senha = senha;
        this.enabled = enabled;
    }   

    public Usuario(UsuarioDTO usuarioDTO) {        
        this.usuario= usuarioDTO.getUsuario();
        this.senha = usuarioDTO.getSenha();
        /*this.permissoes = new Permissoes();
        this.permissoes.setTipo(usuarioDTO.getTipo());*/
    }
    
    /*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}*/
    
    public void criptografarSenha(){
        this.senha = new BCryptPasswordEncoder().encode(this.senha);
    }
            
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return usuario;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return this.permissoes;
    }

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
            return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
 
        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stefanini.bibliotecaMusica.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@Entity
@Data
@XmlRootElement
@Table(name="authorities")
public class Permissoes implements GrantedAuthority,Serializable {
   
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="authority",length = 50)
    private String autorizacao;    
    
    /*@OneToOne(mappedBy = "permissoes")    
    private Usuario usuario;*/

    public Permissoes(String autorizacao) {
        this.autorizacao = autorizacao;
    }
    
    @Override
    public String getAuthority() {
        return autorizacao;
    }
    
    @Override
    public String toString() {
        return autorizacao;
    }

}

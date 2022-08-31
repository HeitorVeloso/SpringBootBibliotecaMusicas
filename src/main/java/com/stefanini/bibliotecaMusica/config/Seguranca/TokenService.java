package com.stefanini.bibliotecaMusica.config.Seguranca;

import com.stefanini.bibliotecaMusica.model.Usuario;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${listenToTheMusic.jwt.expiration}")
	private String expiration;
	
	@Value("${listenToTheMusic.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API do ListenToTheMusic")//a API que criou o token
				.setSubject(logado.getUsuario())//Nome do usuario
				.setIssuedAt(hoje)//data de criação
				.setExpiration(dataExpiracao)//data de expiração do token
				.signWith(SignatureAlgorithm.HS256, secret)//Algoritmo de criptografia e a senha
				.compact();//transforma em uma String
	}
	
	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getUsernameUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

}

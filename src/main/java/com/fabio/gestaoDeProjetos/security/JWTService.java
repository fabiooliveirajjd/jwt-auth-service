package com.fabio.gestaoDeProjetos.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.fabio.gestaoDeProjetos.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component // Spring transforma em um bean e onde precisar pode injetar
public class JWTService {
	// CHAVE SERCRETA UTILIZADA PELO JWT PARA CODIFICAR E CODIFICAR O TOKEM
	private static final String chavePrivadaJWT = "secretKey";

	/**
	 * 
	 * @param authenticateAction MÉTODO PARA AUTENTICAÇÃO DO USUÁRIO
	 * @return RETORNA UM TOKEM
	 */
	public String gerarToken(Authentication authenticateAction) {

		// Tempo de expiração do token
		int tempoExpiração = 500000;

		// Data de expiração
		Date dataExpiração = new Date(new Date().getTime() + tempoExpiração);

		// AQUI PEGAMOS O USUARIO ATUAL DA AUTENTICACAO
		Usuario usuario = (Usuario) authenticateAction.getPrincipal();

		// PEGA TODOS OS DADOS E GERA O TOKEM JWT
		return Jwts.builder().setSubject(usuario.getAuthorities().toString()).setIssuedAt(new Date())
				.setExpiration(dataExpiração).signWith(SignatureAlgorithm.HS256, chavePrivadaJWT).compact();
	}

	/**
	 * //METODO PARA RETORNAR O ID DO USUARIO DONO DO TOKEM
	 * 
	 * @param token TOKEM DO USUÁRIO
	 * @return RETORNA O ID
	 */
	public Optional<Long> obterIdDoUsuario(String token) {
		try {
			// RETORNA AS PERMISSÕES DO TOKEM FAZENDO UM PARSE PARA DESCOBRIR QUAL AS PERMISSÕES SE USUÁRIOS.
			Claims claims = Jwts.parser().setSigningKey(chavePrivadaJWT).parseClaimsJws(token).getBody();

			// RETORNA O ID DE DENTRO DO TOKEM, SE NÃO ENCONTRAR RETORNA NULL.
			return Optional.ofNullable(Long.parseLong(claims.getSubject()));
		} catch (Exception e) {
			// SE NÃO ENCONTAR NADA, RETORNA UM OPTIONAL NULL.
			return Optional.empty();
		}
	}

}

package com.fabio.gestaoDeProjetos.security;

import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private CustomUserDatailsService customUserDatailsService;

	// METODO PRINCIPAL ONDE TODA A REQUISIÇÃO BATE ANTES DE CHEGAR NO ENDPOINT
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// PEGA O TOKEN DE DENTRO DA REQUISIÇÃO
		String token = obterToken(request);
		// PEGA O ID DO USUARIO QUE ESTA DENTRO DO TOKEN

		Optional<Long> id = jwtService.obterIdDoUsuario(token);
		// SE NÃO ACHOU O ID É PORQUE O USUÁRIO MANDOU O TOKEN INCORRETO
		if (!id.isPresent()) {
			throw new InputMismatchException("token inválido");
		}
		// PEGO O USUARIO DO TOKEN PELO SEU ID
		UserDetails usuario = customUserDatailsService.obterUsuarioPorId(id.get());

		// NESSE PONTO VERIFICAMOS SE O USUARIO ESTÁ AUTENTICADO OU NÃO
		// AQUI TAMBÉM PODERIAMOS VALIDAR AS PERMISSÕES
		UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario, null,
				Collections.emptyList());
		// MUDA A AUTENTICAÇÃO PARA A PRÓPRIA REQUISIÇÃO
		autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		// REPASSANDO A AUTENTICAÇÃO PARA O CONTEXTO DO SECURIT
		// A PARTIR DE AGORA O SPRING TOMA CONTA DE TUDO PARA MIM
		SecurityContextHolder.getContext().setAuthentication(autenticacao);
	}

	private String obterToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		// VERIFICA SE VEIO ALGUMA COISA SEM SER ESPAÇOS EM BRANCO DENTRO DO TOKEM
		if (!StringUtils.hasText(token)) {
			return null;
		}
		return token.substring(7);
		// Bearer as81ds8v1s57v.s5cdsvds7aa5c8btnKuyigufghyr8mh12fufy8
	}

}

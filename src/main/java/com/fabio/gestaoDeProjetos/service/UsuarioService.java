package com.fabio.gestaoDeProjetos.service;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fabio.gestaoDeProjetos.entity.Usuario;
import com.fabio.gestaoDeProjetos.repository.UsuarioRepository;
import com.fabio.gestaoDeProjetos.security.JWTService;
import com.fabio.gestaoDeProjetos.usuario.LoginResponse;

@Service
public class UsuarioService {
	//A VARIAVEL headerPrefix TEM QUE SER EXATAMENTE COM ESSE NOME.
	private static final String headerPrefix = "Bearer ";

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public List<Usuario> obterTodos() {
		return usuarioRepository.findAll();
	}

	public Optional<Usuario> obterPorId(Long id) {
		return usuarioRepository.findById(id);
	}

	public Optional<Usuario> obterPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	public Usuario adicionar(Usuario usuario) {
		// ZERA O ID DO CARA, TORNANDO ELE NULL
		usuario.setId(null);
		// SE O EMAIL DE USUARIO FOR PRESENTE
		if (obterPorEmail(usuario.getEmail()).isPresent()) {
			// AQUI PODERIA LANÇAR UMA EXESSAO INFORMANDO QUE O USUARIO JA EXISTE
			throw new InputMismatchException("Já existe um usuario cadastrado com o email " + usuario.getEmail());
		}
		// AQUI EU ESTOU CODIFICANDO A SENHA PARA NÃO FICAR PÚBLICA, GERANDO UM HASH
		String senha = passwordEncoder.encode(usuario.getSenha());
		// SETA A NOVA SENHA
		usuario.setSenha(senha);
		// SALVA O USUÁRIO
		return usuarioRepository.save(usuario);
	}

	public LoginResponse logar(String email, String senha) {

		// AQUI QUE A AUTENTICAÇÃO ACONTECE AUTOMATICAMENTE
		Authentication autenticacao = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, senha, Collections.emptyList()));
		// AQUI PASSO A NOVA AUTENTICAÇÃO PARA O SPRING SECURITY
		SecurityContextHolder.getContext().setAuthentication(autenticacao);
		// AQUI GERO O TOKEN DE USUÁRIO PARA DEVOLVER ELE
		// BEARER anfsdf328f745a4fv4f1av4fda4v5v5fad5vfdabnhgmuiukiu5111
		String token = headerPrefix + jwtService.gerarToken(autenticacao);

		Usuario usuario = usuarioRepository.findByEmail(email).get();

		return new LoginResponse(token, usuario);

	}

}

package com.fabio.gestaoDeProjetos.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fabio.gestaoDeProjetos.entity.Usuario;
import com.fabio.gestaoDeProjetos.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
}

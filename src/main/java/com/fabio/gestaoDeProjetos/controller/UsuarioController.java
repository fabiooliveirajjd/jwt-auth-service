package com.fabio.gestaoDeProjetos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabio.gestaoDeProjetos.entity.Usuario;
import com.fabio.gestaoDeProjetos.service.UsuarioService;
import com.fabio.gestaoDeProjetos.usuario.LoginRequest;
import com.fabio.gestaoDeProjetos.usuario.LoginResponse;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public List<Usuario> obterTodos() {
		return usuarioService.obterTodos();
	}

	@GetMapping("/{id}")
	public Optional<Usuario> obter(@PathVariable("id") Long id) {
		return usuarioService.obterPorId(id);
	}

	@PostMapping
	public Usuario adicionar(@RequestBody Usuario usuario) {
		return usuarioService.adicionar(usuario);
	}

	@PostMapping("/login")	
	public LoginResponse logar(@RequestBody LoginRequest request) {
		return usuarioService.logar(request.getEmail(), request.getSenha());
	}

}

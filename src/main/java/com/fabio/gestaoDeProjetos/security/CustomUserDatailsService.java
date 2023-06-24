package com.fabio.gestaoDeProjetos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.fabio.gestaoDeProjetos.service.UsuarioService;

/**
 * Implementa outra classe
 */
@Service
public class CustomUserDatailsService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String email) {
//		Usuario usuario =  getUser(() -> usuarioService.obterPorEmail(email));
//		return usuario;
		return usuarioService.obterPorEmail(email).get();
	}

	public UserDetails obterUsuarioPorId(Long id) {
		return usuarioService.obterPorId(id).get();
	}

//	private Usuario getUser(Supplier<Optional<Usuario>> supplier) {
//	return	supplier.get().orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
//	}
}

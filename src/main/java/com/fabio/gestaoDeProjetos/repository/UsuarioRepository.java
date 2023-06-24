package com.fabio.gestaoDeProjetos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.gestaoDeProjetos.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}

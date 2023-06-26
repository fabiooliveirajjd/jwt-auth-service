package com.fabio.gestaoDeProjetos.usuario;

import com.fabio.gestaoDeProjetos.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

	private String token;
	private Usuario usuario;
}

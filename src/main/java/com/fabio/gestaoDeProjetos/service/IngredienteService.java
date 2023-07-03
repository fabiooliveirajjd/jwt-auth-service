package com.fabio.gestaoDeProjetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabio.gestaoDeProjetos.entity.Ingrediente;
import com.fabio.gestaoDeProjetos.repository.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;

	public List<Ingrediente> obterTodos() {
		return ingredienteRepository.findAll();
	}

	public Optional<Ingrediente> obterPorId(Long id) {
		return ingredienteRepository.findById(id);
	}

	public Ingrediente adicionar(Ingrediente Ingrediente) {
		return ingredienteRepository.save(Ingrediente);
	}

	public Ingrediente atualizar(Long id, Ingrediente Ingrediente) {
		Ingrediente.setId(id);
		return ingredienteRepository.save(Ingrediente);
	}

	public void deletar(Long id) {
		ingredienteRepository.deleteById(id);
	}
}

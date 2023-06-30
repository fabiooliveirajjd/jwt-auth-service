package com.fabio.gestaoDeProjetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabio.gestaoDeProjetos.entity.Categoria;
import com.fabio.gestaoDeProjetos.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> obterTodos() {
		return categoriaRepository.findAll();
	}

	public Optional<Categoria> obterPorId(Long id) {
		return categoriaRepository.findById(id);
	}

	public Categoria adicionar(Categoria Categoria) {
		return categoriaRepository.save(Categoria);
	}

	public Categoria atualizar(Long id, Categoria Categoria) {
		Categoria.setId(id);
		return categoriaRepository.save(Categoria);
	}
	
	public void deletar (Long id) {
		categoriaRepository.deleteById(id);
	}
}

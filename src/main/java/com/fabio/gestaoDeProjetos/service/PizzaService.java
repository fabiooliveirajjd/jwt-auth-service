package com.fabio.gestaoDeProjetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabio.gestaoDeProjetos.entity.Pizza;
import com.fabio.gestaoDeProjetos.repository.PizzaRepository;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;

	public List<Pizza> obterTodos() {
		return pizzaRepository.findAll();
	}

	public Optional<Pizza> obterPorId(Long id) {
		return pizzaRepository.findById(id);
	}

	public Pizza adicionar(Pizza Pizza) {
		return pizzaRepository.save(Pizza);
	}

	public Pizza atualizar(Long id, Pizza Pizza) {
		Pizza.setId(id);
		return pizzaRepository.save(Pizza);
	}

	public void deletar(Long id) {
		pizzaRepository.deleteById(id);
	}
}

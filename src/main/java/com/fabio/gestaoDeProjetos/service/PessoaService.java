package com.fabio.gestaoDeProjetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabio.gestaoDeProjetos.entity.Pessoa;
import com.fabio.gestaoDeProjetos.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> obterTodos() {
		return pessoaRepository.findAll();
	}

	public Optional<Pessoa> obterPorId(Long id) {
		return pessoaRepository.findById(id);
	}

	public Pessoa adicionar(Pessoa Pessoa) {
		return pessoaRepository.save(Pessoa);
	}

	public Pessoa atualizar(Long id, Pessoa Pessoa) {
		Pessoa.setId(id);
		return pessoaRepository.save(Pessoa);
	}

	public void deletar(Long id) {
		pessoaRepository.deleteById(id);
	}
}

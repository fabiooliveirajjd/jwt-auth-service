package com.fabio.gestaoDeProjetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabio.gestaoDeProjetos.entity.Avaliacao;
import com.fabio.gestaoDeProjetos.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	public List<Avaliacao> obterTodos() {
		return avaliacaoRepository.findAll();
	}

	public Optional<Avaliacao> obterPorId(Long id) {
		return avaliacaoRepository.findById(id);
	}

	public Avaliacao adicionar(Avaliacao Avaliacao) {
		return avaliacaoRepository.save(Avaliacao);
	}

	public Avaliacao atualizar(Long id, Avaliacao Avaliacao) {
		Avaliacao.setId(id);
		return avaliacaoRepository.save(Avaliacao);
	}

	public void deletar(Long id) {
		avaliacaoRepository.deleteById(id);
	}
}

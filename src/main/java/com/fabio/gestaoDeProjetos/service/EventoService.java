package com.fabio.gestaoDeProjetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabio.gestaoDeProjetos.entity.Evento;
import com.fabio.gestaoDeProjetos.repository.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	public List<Evento> obterTodos() {
		return eventoRepository.findAll();
	}

	public Optional<Evento> obterPorId(Long id) {
		return eventoRepository.findById(id);
	}

	public Evento adicionar(Evento evento) {
		return eventoRepository.save(evento);
	}

	public Evento atualizar(Long id, Evento evento) {
		evento.setId(id);
		return eventoRepository.save(evento);
	}
	
	public void deletar (Long id) {
		eventoRepository.deleteById(id);
	}
}

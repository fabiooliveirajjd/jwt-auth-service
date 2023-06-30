package com.fabio.gestaoDeProjetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabio.gestaoDeProjetos.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

}

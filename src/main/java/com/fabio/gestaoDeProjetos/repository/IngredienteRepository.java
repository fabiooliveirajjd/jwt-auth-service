package com.fabio.gestaoDeProjetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabio.gestaoDeProjetos.entity.Avaliacao;
import com.fabio.gestaoDeProjetos.entity.Ingrediente;
import com.fabio.gestaoDeProjetos.entity.Pizza;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

}

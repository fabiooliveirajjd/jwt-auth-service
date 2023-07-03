package com.fabio.gestaoDeProjetos.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idIngrediente")
	private Long id;

	private String descricao;
	// SÓ MAPEIA POR SE TRATAR DA ENTIDADE SECUNDÁRIA DO RELACIONAMENTO
	@ManyToMany(mappedBy = "ingredientes")
	@JsonBackReference
	private List<Pizza> pizzas;
}

package com.fabio.gestaoDeProjetos.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
	// DONO DO RELACIONAMENTO
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPizza")
	private Long id;

	private String descricao;

	// MUITOS PARA MUITOS
	@ManyToMany
	// RESPONSÁVEL TE FAZER O MAPEAMENTO DA TABELA PRINCIPAL COM A TEBELA
	// INTERMEDIÁRIA
	@JsonManagedReference
	@JoinTable(name = "pizza_ingrediente", // NOME DA TABELA, ENTIDADE MANDATÓRIA
			joinColumns = @JoinColumn(name = "idPizza"), // NOME DA COLUNA NA TABELA
			inverseJoinColumns = @JoinColumn(name = "idIngrediente"))
	private List<Ingrediente> ingredientes;
}

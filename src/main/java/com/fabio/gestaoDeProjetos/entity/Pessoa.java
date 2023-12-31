package com.fabio.gestaoDeProjetos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPessoa")
	private Long id;
	
	private String nome;
	
	@OneToOne(mappedBy = "pessoa")
	@JsonBackReference //TRÁS A PESSOA MAS NÃO TRAS A AVALIZÇÃO
	private Avaliacao avaliacao;

}

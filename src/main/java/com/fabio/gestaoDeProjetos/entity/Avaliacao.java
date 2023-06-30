package com.fabio.gestaoDeProjetos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Avaliacao {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAvaliacao")
	private Long id;
	
	private String descricao;
	
	//RELACIONAMENTO 1:1 PODE SER COLOCADO EM QUALQUER TABELA QUE VAI FUNCIONAR
	@OneToOne
	@JoinColumn(name = "idPessoa")
	private Pessoa pessoa;

}

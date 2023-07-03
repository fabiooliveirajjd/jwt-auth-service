package com.fabio.gestaoDeProjetos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Avaliacao {
	
	//CLASSE MANDATÓRIA
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAvaliacao")
	private Long id;
	
	private String descricao;
	
	//RELACIONAMENTO 1:1 PODE SER COLOCADO EM QUALQUER TABELA QUE VAI FUNCIONAR
	@OneToOne
	//FAZ O VÍNCULO
	@JoinColumn(name = "idPessoa")
	@JsonManagedReference
	private Pessoa pessoa;

}

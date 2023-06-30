package com.fabio.gestaoDeProjetos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEvento")
	private Long id;

	private String descricao;

	private Date dataHora;
	//MUITOS EVENTOS PARA UMA CATEGORIA (TIPO UNIDIRECIONAL)
	@ManyToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;

}

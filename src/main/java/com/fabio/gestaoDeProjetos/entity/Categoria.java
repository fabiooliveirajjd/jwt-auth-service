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
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCategoria")
	private Long id;

	private String descricao;
	//UMA CATEGORIA PODE ESTAR EM MUITOS EVENTOS
	//SETA A CATEGORIA QUE ESTÁ NA CLASSE ENVENTO 
	//private Categoria categoria;
	@ManyToMany(mappedBy = "categoria")
	//CUIDA PARA NÃO DEIXAR SERIALIZAR O QUE ESTÁ AQUI DENTRO
	//TECNICAMENTE PARA NÃO TRAZER A LISTA DE EVENTOS
	@JsonBackReference
	private List<Evento> eventos;

}

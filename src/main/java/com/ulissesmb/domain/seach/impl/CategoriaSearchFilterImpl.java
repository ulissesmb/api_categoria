package com.ulissesmb.domain.seach.impl;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.ulissesmb.domain.seach.CategoriaSearchFilter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaSearchFilterImpl implements CategoriaSearchFilter {

	@NotNull(message = "O nome é obrigatorio")
	@Size(min = 3, message = "O nome tem que conter no minimo 3 caracteres")
	private String nome;
	
	private Long id;
	
	public CategoriaSearchFilterImpl(String nome, Long id) {
		this.nome = nome;
		this.id = id;
	}
	
	public CategoriaSearchFilterImpl(String nome) {
		this.nome = nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

}

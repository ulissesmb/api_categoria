package com.ulissesmb.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaDTO implements RequestDTO {
	
	private static final String MSG_NOME_VALIDATOR = "O nome é obrigatorio";
	private static final String MSG_VALIDATOR = "A categoria tem que conter de 3 a 30 caracteres";

	private Long id;

	@NotNull(message = MSG_NOME_VALIDATOR)
	@Size(min = 3, max = 30, message = MSG_VALIDATOR)
	private String nome;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Long id,
			@NotNull(message = MSG_NOME_VALIDATOR ) @Size(min = 3, max = 30, message = MSG_VALIDATOR) String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

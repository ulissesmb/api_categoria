package com.ulissesmb.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.ulissesmb.domain.interfaces.PersistentEntity;

@Entity
@Table(name = "TB_CATEGORIA")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Categoria implements PersistentEntity, Serializable {

	private static final long serialVersionUID = 3525056461980422999L;

	private static final String MSG_NOME_VALIDATOR = "O nome é obrigatorio";
	private static final String MSG_VALIDATOR = "A categoria tem que conter de 3 a 30 caracteres";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = MSG_NOME_VALIDATOR)
	@Size(min = 3, max = 30, message = MSG_VALIDATOR)
	private String nome;
	
	@Lob
	@Column
	private byte[] imagem;

	public Categoria() {
	}

	public Categoria(Long id,
			@NotNull(message = MSG_NOME_VALIDATOR ) @Size(min = 3, max = 30, message = MSG_VALIDATOR) String nome) {
		super();
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

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categoria [id=").append(id).append(", nome=").append(nome).append("]");
		return builder.toString();
	}

}

package com.attonatus.desafio.dto;

import java.io.Serializable;

import com.attonatus.desafio.domain.Pessoa;

public class PessoaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String dataNascimento;
	
	public PessoaDTO() {
	}
	
	public PessoaDTO(Long id, String name, String dataNascimento) {
		this.id = id;
		this.name = name;
		this.dataNascimento = dataNascimento;
	}

	public PessoaDTO(Pessoa entity) {
		id = entity.getId();
		name = entity.getName();
		dataNascimento = entity.getDataNascimento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}

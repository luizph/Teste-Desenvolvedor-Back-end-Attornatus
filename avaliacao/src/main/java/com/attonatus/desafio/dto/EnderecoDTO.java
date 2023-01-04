package com.attonatus.desafio.dto;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.attonatus.desafio.domain.Endereco;
import com.attonatus.desafio.domain.Pessoa;
import com.attonatus.desafio.repository.PessoaRepository;

public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PessoaRepository Repository;
	
	private Long id;
	private String cidade;
	private String logradouro;
	private Integer cep;
	private Integer numero;
	private Boolean principal;
	private Long morador;
	
	public EnderecoDTO() {
	}

	public EnderecoDTO(Long id, String cidade, String logradouro, Integer cep, Integer numero, Boolean principal,
			Long residenciaId) {
		this.id = id;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.morador = residenciaId;
		setPrincipal(principal);
	}

	public EnderecoDTO(Endereco entity) {
		id = entity.getId();
		cidade = entity.getCidade();
		logradouro = entity.getLogradouro();
		cep = entity.getCep();
		numero = entity.getNumero();
		morador = entity.getId();
		principal = entity.getPrincipal();
	}

	public PessoaRepository getRepository() {
		return Repository;
	}

	public void setRepository(PessoaRepository repository) {
		Repository = repository;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = true;
	}

	public Long getMorador() {
		return morador;
	}
	
	@SuppressWarnings("deprecation")
	public Pessoa getMoradorPessoa(Long id) {		
		Pessoa entity = Repository.getOne(id);
		return entity;
	}

	public void setMorador(Long morador) {
		this.morador = morador;
	}
}
	
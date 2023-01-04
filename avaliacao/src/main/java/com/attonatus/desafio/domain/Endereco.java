package com.attonatus.desafio.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cidade;
	private String logradouro;
	private Integer cep;
	private Integer numero;
	private Boolean principal;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "residencia_id")
	private Pessoa morador;
	
	public Endereco() {
	}

	public Endereco(Long id, String cidade, String logradouro, Integer cep, Integer numero, Boolean principal,
			Pessoa morador) {
		this.id = id;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.principal = principal;
		this.morador = morador;
		setPrincipal(principal);
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

	public Pessoa getMorador() {
		return morador;
	}

	public void setMorador(Pessoa morador) {
		this.morador = morador;
	}
	
	public Boolean getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(Boolean principal) {
		List<Endereco> enderecos = this.morador.getEndereco();
		if (principal) {
			enderecos.stream().forEach((x) -> x.principal = false);
			this.principal = true;
		} else {
			this.principal = false;
			}
		}	
	}
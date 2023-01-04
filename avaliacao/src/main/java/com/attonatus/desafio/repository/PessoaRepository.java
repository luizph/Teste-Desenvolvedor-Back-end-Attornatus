package com.attonatus.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attonatus.desafio.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository <Pessoa, Long> {
	
}

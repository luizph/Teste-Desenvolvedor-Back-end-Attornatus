package com.attonatus.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attonatus.desafio.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, String> {

}

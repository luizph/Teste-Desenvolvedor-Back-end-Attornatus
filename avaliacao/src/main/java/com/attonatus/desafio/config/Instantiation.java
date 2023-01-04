package com.attonatus.desafio.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.attonatus.desafio.domain.Endereco;
import com.attonatus.desafio.domain.Pessoa;
import com.attonatus.desafio.repository.EnderecoRepository;
import com.attonatus.desafio.repository.PessoaRepository;

@Configuration
@Profile("test")
public class Instantiation implements CommandLineRunner {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void run(String... args) throws Exception {

		Pessoa p1 = new Pessoa(null, "Luiz philipe", "1996/07/17");
		Pessoa p2 = new Pessoa(null, "Luan", "2002/08/21");

		Endereco e1 = new Endereco(null, "Mirabela", "Rua Gregoerio Rodrigues", 3373000, 214, true, p1);
		Endereco e2 = new Endereco(null, "Montes Claros", "Rua Antonio Pimento ", 3985748, 58, true, p2);
		Endereco e3 = new Endereco(null, "Patis", "Mendes Camelo", 39373000, 14, false, p2);

		pessoaRepository.saveAll(Arrays.asList(p1, p2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
	}
}

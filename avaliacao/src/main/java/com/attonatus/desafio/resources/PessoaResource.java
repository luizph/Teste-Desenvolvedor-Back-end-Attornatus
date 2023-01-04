package com.attonatus.desafio.resources;

import java.net.URI;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.attonatus.desafio.domain.Pessoa;
import com.attonatus.desafio.dto.PessoaDTO;
import com.attonatus.desafio.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService service;
	
	
	@GetMapping
	public ResponseEntity<List<PessoaDTO>> findAll(){
		List<PessoaDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
		Pessoa obj = service.findById(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> insert (@RequestBody Pessoa obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).body(obj);		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);;
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa obj) throws ParseException{
		obj = service.update(id,  obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
package com.attonatus.desafio.services;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.attonatus.desafio.domain.Pessoa;
import com.attonatus.desafio.dto.PessoaDTO;
import com.attonatus.desafio.repository.PessoaRepository;
import com.attonatus.desafio.services.exception.DatabaseException;
import com.attonatus.desafio.services.exception.ResourceNotFoundException;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repository;
	
	
	public Pessoa findById(Long id) {
		Optional<Pessoa> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Pessoa insert(Pessoa obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}	
	}
	
	@SuppressWarnings("deprecation")
	public Pessoa update(Long id, Pessoa obj) throws ParseException {
		try {
			Pessoa entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id); 
		}
	}
	
	private void updateData(Pessoa entity, Pessoa obj) throws ParseException {
		entity.setName(obj.getName());
		entity.setDataNascimento(obj.getDataNascimento().toString());
	}
	
	public List<PessoaDTO> findAll(){
		List<Pessoa> list = repository.findAll();
		return list.stream().map(x -> new PessoaDTO(x)).collect(Collectors.toList());
	}	
}

package com.attonatus.desafio.services;

import java.awt.print.Pageable;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.attonatus.desafio.domain.Endereco;
import com.attonatus.desafio.domain.Pessoa;
import com.attonatus.desafio.dto.EnderecoDTO;
import com.attonatus.desafio.repository.EnderecoRepository;
import com.attonatus.desafio.services.exception.DatabaseException;
import com.attonatus.desafio.services.exception.ResourceNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	public List<Endereco> findAll() {
		return repository.findAll();
	}

	public Endereco findById(Long id) {
		Optional<Endereco> obj = repository.findById(id);
		return obj.get();
	}

	public Endereco insert(Endereco obj) {

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

	public Endereco update(Long id, EnderecoDTO objDTO) throws ParseException {
		Long moradorId = objDTO.getMorador();
		Endereco novoObj = new Endereco(objDTO.getId(), objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getCidade(),
				objDTO.getCep(), objDTO.getPrincipal(), new Pessoa(moradorId, null, null));

		try {
			@SuppressWarnings("deprecation")
			Endereco entity = repository.getOne(id);
			updateSource(entity, novoObj);
			return repository.save(entity);
		} catch (EntityNotFoundException error) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateSource(Endereco entity, Endereco obj) throws ParseException {

		entity.setMorador(obj.getMorador());
		entity.setLogradouro(obj.getLogradouro());
		entity.setNumero(obj.getNumero());
		entity.setCidade(obj.getCidade());
		entity.setCep(obj.getCep());
		entity.setPrincipal(obj.getPrincipal());
	}

	@Transactional
	public Page<EnderecoDTO> findAll(Pageable pageable) {
		Page<Endereco> page = repository.findAll(pageable);
		return page.map(x -> new EnderecoDTO(x));
	}

	@Transactional
	public EnderecoDTO insert(EnderecoDTO dto) {
		Endereco entity = new Endereco();
		entity.setMorador(new Pessoa(dto.getMorador(), null, null));
		entity.setLogradouro(dto.getLogradouro());
		entity.setNumero(dto.getNumero());
		entity.setCidade(dto.getCidade());
		entity.setCep(dto.getCep());
		entity.setPrincipal(dto.getPrincipal());

		entity = repository.save(entity);

		return new EnderecoDTO(entity);
	}
}
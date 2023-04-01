package com.jlarger.contasfacil.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jlarger.contasfacil.dto.CategoriaDTO;
import com.jlarger.contasfacil.entities.Categoria;
import com.jlarger.contasfacil.entities.Usuario;
import com.jlarger.contasfacil.repositories.CategoriaRepository;
import com.jlarger.contasfacil.services.exceptions.DatabaseException;
import com.jlarger.contasfacil.services.exceptions.ResourceNotFoundException;
import com.jlarger.contasfacil.util.ServiceLocator;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	@Transactional(readOnly = true)
	public List<CategoriaDTO> findAll(Pageable pageable) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		List<Categoria> list =  repository.findAllCategoriasByUser(usuarioLogado.getId());
		
		return list.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		Optional<Categoria> obj = repository.findByIdAndUsuarioId(id, usuarioLogado.getId());
		
		Categoria entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new CategoriaDTO(entity);
	}

	@Transactional
	public CategoriaDTO insert(CategoriaDTO dto) {
		
		Categoria entity = new Categoria();
		entity.setNmCategoria(dto.getNmCategoria());
		entity.setUsuario(ServiceLocator.getUsuarioLogado());
		
		entity = repository.save(entity);
		
		return new CategoriaDTO(entity);
	}

	@Transactional
	public CategoriaDTO update(Long id, CategoriaDTO dto) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		Optional<Categoria> obj = repository.findByIdAndUsuarioId(id, usuarioLogado.getId());
		
		Categoria entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		entity.setNmCategoria(dto.getNmCategoria());
		entity = repository.save(entity);
		
		return new CategoriaDTO(entity);
	}

	public void delete(Long id) {
		
		try {
			
			Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
			
			Optional<Categoria> obj = repository.findByIdAndUsuarioId(id, usuarioLogado.getId());
			
			Categoria entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
			
			repository.deleteById(entity.getId());
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}
}

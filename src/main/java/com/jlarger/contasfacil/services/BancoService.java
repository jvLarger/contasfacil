package com.jlarger.contasfacil.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jlarger.contasfacil.dto.BancoDTO;
import com.jlarger.contasfacil.entities.Banco;
import com.jlarger.contasfacil.repositories.BancoRepository;
import com.jlarger.contasfacil.services.exceptions.ResourceNotFoundException;

@Service
public class BancoService {

	@Autowired
	private BancoRepository repository;
	
	@Transactional(readOnly = true)
	public List<BancoDTO> findAll() {
		
		List<Banco> list =  repository.findAll();
		
		return list.stream().map(x -> new BancoDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public BancoDTO findById(Long id) {
		
		Optional<Banco> obj = repository.findById(id);
		
		Banco entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new BancoDTO(entity);
	}

}

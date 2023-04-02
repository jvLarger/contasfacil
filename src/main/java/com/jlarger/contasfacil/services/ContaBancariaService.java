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

import com.jlarger.contasfacil.dto.ContaBancariaDTO;
import com.jlarger.contasfacil.entities.Banco;
import com.jlarger.contasfacil.entities.ContaBancaria;
import com.jlarger.contasfacil.entities.Usuario;
import com.jlarger.contasfacil.repositories.BancoRepository;
import com.jlarger.contasfacil.repositories.ContaBancariaRepository;
import com.jlarger.contasfacil.services.exceptions.DatabaseException;
import com.jlarger.contasfacil.services.exceptions.ResourceNotFoundException;
import com.jlarger.contasfacil.util.ServiceLocator;

@Service
public class ContaBancariaService {

	@Autowired
	private ContaBancariaRepository repository;
	
	@Autowired
	private BancoRepository bancoRepository;
	
	@Transactional(readOnly = true)
	public List<ContaBancariaDTO> findAll(Pageable pageable) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		List<ContaBancaria> list =  repository.findAllContasBancariasByUser(usuarioLogado.getId());
		
		return list.stream().map(x -> new ContaBancariaDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ContaBancariaDTO findById(Long id) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		Optional<ContaBancaria> obj = repository.findByIdAndUsuarioId(id, usuarioLogado.getId());
		
		ContaBancaria entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new ContaBancariaDTO(entity);
	}

	@Transactional
	public ContaBancariaDTO insert(ContaBancariaDTO dto) {
		
		Optional<Banco> bancoOptional = bancoRepository.findById(dto.getBanco().getId());
		
		Banco banco = bancoOptional.orElseThrow(() -> new ResourceNotFoundException("Banco não encontrado"));
		
		ContaBancaria entity = new ContaBancaria();
		entity.setNmNumeroConta(dto.getNmNumeroConta());
		entity.setTxtDetalhes(dto.getTxtDetalhes());
		entity.setVlrLimiteConta(dto.getVlrLimiteConta());
		entity.setBanco(banco);
		entity.setUsuario(ServiceLocator.getUsuarioLogado());
		
		entity = repository.save(entity);
		
		return new ContaBancariaDTO(entity);
	}

	@Transactional
	public ContaBancariaDTO update(Long id, ContaBancariaDTO dto) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		Optional<Banco> bancoOptional = bancoRepository.findById(dto.getBanco().getId());
		
		Banco banco = bancoOptional.orElseThrow(() -> new ResourceNotFoundException("Banco não encontrado"));
		
		Optional<ContaBancaria> obj = repository.findByIdAndUsuarioId(id, usuarioLogado.getId());
		
		ContaBancaria entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		entity.setNmNumeroConta(dto.getNmNumeroConta());
		entity.setTxtDetalhes(dto.getTxtDetalhes());
		entity.setVlrLimiteConta(dto.getVlrLimiteConta());
		entity.setBanco(banco);
		entity = repository.save(entity);
		
		return new ContaBancariaDTO(entity);
	}

	public void delete(Long id) {
		
		try {
			
			Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
			
			Optional<ContaBancaria> obj = repository.findByIdAndUsuarioId(id, usuarioLogado.getId());
			
			ContaBancaria entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
			
			repository.deleteById(entity.getId());
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}
}

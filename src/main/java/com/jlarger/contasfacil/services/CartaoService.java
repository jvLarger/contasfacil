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

import com.jlarger.contasfacil.dto.CartaoDTO;
import com.jlarger.contasfacil.entities.Cartao;
import com.jlarger.contasfacil.entities.ContaBancaria;
import com.jlarger.contasfacil.entities.Usuario;
import com.jlarger.contasfacil.repositories.CartaoRepository;
import com.jlarger.contasfacil.repositories.ContaBancariaRepository;
import com.jlarger.contasfacil.services.exceptions.DatabaseException;
import com.jlarger.contasfacil.services.exceptions.ResourceNotFoundException;
import com.jlarger.contasfacil.util.ServiceLocator;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository repository;
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	@Transactional(readOnly = true)
	public List<CartaoDTO> findAll(Pageable pageable) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		List<Cartao> list =  repository.findAllCartoesByUser(usuarioLogado.getId());
		
		return list.stream().map(x -> new CartaoDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CartaoDTO findById(Long id) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		Optional<Cartao> obj = repository.findByIdAndUsuarioId(id, usuarioLogado.getId());
		
		Cartao entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new CartaoDTO(entity);
	}

	@Transactional
	public CartaoDTO insert(CartaoDTO dto) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		Optional<ContaBancaria> optionalContaBancaria = contaBancariaRepository.findByIdAndUsuarioId(dto.getContaBancaria().getId(), usuarioLogado.getId());
		
		ContaBancaria contaBancaria = optionalContaBancaria.orElseThrow(() -> new ResourceNotFoundException("Conta Bancária não encontrada."));
		
		Cartao entity = new Cartao();
		entity.setNmApelidoCartao(dto.getNmApelidoCartao());
		entity.setNrDiaFechamentoFatura(dto.getNrDiaFechamentoFatura());
		entity.setNrDiaVencimentoFatura(dto.getNrDiaVencimentoFatura());
		entity.setTpCartao(dto.getTpCartao());
		entity.setVlrLimite(dto.getVlrLimite());
		entity.setContaBancaria(contaBancaria);
		entity.setUsuario(ServiceLocator.getUsuarioLogado());
		
		entity = repository.save(entity);
		
		return new CartaoDTO(entity);
	}

	@Transactional
	public CartaoDTO update(Long id, CartaoDTO dto) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		Optional<ContaBancaria> optionalContaBancaria = contaBancariaRepository.findByIdAndUsuarioId(dto.getContaBancaria().getId(), usuarioLogado.getId());
		
		ContaBancaria contaBancaria = optionalContaBancaria.orElseThrow(() -> new ResourceNotFoundException("Conta Bancária não encontrada."));
		
		Optional<Cartao> obj = repository.findByIdAndUsuarioId(id, usuarioLogado.getId());
		
		Cartao entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		entity.setNmApelidoCartao(dto.getNmApelidoCartao());
		entity.setNrDiaFechamentoFatura(dto.getNrDiaFechamentoFatura());
		entity.setNrDiaVencimentoFatura(dto.getNrDiaVencimentoFatura());
		entity.setTpCartao(dto.getTpCartao());
		entity.setVlrLimite(dto.getVlrLimite());
		entity.setContaBancaria(contaBancaria);
		entity.setUsuario(ServiceLocator.getUsuarioLogado());
		entity = repository.save(entity);
		
		return new CartaoDTO(entity);
	}

	public void delete(Long id) {
		
		try {
			
			Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
			
			Optional<Cartao> obj = repository.findByIdAndUsuarioId(id, usuarioLogado.getId());
			
			Cartao entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
			
			repository.deleteById(entity.getId());
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}
}

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

import com.jlarger.contasfacil.dto.LancamentoDTO;
import com.jlarger.contasfacil.entities.Categoria;
import com.jlarger.contasfacil.entities.ContaBancaria;
import com.jlarger.contasfacil.entities.Lancamento;
import com.jlarger.contasfacil.entities.Usuario;
import com.jlarger.contasfacil.repositories.CategoriaRepository;
import com.jlarger.contasfacil.repositories.ContaBancariaRepository;
import com.jlarger.contasfacil.repositories.LancamentoRepository;
import com.jlarger.contasfacil.services.exceptions.DatabaseException;
import com.jlarger.contasfacil.services.exceptions.ResourceNotFoundException;
import com.jlarger.contasfacil.util.ServiceLocator;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	@Transactional(readOnly = true)
	public List<LancamentoDTO> findAll(Pageable pageable) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		List<Lancamento> list =  repository.findAllLancamentosByUser(usuarioLogado.getId());
		
		return list.stream().map(x -> new LancamentoDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public LancamentoDTO findById(Long id) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		Optional<Lancamento> obj = repository.findByIdAndUsuarioId(id, usuarioLogado.getId());
		
		Lancamento entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new LancamentoDTO(entity);
	}

	@Transactional
	public LancamentoDTO insert(LancamentoDTO dto) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		Optional<Categoria> optionalCategoria = categoriaRepository.findByIdAndUsuarioId(dto.getCategoria().getId(), usuarioLogado.getId());
		
		Categoria categoria = optionalCategoria.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada."));
		
		Optional<ContaBancaria> optionalContaBancaria = contaBancariaRepository.findByIdAndUsuarioId(dto.getContaBancaria().getId(), usuarioLogado.getId());
		
		ContaBancaria contaBancaria = optionalContaBancaria.orElseThrow(() -> new ResourceNotFoundException("Conta Bancária não encontrada."));
		
		Lancamento entity = new Lancamento();
		entity.setContaBancaria(contaBancaria);
		entity.setCategoria(categoria);
		entity.setUsuario(usuarioLogado);
		entity.setTpNatureza(dto.getTpNatureza());
		entity.setNrParcela(dto.getNrParcela());
		entity.setDtVencimento(dto.getDtVencimento());
		entity.setVlrMovimento(dto.getVlrMovimento());
		entity.setVlrPago(0.0);
		entity.setNmInformacaoPrincipal(dto.getNmInformacaoPrincipal());
		entity.setTxtDetalhes(dto.getTxtDetalhes());
		
		entity = repository.save(entity);
		
		return new LancamentoDTO(entity);
	}

	@Transactional
	public LancamentoDTO update(Long id, LancamentoDTO dto) {
		
		Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
		
		Optional<Categoria> optionalCategoria = categoriaRepository.findByIdAndUsuarioId(dto.getCategoria().getId(), usuarioLogado.getId());
		
		Categoria categoria = optionalCategoria.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada."));
		
		Optional<ContaBancaria> optionalContaBancaria = contaBancariaRepository.findByIdAndUsuarioId(dto.getContaBancaria().getId(), usuarioLogado.getId());
		
		ContaBancaria contaBancaria = optionalContaBancaria.orElseThrow(() -> new ResourceNotFoundException("Conta Bancária não encontrada."));
		
		Optional<Lancamento> obj = repository.findByIdAndUsuarioId(id, usuarioLogado.getId());
		
		Lancamento entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		entity.setContaBancaria(contaBancaria);
		entity.setCategoria(categoria);
		entity.setUsuario(usuarioLogado);
		entity.setTpNatureza(dto.getTpNatureza());
		entity.setNrParcela(dto.getNrParcela());
		entity.setDtVencimento(dto.getDtVencimento());
		entity.setVlrMovimento(dto.getVlrMovimento());
		entity.setNmInformacaoPrincipal(dto.getNmInformacaoPrincipal());
		entity.setTxtDetalhes(dto.getTxtDetalhes());
		
		entity = repository.save(entity);
		
		return new LancamentoDTO(entity);
	}

	public void delete(Long id) {
		
		try {
			
			Usuario usuarioLogado = ServiceLocator.getUsuarioLogado();
			
			Optional<Lancamento> obj = repository.findByIdAndUsuarioId(id, usuarioLogado.getId());
			
			Lancamento entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
			
			repository.deleteById(entity.getId());
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}
}

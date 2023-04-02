package com.jlarger.contasfacil.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jlarger.contasfacil.dto.BancoDTO;
import com.jlarger.contasfacil.services.BancoService;

@RestController
@RequestMapping(value = "api/bancos")
public class BancoResource {
	
	@Autowired
	private BancoService service;
	
	@GetMapping
	public ResponseEntity<List<BancoDTO>> findAll() {
		List<BancoDTO> list = service.findAll();		
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BancoDTO> findById(@PathVariable Long id) {
		BancoDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
}

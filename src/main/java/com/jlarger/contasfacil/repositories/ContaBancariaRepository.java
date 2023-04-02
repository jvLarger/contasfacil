package com.jlarger.contasfacil.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jlarger.contasfacil.entities.ContaBancaria;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
	
    @Query("SELECT c FROM ContaBancaria c WHERE c.usuario.id = :usuarioId")
    List<ContaBancaria> findAllContasBancariasByUser(Long usuarioId);
	
    @Query("SELECT c FROM ContaBancaria c WHERE c.id = :contaBancariaId AND c.usuario.id = :usuarioId")
    Optional<ContaBancaria> findByIdAndUsuarioId(Long contaBancariaId, Long usuarioId);
    
}
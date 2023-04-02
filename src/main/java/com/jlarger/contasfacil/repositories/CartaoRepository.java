package com.jlarger.contasfacil.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jlarger.contasfacil.entities.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
	
    @Query("SELECT c FROM Cartao c WHERE c.usuario.id = :usuarioId")
    List<Cartao> findAllCartoesByUser(Long usuarioId);
	
    @Query("SELECT c FROM Cartao c WHERE c.id = :cartaoId AND c.usuario.id = :usuarioId")
    Optional<Cartao> findByIdAndUsuarioId(Long cartaoId, Long usuarioId);
    
}
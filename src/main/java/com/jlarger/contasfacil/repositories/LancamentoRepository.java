package com.jlarger.contasfacil.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jlarger.contasfacil.entities.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    
	
    @Query("SELECT l FROM Lancamento l WHERE l.usuario.id = :usuarioId")
    List<Lancamento> findAllLancamentosByUser(Long usuarioId);
	
    @Query("SELECT l FROM Lancamento l WHERE l.id = :lancamentoId AND l.usuario.id = :usuarioId")
    Optional<Lancamento> findByIdAndUsuarioId(Long lancamentoId, Long usuarioId);

}
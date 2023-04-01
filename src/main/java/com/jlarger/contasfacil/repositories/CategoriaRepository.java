package com.jlarger.contasfacil.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jlarger.contasfacil.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
    @Query("SELECT c FROM Categoria c WHERE c.usuario.id = :usuarioId")
    List<Categoria> findAllCategoriasByUser(Long usuarioId);
	
    @Query("SELECT c FROM Categoria c WHERE c.id = :categoriaId AND c.usuario.id = :usuarioId")
    Optional<Categoria> findByIdAndUsuarioId(Long categoriaId, Long usuarioId);
    
}
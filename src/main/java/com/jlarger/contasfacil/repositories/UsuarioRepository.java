package com.jlarger.contasfacil.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jlarger.contasfacil.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  
	Optional<Usuario> findByNmUsuario(String nmUsuario);

	Boolean existsByNmUsuario(String nmUsuario);

	Boolean existsByNmEmail(String nmEmail);
}
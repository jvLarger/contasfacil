package com.jlarger.contasfacil.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jlarger.contasfacil.entities.Perfil;
import com.jlarger.contasfacil.entities.type.TpPerfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	Optional<Perfil> findByTpPerfil(TpPerfil tpPerfil);  
}
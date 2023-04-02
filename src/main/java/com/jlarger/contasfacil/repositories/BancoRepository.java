package com.jlarger.contasfacil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jlarger.contasfacil.entities.Banco;

public interface BancoRepository extends JpaRepository<Banco, Long> {
    
}
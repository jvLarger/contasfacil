package com.jlarger.contasfacil.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jlarger.contasfacil.entities.Usuario;
import com.jlarger.contasfacil.security.services.UserDetailsImpl;

public class ServiceLocator {
	
	public static Usuario getUsuarioLogado() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		Usuario usuario = new Usuario();
		usuario.setId(userDetails.getId());
		usuario.setNmUsuario(userDetails.getUsername());
		usuario.setNmEmail(userDetails.getEmail());
		usuario.setNmSenha(userDetails.getPassword());
		
		return usuario;
	}
	
}
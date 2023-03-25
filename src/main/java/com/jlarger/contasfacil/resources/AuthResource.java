package com.jlarger.contasfacil.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jlarger.contasfacil.dto.JwtResponse;
import com.jlarger.contasfacil.dto.MessageResponse;
import com.jlarger.contasfacil.dto.UsuarioDTO;
import com.jlarger.contasfacil.entities.Usuario;
import com.jlarger.contasfacil.repositories.PerfilRepository;
import com.jlarger.contasfacil.repositories.UsuarioRepository;
import com.jlarger.contasfacil.security.jwt.JwtUtils;
import com.jlarger.contasfacil.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthResource {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioRepository userRepository;

	@Autowired
	PerfilRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody UsuarioDTO usuarioDTO) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioDTO.getNmUsuario(), usuarioDTO.getNmSenha()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody UsuarioDTO usuarioDTO) {
		
		if (userRepository.existsByNmUsuario(usuarioDTO.getNmUsuario())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByNmEmail(usuarioDTO.getNmEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		
		Usuario user = new Usuario();
		user.setNmUsuario(usuarioDTO.getNmUsuario());
		user.setNmEmail(usuarioDTO.getNmEmail());
		user.setNmSenha( encoder.encode(usuarioDTO.getNmSenha()));
		/*
		Set<Perfil> roles = new HashSet<>();

		if (strRoles == null) {
			Perfil userRole = roleRepository.findByTpPerfil(TpPerfil.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Perfil adminRole = roleRepository.findByTpPerfil(TpPerfil.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Perfil modRole = roleRepository.findByTpPerfil(TpPerfil.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Perfil userRole = roleRepository.findByTpPerfil(TpPerfil.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
	user.setRoles(roles);
		 */
		
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}

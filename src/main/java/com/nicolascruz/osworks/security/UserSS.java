package com.nicolascruz.osworks.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nicolascruz.osworks.domain.model.Perfil;

public class UserSS implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserSS() {
		
	}
	
	public UserSS(Long id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.descricao())).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// Caso queira que a conta expire, adicionar o código aqui
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// Caso queira que a conta bloqueie
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// Caso queira que as credenciais estão expiradas
		return true;
	}

	@Override
	public boolean isEnabled() {
		// Diz que o usuario esta ativo
		return true;
	}
	
	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.descricao())); //checa se o usuario tem o perfil informado
	}

}
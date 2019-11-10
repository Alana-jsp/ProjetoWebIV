package com.projetoalana.model.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public enum PerfilUsuarioEnum implements GrantedAuthority{

	
	VENDEDOR,
	ADMINISTRADOR;
	
	// static final o valor declarado não pode ser mudado
	public static final Integer VENDEDOR_VALUE = 0;
	public static final Integer ADMINISTRADOR_VALUE = 1;
	
	@Override
	public String getAuthority()
	{
		return this.name();
	}

	/**
	 * @return
	 */
	public Set<GrantedAuthority> getAuthorities()
	{
		final Set<GrantedAuthority> authorities = new HashSet<>();

		authorities.add( this );


		return authorities;
	}
}

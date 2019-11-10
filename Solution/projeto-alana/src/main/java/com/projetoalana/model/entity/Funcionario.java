package com.projetoalana.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario extends AbstractEntity implements Serializable, UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Atributos 
	 */
	@Column(nullable = false, length = 100)
	@NotBlank
	private String nome;
	
	@Column(nullable = false, length = 100, unique = true)
	@Size(max = 100)
	@NotBlank
	private String email;
    
	@Column(nullable = false, length = 100)
	@Size(max = 100)
	@NotBlank
	private String senha;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private PerfilUsuarioEnum perfil;
	

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
    
    /*
     * Metodos
     */
	@Override
	@Transient
	public Set<GrantedAuthority> getAuthorities()
	{
		final Set<GrantedAuthority> authorities = new HashSet<>();

		if ( this.perfil == null )
		{
			return null;
		}
		
		authorities.addAll( this.perfil.getAuthorities() );

		return authorities;
	}
}

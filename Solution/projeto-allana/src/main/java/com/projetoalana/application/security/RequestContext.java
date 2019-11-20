package com.projetoalana.application.security;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;

import com.projetoalana.model.entity.Funcionario;

/**
 *
 */
public abstract class RequestContext
{
	/*-------------------------------------------------------------------
	 * 		 						BEHAVIORS
	 *-------------------------------------------------------------------*/

	/**
	 * @return
	 */
	public static Optional<Funcionario> currentUser()
	{
		return Optional.ofNullable( SecurityContextHolder.getContext().getAuthentication() )
				.map( auth -> auth.getPrincipal() instanceof Funcionario ? (Funcionario) auth.getPrincipal() : null );
	}


}

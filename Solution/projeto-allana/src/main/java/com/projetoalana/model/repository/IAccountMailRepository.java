package com.projetoalana.model.repository;

import java.util.concurrent.Future;

import com.projetoalana.model.entity.Funcionario;


/**
 *
 */
public interface IAccountMailRepository
{
	/*-------------------------------------------------------------------
     *                          BEHAVIORS
     *-------------------------------------------------------------------*/

	/**
	 * Envia email notificando criação de conta do usuário
	 * @param usuario
	 * @return
	 */
	Future<Void> sendNewUserAccount( Funcionario funcionario );

	/**
	 *
	 * @param user
	 * @return
	 */
	Future<Void> sendPasswordReseted( Funcionario funcionario );

	/**
	 *
	 * @param user
	 * @return
	 */
	Future<Void> sendPasswordResetNotice( Funcionario funcionario );

	/**
	 *
	 * @param user
	 * @return
	 */
	Future<Void> sendAccountActivated( Funcionario funcionario );

	/**
	 *
	 * @param user
	 * @return
	 */
	Future<Void> sendAccountInactivated( Funcionario funcionario );
	
	/**
	 *
	 * @param user
	 * @return
	 */
	Future<Void> sendResetPassword( Funcionario funcionario );

}
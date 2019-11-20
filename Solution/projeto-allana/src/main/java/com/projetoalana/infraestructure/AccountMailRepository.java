package com.projetoalana.infraestructure;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import com.projetoalana.application.configuration.settings.AppSettings;
import com.projetoalana.model.entity.Funcionario;
import com.projetoalana.model.repository.IAccountMailRepository;

/**
 *
 */
@Component
public class AccountMailRepository implements IAccountMailRepository {
	/*-------------------------------------------------------------------
	 *                          ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 *
	 */
	private final Mailer mailer;
	/**
	 *
	 */
	private final AppSettings appSettings;

	@Autowired
	public AccountMailRepository(Mailer mailer, AppSettings appSettings) {
		this.mailer = mailer;
		this.appSettings = appSettings;
	}

	/*-------------------------------------------------------------------
	 *                          BEHAVIORS
	 *-------------------------------------------------------------------*/

	/**
	 * Envia email notificando criação de conta do usuário
	 * Envia token para ativação da conta
	 * @param usuario
	 * @return
	 */
	@Async
	@Override
	public Future<Void> sendNewUserAccount(Funcionario funcionario) {
		Context context = new Context();
		context.setVariable("user", funcionario);
		String link = appSettings.getExternalUrl() + "/authentication#/ativar-conta/";
		context.setVariable("link", link);
		Mailer.MessagePreparer preparer = (messageHelper, mimeMessage) -> {
			messageHelper.setSubject("Nova conta de usuário");
			messageHelper.setTo(funcionario.getEmail().toLowerCase());
			messageHelper.setFrom(appSettings.getMailFrom());
		};
		return mailer.sendMessage(

				"mail/new-account", context, preparer);
	}

	

	/**
	 * Envia email notificando que a conta foi ativada
	 * @param usuario
	 * @return
	 */
	@Override
	public Future<Void> sendAccountActivated(Funcionario funcionario) {
		Context context = new Context();
		context.setVariable("user", funcionario);
		String link = appSettings.getExternalUrl();
		context.setVariable("link", link);

		Mailer.MessagePreparer preparer = (messageHelper, mimeMessage) -> {

			messageHelper.setSubject("Conta ativada");
			messageHelper.setTo(funcionario.getEmail().toLowerCase());
			messageHelper.setFrom(appSettings.getMailFrom());
		};

		return mailer.sendMessage("mail/account-activated", context, preparer);
	}
	
	/**
	 * Envia email notificando que houve um pedido de redefinição de senha
	 * Com token para redefinir
	 */
	@Override
	public Future<Void> sendResetPassword(Funcionario funcionario) {
		Context context = new Context();
		context.setVariable("user", funcionario);
		String link = appSettings.getExternalUrl() + "/authentication#/redefinir-senha/";
		context.setVariable("link", link);

		Mailer.MessagePreparer preparer = (messageHelper, mimeMessage) -> {

			messageHelper.setSubject("Recuperar Senha");
			messageHelper.setTo(funcionario.getEmail().toLowerCase());
			messageHelper.setFrom(appSettings.getMailFrom());
		};

		return mailer.sendMessage("mail/reset-password", context, preparer);
	}
	
	

	/**
	 *
	 * @param usuario
	 * @return
	 */
	@Override
	public Future<Void> sendAccountInactivated(Funcionario funcionario) {
		Context context = new Context();
		context.setVariable("user", funcionario);

		Mailer.MessagePreparer preparer = (messageHelper, mimeMessage) -> {
			messageHelper.setSubject("Inativação de conta");
			messageHelper.setTo(funcionario.getEmail().toLowerCase());
			messageHelper.setFrom(appSettings.getMailFrom());
		};
		return mailer.sendMessage("mail/account-inactivated", context, preparer);
	}

	
	
	/**
	 *
	 * @param usuario
	 * @return
	 */
	@Override
	public Future<Void> sendPasswordReseted(Funcionario funcionario) {
		return new AsyncResult<>(null);
	}

	/**
	 *
	 * @param usuario
	 * @return
	 */
	@Override
	public Future<Void> sendPasswordResetNotice(Funcionario funcionario) {
		return new AsyncResult<>(null);
	}

}
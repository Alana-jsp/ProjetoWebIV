package com.projetoalana.model.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.MailSendException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.projetoalana.model.entity.Funcionario;
import com.projetoalana.model.repository.FuncionarioRepository;
import com.projetoalana.application.security.*;
import com.projetoalana.application.configuration.settings.*;
import com.projetoalana.model.repository.*;



@Service
@Transactional 
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IAccountMailRepository accountMailRepository;

	@Autowired
	private AppSettings appSettings;

	
	/*
	 * Servico para realizar um novo cadastro de funcionario
	 * 
	 */
	public Funcionario cadastrarFuncionario(Funcionario funcionario) {
		return this.funcionarioRepository.save(funcionario);
	}
	
	/*
	 * Servico para atualizar um funcionario
	 * 
	 */
	public Funcionario atualizarFuncionario(Funcionario funcionario) {
		return this.funcionarioRepository.save(funcionario);
	}
	
	/*
	 * Serviço de listagem de Funcionarios
	 * 
	 */
	public List<Funcionario> listaFuncionario(){
		return this.funcionarioRepository.findAll();
	}
	
	/*
	 * Serviço de detalhar um funcionario
	 * Apartir do seu codigo
	 * caso não seja encontrado ele retorna uma mensagem de erro
	 */
	public Funcionario detalhaFuncioanrio(long codigo) {
		Funcionario funcionario = this.funcionarioRepository.findById(codigo).orElse(null);
		Assert.notNull(funcionario, "Codigo"+codigo+"não encontrado");
	
		return funcionario;
	}
	
	/*
	 * Serviço para remover um funcionario
	 * a partir do seu codigo
	 * 
	 */
	public void removeFuncionario(long codigo) {
		this.funcionarioRepository.deleteById(codigo);
	}
	
	/*
	 * Serviço que lista os funcionario por filtro
	 */
	public Page<Funcionario> listarFuncionarioPorFiltros(String nome, String email,String senha,PageRequest pageable){
		return this.funcionarioRepository.findByFilters(nome, email, senha, pageable);
	}
	
	public Funcionario cadastrarfuncionario(Funcionario funcionario)
	{
		//seta o funcionario como inativo
		funcionario.setAtivo( false );
		
		//gera senha aleatória
		funcionario.generatePassword();
		funcionario.setSenha( this.passwordEncoder.encode(funcionario.getSenha()));
		
		//gera um token para ativação da conta
		funcionario.generateAccountActivateToken();
		
		funcionario = this.funcionarioRepository.save(funcionario);
		try
		{
			this.accountMailRepository.sendNewUserAccount(funcionario).get();
		}
		catch ( MailSendException | InterruptedException | ExecutionException e )
		{
			e.printStackTrace();
		}
		return funcionario;
	}
	
	/**
	 * Serviço para ativar um usuário para acesso a plataforma
	 * É chamado ao acessar o token accountActivateToken
	 * 
	 * @param senha
	 * @param confirmacaoSenha
	 * @param accountActivateToken
	 */
	public void ativarFuncionario( String senha, String confirmacaoSenha, String accountActivateToken )
	{
		OffsetDateTime dateTime = OffsetDateTime.now();
		Assert.notNull( accountActivateToken, "Token inválido." );
		
		Assert.isTrue( senha.equals( confirmacaoSenha ),
				"As senhas não conferem." );
		
		Funcionario funcionarioByToken = this.funcionarioRepository.findByAccountActivateToken( accountActivateToken ).orElse( null );
		Assert.notNull(funcionarioByToken, "Token inválido");
		Assert.isTrue(funcionarioByToken.getAccountActivateTokenExpiration().isAfter( dateTime ), "Token venceu." );
		
		
		funcionarioByToken.setAtivo( true );
		funcionarioByToken.setSenha( this.passwordEncoder.encode( senha ) );
		
		funcionarioByToken = this.funcionarioRepository.save(funcionarioByToken );
		try
		{
			this.accountMailRepository.sendAccountActivated(funcionarioByToken );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

	}
	
	/**
	 * Serviço que envia um e-mail para recuperar a senha do usuário
	 * Gera um token com um link para acesso a redefinição de senha
	 *
	 * @param email
	 */
	public void enviarEmailRecuperarSenhaFuncionario(String email)
	{
		Funcionario funcionario = this.funcionarioRepository.findByEmailIgnoreCase(email);

		Assert.notNull(funcionario, "E-mail inválido." );

		funcionario.generatePasswordResetToken();
		funcionario.setPasswordResetTokenExpiration( OffsetDateTime.now().plusDays( 1 ) );
		funcionario = this.funcionarioRepository.save(funcionario);

		try
		{
			this.accountMailRepository.sendResetPassword(funcionario);
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

	}
	
	/**
	 * Serviço para redifinir a senha do usuário
	 *
	 * @param senha
	 * @param confirmacaoSenha
	 * @param passwordResetToken
	 * @return
	 */
	public Funcionario redefinirSenha( String senha, String confirmacaoSenha, String passwordResetToken )
	{
		OffsetDateTime dateTime = OffsetDateTime.now();
		Assert.notNull( passwordResetToken, "Token inválido." );
		Assert.isTrue( senha.equals( confirmacaoSenha ), "Senhas não conferem." );
		
		Funcionario funcionario = this.funcionarioRepository.findByPasswordResetToken( passwordResetToken ).orElse( null );
		
		Assert.notNull(funcionario, "Token inválido");
		Assert.isTrue(funcionario.getPasswordResetTokenExpiration().isAfter( dateTime ), "Token venceu." );
		funcionario.setSenha( this.passwordEncoder.encode( senha ) );
		return this.funcionarioRepository.save(funcionario);
	}


	/**
	 * Serviço que trás o usuário logado
	 *
	 * @return
	 */
	public Funcionario getAuthenticatedUser()
	{
		return RequestContext.currentUser().orElse( null );
	}

	/**
	 * Serviço para "alterar minha conta"
	 *
	 * @param user
	 */
	public Funcionario alterarMinhaConta(Funcionario funcionario, String senha, String confirmationPassword )
	{
		if (senha != null && confirmationPassword != null )
		{
			//verificamos se não é ''
			Assert.hasText( senha, "Informe sua senha." );
			Assert.hasText( confirmationPassword, "Informe a configuração de senha." );

			//verificamos a senha se é igual a confirmação
			Assert.isTrue(senha.equals( confirmationPassword ),
					"As senhas não conferem." );
			funcionario.setSenha( this.passwordEncoder.encode(senha) );
		}
		return this.funcionarioRepository.save(funcionario);
	}

	
}

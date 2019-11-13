package com.projetoalana.model.service;


import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import com.projetoalana.model.entity.Funcionario;
import com.projetoalana.model.entity.PerfilUsuarioEnum;
import com.projetoalana.model.repository.FuncionarioRepository;


public class FuncionarioServiceTests extends AbstractIntegrationTests{

	//MustPass -> O teste é feito para passar
	//MustFail -> O teste é feito para falhar
	
	@Autowired
	private PasswordEncoder passwordEncode;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	/*
	 * ------------Teste Para Cadastro de Funcionários----------------------
	 */
	@WithUserDetails("allanahwayoung@gmail.com")
	@Test
	@Sql({"/dataset/truncate.sql",
		  "/dataset/funcionario.sql"})
	public void cadastrarfuncionarioMustPass() {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Alana");
		funcionario.setEmail("allanahwayoung@gmail.com");
		funcionario.setSenha("1234");
		funcionario.setPerfil(PerfilUsuarioEnum.VENDEDOR);
		this.funcionarioService.cadastrarfuncionario(funcionario);
		Assert.assertNotNull(funcionario);
		Assert.assertNotNull(funcionario.getCodigo());
		
	}
	
	//expected faz com que o metodo de teste seja bem sucedido
	//ConstraintViolationException cria um relatorio de violação de restrição
	@Test(expected = ConstraintViolationException.class) 
	@Sql({"/dataset/truncate.sql",
		  "/dataset/funcionario.sql"})
	public void cadastrarFuncionarioMustFailNomeEmBranco() {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("");
        funcionario.setEmail("alanahwayoung@gmail.com");
        funcionario.setSenha("1234");
		funcionario.setPerfil(PerfilUsuarioEnum.VENDEDOR);

		this.funcionarioService.cadastrarFuncionario(funcionario);
	}
	//Exceção de violação de integridade de dados.
	@Test(expected = DataIntegrityViolationException.class)
	@Sql({"/dataset/truncate.sql",
		  "/dataset/funcionario.sql"})
	public void cadastrarFuncionarioMustFailEmailDuplicado() {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Jesica");
		funcionario.setEmail("jessica@gmail.com");
		funcionario.setSenha("123456");
		funcionario.setPerfil(PerfilUsuarioEnum.ADMINISTRADOR);
		
		funcionarioService.cadastrarFuncionario(funcionario);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Sql({"/dataset/truncate.sql",
		  "/dataset/funcionario.sql"})
	public void cadastrarFuncionarioMustFailSenhaEmBranco() {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Alana");
		funcionario.setEmail("allanahwayoung@gmail.com");
		funcionario.setSenha("");
		funcionario.setPerfil(PerfilUsuarioEnum.VENDEDOR);
		
		this.funcionarioService.cadastrarFuncionario(funcionario);
		
	}
	/*
	 * Test para ativar um funcionario no sistema
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		  "dataset/funcionario.sql"})
	public void ativarFuncioanrioMustPass() {
		this.funcionarioService.ativarFuncionario("1234", "1234", "");
		
		Funcionario funcionarioAtivo = this.funcionarioRepository.findByEmailIgnoreCase("allanahwayoung@gmail.com");
		Assert.assertEquals(true, funcionarioAtivo.getAtivo());
	}
	
	/*
	 * ------------------------Teste para funcionario que esqueceu a senha------------------------
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		  "/dataset/funcionario.sql"})
	public void emailParaRecuperarSenhaMustPass() {
		this.funcionarioService.enviarEmailRecuperarSenhaFuncionario("allanahwayoung@gmail.com");
		Funcionario funcionario = this.funcionarioRepository.findByEmailIgnoreCase("allanahwayoung@gmail.com");
		
		Assert.assertNotNull(funcionario);
		Assert.assertNotNull(funcionario.getPasswordResetToken());
		Assert.assertNotNull(funcionario.getAccountActivateTokenExpiration());
	}
	
	
}

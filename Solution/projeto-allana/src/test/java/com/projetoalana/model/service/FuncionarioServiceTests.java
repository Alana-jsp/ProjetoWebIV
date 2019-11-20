package com.projetoalana.model.service;


import java.util.List;

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
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	/*
	 * ------------Teste Para Cadastro de Funcionários----------------------
	 */
	
	@Test
	@WithUserDetails("allanahwayoung@gmail.com")
	@Sql({"/dataset/truncate.sql",
	     "/dataset/funcionario.sql"})
	public void cadastrarFuncionarioMustPass() {
	    Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Allana");
		funcionario.setEmail("allanahwayong@gmail.com");
		//funcionario.setSenha("1234");
		funcionario.setPerfil(PerfilUsuarioEnum.ADMINISTRADOR);
		this.funcionarioService.cadastrarFuncionario(funcionario);
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
		  "/dataset/funcionario.sql"})
	public void ativarFuncionarioMustPass() {
		this.funcionarioService.ativarFuncionario("1234", "1234", "f786c907-032e-451b-ac93-8508dec75a3d");
		
		Funcionario funcionarioAtivo = this.funcionarioRepository.findByEmailIgnoreCase("allanahwayoung@gmail.com");
		Assert.assertEquals(true, funcionarioAtivo.getAtivo());
	}
	
	/*
	 * -------------------------Listagem de Funcionario--------------------------
	 * 
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		   "/dataset/funcionario.sql"})
	public void listarFuncionarioMustPass() {
		List<Funcionario> funcionarios = this.funcionarioService.listaFuncionario();
		Assert.assertEquals(funcionarios.size(), 1001);
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
	
	/*
	 * ---------------------------- Redefinir senha do Funcionario----------------------
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		  "/dataset/funcionario.sql"})
	public void redefinirSenhaMustPass() {
		
		this.funcionarioService.redefinirSenha("12345", "12345", "f786c907-032e-451b-ac93-8508dec75a3d");
		
	}
	
	/*
	 * -----------------------Pegar Funcionario Autenticado
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		  "/dataset/funcionario.sql"})
	public void pegarFuncionarioAutenticadoMustPass() {
		//Funcionario funcionario = this.funcionarioService.getAuthenticatedUser();
		Funcionario funcionario = this.funcionarioRepository.findById(1001L).orElse(null);
		Assert.assertNotNull(funcionario);
		Assert.assertNotNull(funcionario.getCodigo());
	}
	
	
	
}

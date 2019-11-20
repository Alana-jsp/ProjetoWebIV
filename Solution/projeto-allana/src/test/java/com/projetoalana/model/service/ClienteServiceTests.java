package com.projetoalana.model.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.projetoalana.model.entity.Cliente;
import com.projetoalana.model.repository.ClienteRepository;



public class ClienteServiceTests extends AbstractIntegrationTests {
	
	//permite que o spring injete as dependencias nesta classe
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	/*
	 * ----------------Cadastro Cliente---------------
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		  "/dataset/cliente.sql"})
	public void cadastrarClienteMustPass() {
		final Cliente cliente = new Cliente();
		cliente.setNome("Aparecida da Silva");
		
		clienteService.cadastrarCliente(cliente);
		
		Assert.assertNotNull(cliente.getCodigo());
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Sql({"/dataset/truncate.sql",
		  "/dataset/cliente.sql"})
	public void cadastrarClienteMustFailNomeEmBranco() {
		Cliente cliente = new Cliente();
		
		cliente.setNome("");
		clienteService.cadastrarCliente(cliente);
		
	}
	
	/*
	 * -------------------Atualizar Cliente---------------
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		  "/dataset/cliente.sql"})
	public void atualizarClienteMustPass() {
		Cliente cliente = this.clienteRepository.findById(1001L).orElse(null);
		cliente.setNome("Allana Castro");
		
		clienteService.atualizarCliente(cliente);
		
	}
	
	@Test
	@Sql({"/dataset/truncate.sql",
		  "/dataset/cliente.sql"})
	public void atualizarClienteMustFailNomeEmBranco() {
		Cliente cliente = new Cliente();
		
		cliente.setNome("");
		this.clienteService.atualizarCliente(cliente);
	}
	
	/*
	 * ----------------Listar Clientes------------------------
	 */
	@Test 
	@Sql({"/dataset/truncate.sql",
		  "/dataset/cliente.sql"})
	public void listarClientesMustPass() {
		List<Cliente> clientes = this.clienteService.listaCliente();
		Assert.assertEquals(clientes.size(), 4);
	}
	
	/*
	 * ----------------Detalhar Cliente------------
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		  "/dataset/cliente.sql"})
	public void detalharClienteMustPass() {
		Cliente cliente = this.clienteService.detalhaCliente(1001L);
		
		Assert.assertNotNull(cliente);
		Assert.assertNotNull(cliente.getCodigo());
		Assert.assertNotNull(cliente.getNome(),"Sebastião");
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Sql({"/dataset/truncate.sql",
		  "/dataset/cliente.sql"})
	public void detalharClienteMustFailCodigoNaoExiste() {
		
		Cliente cliente = this.clienteService.detalhaCliente(2L);
	}
	
	/*
	 * -------------Excluir Cliente-------------------
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		  "/dataset/cliente.sql"})
	public void removeClienteMustPass() {
		this.clienteService.removeCliente(1003);
		
		Cliente cliente = this.clienteRepository.findById(1003L).orElse(null);
		
		Assert.assertNull(cliente);
	}
}

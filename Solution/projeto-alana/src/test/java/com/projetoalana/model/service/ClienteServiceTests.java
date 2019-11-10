package com.projetoalana.model.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.projetoalana.model.entity.Cliente;



public class ClienteServiceTests extends AbstractIntegrationTests {
	
	@Autowired
	private ClienteService clienteService;
	
	@Test
	public void cadastrarClienteMustPass() {
		final Cliente cliente = new Cliente();
		cliente.setNome("Aparecida da Silva");
		
		clienteService.cadastrarCliente(cliente);
		
		Assert.assertNotNull(cliente.getCodigo());
	}

}

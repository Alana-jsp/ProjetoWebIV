package com.projetoalana.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.projetoalana.model.entity.Cliente;
import com.projetoalana.model.repository.ClienteRepository;

@Service
@Transactional
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	/*
	 * Serviço para realizar um novo cadastro de cliente
	 */
	public Cliente cadastrarCliente(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	
	/*
	 * Serviço para atualizar um Cliente
	 */
	public Cliente atualizarCliente(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	
	/*
	 * Serviço para listar um Cliente
	 */
	public List<Cliente> listaCliente(){
		return this.clienteRepository.findAll();
	}
	
	/*
	 * Serviço de detalhar um cliente
	 * A partir do seu codigo
	 * caso não seja encontrado ele retorna uma mensagem de erro
	 */
	public Cliente detalhaCliente(long codigo) {
		Cliente cliente = this.clienteRepository.findById(codigo).orElse(null);
		Assert.notNull(cliente, "Codigo"+codigo+"não encontrado");
		
		return cliente;
	}
	
	/*
	 * Serviço para excluir um cliente
	 * A partir do seu codigo
	 */
	public void removecliente(long codigo) {
		this.clienteRepository.deleteById(codigo);
	}
}

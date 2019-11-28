package com.projetoalana.model.service;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.projetoalana.model.entity.Cliente;
import com.projetoalana.model.entity.Funcionario;
import com.projetoalana.model.entity.PagamentoEnum;
import com.projetoalana.model.entity.Venda;
import com.projetoalana.model.repository.ClienteRepository;
import com.projetoalana.model.repository.FuncionarioRepository;
import com.projetoalana.model.repository.VendaRepository;

public class VendaServiceTests {
	
	@Autowired
	private VendaService vendaService;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	
	
	@Autowired 
	private ClienteRepository clienteRepository;
	@Autowired 
	private FuncionarioRepository funcionarioRepository;
	/*
	 * ----------------------Teste Para Realizar a venda de um produto----------------------
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		   "/dataset/venda.sql",
		   "/dataset/funcionario.sql",
		    "/dataset/cliente.sql"})
	public void cadastrarVendaMustPass() {
		Venda venda = new Venda();
		
		venda.setQuantidade(2);
		venda.setDataHora(LocalDateTime.now());
		venda.setFormaPagamento(PagamentoEnum.DINHEIRO);
		venda.setValorTotal(25.00);
		Cliente cliente = this.clienteRepository.findById(1001L).orElse(null);
		Funcionario funcionario = this.funcionarioRepository.findById(1001L).orElse(null);
		venda.setFuncionario(funcionario);
		venda.setCliente(cliente);
		this.vendaService.cadastrarVenda(venda);

		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}

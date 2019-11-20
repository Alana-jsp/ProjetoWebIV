package com.projetoalana.model.service;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.test.context.jdbc.Sql;

import com.projetoalana.model.entity.Cliente;
import com.projetoalana.model.entity.Funcionario;
import com.projetoalana.model.entity.PagamentoEnum;
import com.projetoalana.model.entity.Venda;
import com.projetoalana.model.repository.ClienteRepository;
import com.projetoalana.model.repository.FuncionarioRepository;
import com.projetoalana.model.repository.ProdutoRepository;
import com.projetoalana.model.repository.VendaProdutoRepository;
import com.projetoalana.model.repository.VendaRepository;

public class VendaServiceTests {
	
	@Autowired
	private VendaService vendaService;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private VendaProdutoService vendaProdutoService;
	
	@Autowired
	private VendaProdutoRepository vendaProdutoRepository;
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired 
	private ClienteRepository clienteRepository;
	@Autowired 
	private FuncionarioRepository funcionarioRepository;
	/*
	 * ----------------------Teste Para Realizar a venda de um produto----------------------
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		   "/dataset/venda.sql"})
	public void CadastrarVendaMustPass() {
		Venda venda = new Venda();
		
	/*	Cliente cliente = this.clienteRepository.findById(1L).orElse(null);
		venda.setCliente(cliente);
		Funcionario funcionario = this.funcionarioRepository.findById(1L).orElse(null);
		venda.setFuncionario(funcionario);*/
		
		venda.setQuantidade(2);
		venda.setDataHora(LocalDateTime.now());
		venda.setFormaPagamento(PagamentoEnum.DINHEIRO);
		venda.setValorTotal(25.00);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}

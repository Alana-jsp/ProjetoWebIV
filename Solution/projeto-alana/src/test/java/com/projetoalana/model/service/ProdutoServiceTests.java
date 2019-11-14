package com.projetoalana.model.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;

import com.projetoalana.model.entity.Produto;
import com.projetoalana.model.repository.ProdutoRepository;


public class ProdutoServiceTests extends AbstractIntegrationTests{
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	/*
	 * ------------------ Testes para Cadastro de Produtos-------------------------
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		   "/dataset/produto.sql"})
	public void cadastrarProdutoMustPass() {
		
		Produto produto = new Produto();
		produto.setNome("maltine");
		produto.setValor(6.50);
		produto.setDescricao("maltine é uma bebida, originalmente suíça, composta de malte, cacau, leite");
		this.produtoService.cadastrarProduto(produto);
		Assert.assertNotNull(produto.getCodigo());
	
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Sql({"/dataset/truncate.sql",
		   "/dataset/produto.sql"})
	public void cadastrarProdutoMustFailNomeEmBranco() {
		
		Produto produto = new Produto();
		produto.setNome("");
		produto.setValor(6.50);
		produto.setDescricao("maltine é uma bebida, originalmente suíça, composta de malte, cacau, leite");
		this.produtoService.cadastrarProduto(produto);
	//	Assert.assertNotNull(produto.getCodigo());
	
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Sql({"/dataset/truncate.sql",
		   "/dataset/produto.sql"})
	public void cadastrarProdutoMustFailNomeDuplicado() {
		
		Produto produto = new Produto();
		produto.setNome("café");
		produto.setValor(6.50);
		produto.setDescricao("maltine é uma bebida, originalmente suíça, composta de malte, cacau, leite");
		this.produtoService.cadastrarProduto(produto);
		Assert.assertNotNull(produto.getCodigo());
	
	}
	
	/*
	 * --------------------Testes Alterar um Produto--------------------------------
	 */
	@Test()
	@Sql({"/dataset/truncate.sql",
		   "/dataset/produto.sql"})
	public void alterarProdutoMustPass() {
		
		Produto produto = this.produtoRepository.findById(1000L).orElse(null);
		produto.setNome("café com leite");
		this.produtoService.atualizaProduto(produto);
		//Assert.assertNotNull(produto.getCodigo());
		
	}
	
	@Test()
	@Sql({"/dataset/truncate.sql",
		   "/dataset/produto.sql"})
	public void alterarProdutoMustFailNomeEmBranco() {
		
		Produto produto = new Produto();
		produto.setNome("");
		produto.setValor(6.50);
		produto.setDescricao("maltine é uma bebida, originalmente suíça, composta de malte, cacau, leite");
		this.produtoService.atualizaProduto(produto);
		Assert.assertNotNull(produto.getCodigo());
		
	}
	
	
}

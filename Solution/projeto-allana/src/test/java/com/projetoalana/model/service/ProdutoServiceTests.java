package com.projetoalana.model.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

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
	
	@Test
	@Sql({"/dataset/truncate.sql",
		   "/dataset/produto.sql"})
	public void cadastrarProdutoMustFailNomeDuplicado() {
		
		Produto produto = this.produtoRepository.findById(1001L).orElse(null);
		produto.setNome("capucchino");
		this.produtoService.cadastrarProduto(produto);
		//Assert.assertNotNull(produto.getCodigo());
	
	}
	
	/*
	 * --------------------Testes Alterar um Produto--------------------------------
	 */
	@Test()
	@Sql({"/dataset/truncate.sql",
		   "/dataset/produto.sql"})
	public void atualizarProdutoMustPass() {
		
		Produto produto = this.produtoRepository.findById(1000L).orElse(null);
		produto.setNome("café com leite");
		this.produtoService.atualizaProduto(produto);
		//Assert.assertNotNull(produto.getCodigo());
		
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Sql({"/dataset/truncate.sql",
		   "/dataset/produto.sql"})
	public void atualizarProdutoMustFailNomeEmBranco() {
		
		Produto produto = new Produto();
		produto.setNome("");
		produto.setValor(6.50);
		produto.setDescricao("maltine é uma bebida, originalmente suíça, composta de malte, cacau, leite");
		this.produtoService.atualizaProduto(produto);
		Assert.assertNotNull(produto.getCodigo());
		
	}
	
	/*
	 * ----------------Listar Produtos------------------------
	 */
	@Test 
	@Sql({"/dataset/truncate.sql",
		  "/dataset/produto.sql"})
	public void listarProdutosMustPass() {
		List<Produto> produtos = this.produtoService.listaProduto();
		
	}
	
	/*
	 * --------------------------Detalhar----------------------------------
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		  "/dataset/produto.sql"})
	public void detalharProdutoMustPass() {
		Produto produto = this.produtoService.detalharProduto(1001L);
		
		Assert.assertNotNull(produto);
		Assert.assertNotNull(produto.getCodigo());
	}
	@Test(expected = IllegalArgumentException.class)
	@Sql({"/dataset/truncate.sql",
		  "/dataset/produto.sql"})
	public void detalharProdutoMustFaiCodigolNaoExiste() {
		
		Produto produto = this.produtoService.detalharProduto(2L);
	}
	
	/*
	 * -----------------------------Excluir Produto---------------------------
	 */
	@Test
	@Sql({"/dataset/truncate.sql",
		  "/dataset/produto.sql"})
	public void removerProdutoMustPass() {
		this.produtoService.removeProduto(1001);
		Produto produto = this.produtoRepository.findById(1001L).orElse(null);
		
		Assert.assertNotNull(produto);
	}
}

package com.projetoalana.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.projetoalana.model.entity.Produto;
import com.projetoalana.model.repository.ProdutoRepository;



@Service
@Transactional
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	/*
	 * Serviço para Cadastrar um novo Produto
	 * 
	 */
	public Produto cadastrarProduto(Produto produto) {
		return this.produtoRepository.save(produto);
	}
	
	/*
	 * Servico para atualizar um Cadastro de Produto
	 *  
	 */
	public Produto atualizaProduto(Produto produto) {
		return this.produtoRepository.save(produto);
	}
	
	/*
	 * Serviço de listagem de Produtos
	 * 
	 */
	public List<Produto> listaProduto(){
		return this.produtoRepository.findAll();
	}
	
	/*
	 * Serviço para detalhar um Produto
	 * apartir do seu codigo
	 * caso não seja encontre ele retorna uma mensagem de erro
	 */
	public Produto detalharProduto(long codigo) {
		Produto produto = this.produtoRepository.findById(codigo).orElse(null);
		Assert.notNull(produto,"Codigo"+codigo+"não encontrado");
		
		return produto;
	}
	
	/*
	 * Servico para remover um Produto
	 * apartir do seu codigo
	 * 
	 */
	public void removeProduto(long codigo) {
		this.produtoRepository.deleteById(codigo);
	}
	
	/*
	 * Serviço que lista os Produtos por filtro
	 */
	
	public Page<Produto> listarProdutoPorFiltros(String nome,Double valor, String descricao, PageRequest pageable){
		return this.produtoRepository.findByFilters(nome,valor,descricao,pageable);
	}
}

package com.projetoalana.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.projetoalana.model.entity.VendaProduto;
import com.projetoalana.model.repository.VendaProdutoRepository;

public class VendaProdutoService {
	
	@Autowired
	private VendaProdutoRepository vendaProdutoRepository;
	
	/*
	 * Serviço para cadastrar uma vendaProduto
	 */
	public VendaProduto cadastrarVendaProduto(VendaProduto vendaProduto) {
		return this.vendaProdutoRepository.save(vendaProduto);
	}
	
	/*
	 * Serviço para atualizar uma vendaProduto
	 */
	public VendaProduto atualizaVendaProduto(VendaProduto vendaProduto) {
		return this.vendaProdutoRepository.save(vendaProduto);
	}
	
	/*
	 * Serviço para listar uma vendaProduto
	 */
	public List<VendaProduto> listaVendaProduto(){
		return this.vendaProdutoRepository.findAll();
	}
	
	/*
	 * Serviço de detalhar uma vendaProduto
	 * A partir do seu codigo
	 * caso não seja encontrado ele retorna uma mensagem de erro
	 */
	public VendaProduto detalhaVendaProduto(long codigo) {
		VendaProduto vendaProduto = this.vendaProdutoRepository.findById(codigo).orElse(null);
		Assert.notNull(vendaProduto, "Codigo"+codigo+"não encontrado");
		
		return vendaProduto;
	}
	
	/*
	 * Serviço para excluir uma vendaProduto
	 * A partir do seu codigo
	 */
	public void removeVendaProduto(long codigo) {
		this.vendaProdutoRepository.deleteById(codigo);
	}
}


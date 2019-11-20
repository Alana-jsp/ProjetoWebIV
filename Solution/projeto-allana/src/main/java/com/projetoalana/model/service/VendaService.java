package com.projetoalana.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.projetoalana.model.entity.Venda;
import com.projetoalana.model.repository.VendaRepository;



@Service
@Transactional
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	/*
	 * Servico para realizar uma nova venda
	 * 
	 */
	public Venda cadastrarVenda(Venda venda) {
		return this.vendaRepository.save(venda);
	}
	
	/*
	 * Servico para atualizar uma venda
	 * 
	 */
	public Venda atualizaVenda(Venda venda) {
		return this.vendaRepository.save(venda);
	}
	
	/*
	 * Servico de listagem das vendas realizadas
	 * 
	 */
	public List<Venda> listaVenda(){
		return this.vendaRepository.findAll();
	}
	
	/*
	 * Servico para detalhar uma Venda
	 * Apartir do seu codigo
	 * caso não seja encontrado ele retorna uma mensagem de erro
	 */
	public Venda detalhaVenda(long codigo) {
		Venda venda = this.vendaRepository.findById(codigo).orElse(null);
		Assert.notNull(venda, "Codigo"+codigo+"não encontrado");
		
		return venda;
	}
	
	/*
	 * Serviço para remover uma venda 
	 * a partir do seu codigo
	 * 
	 */
	public void removeVenda(long codigo) {
		this.vendaRepository.deleteById(codigo);
	}
	
}

package com.projetoalana.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.projetoalana.model.entity.Funcionario;
import com.projetoalana.model.repository.FuncionarioRepository;



@Service
@Transactional 
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	/*
	 * Servico para realizar um novo cadastro de funcionario
	 * 
	 */
	public Funcionario cadastrarFuncionario(Funcionario funcionario) {
		return this.funcionarioRepository.save(funcionario);
	}
	
	/*
	 * Servico para atualizar um funcionario
	 * 
	 */
	public Funcionario atualizarFuncionario(Funcionario funcionario) {
		return this.funcionarioRepository.save(funcionario);
	}
	
	/*
	 * Serviço de listagem de Funcionarios
	 * 
	 */
	public List<Funcionario> listaFuncionario(){
		return this.funcionarioRepository.findAll();
	}
	
	/*
	 * Serviço de detalhar um funcionario
	 * Apartir do seu codigo
	 * caso não seja encontrado ele retorna uma mensagem de erro
	 */
	public Funcionario detalhaFuncioanrio(long codigo) {
		Funcionario funcionario = this.funcionarioRepository.findById(codigo).orElse(null);
		Assert.notNull(funcionario, "Codigo"+codigo+"não encontrado");
	
		return funcionario;
	}
	
	/*
	 * Serviço para remover um funcionario
	 * a partir do seu codigo
	 * 
	 */
	public void removeFuncionario(long codigo) {
		this.funcionarioRepository.deleteById(codigo);
	}
}

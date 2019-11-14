package com.projetoalana.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.projetoalana.model.entity.Funcionario;
import com.projetoalana.model.repository.FuncionarioRepository;

@Service
@Transactional
public class ImplUserDetailService implements UserDetailsService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email)  {
		Funcionario funcionario = this.funcionarioRepository.findByEmailIgnoreCase(email);
		Assert.notNull(funcionario, "Nenhum usuário encontrado com o e-mail especificado.");
		return funcionario;
	}	

}

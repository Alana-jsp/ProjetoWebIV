package com.projetoalana.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoalana.model.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long>{

	Funcionario findByEmailIgnoreCase(String email);
}

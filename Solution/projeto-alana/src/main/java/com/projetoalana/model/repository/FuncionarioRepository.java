package com.projetoalana.model.repository;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetoalana.model.entity.Funcionario;


public interface FuncionarioRepository extends JpaRepository<Funcionario,Long>{

	Funcionario findByEmailIgnoreCase(String email);
	
	
	Optional<Funcionario> findByPasswordResetToken( String token );
	
	
	Optional<Funcionario> findByAccountActivateToken( String token );
	
}

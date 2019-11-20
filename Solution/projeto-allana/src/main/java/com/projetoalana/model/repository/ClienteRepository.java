package com.projetoalana.model.repository;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetoalana.model.entity.Cliente;




public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	
	@Query("FROM Cliente cliente "
			+ "WHERE "
			+ "(cliente.nome LIKE '%' || :nome || '%' OR :nome IS NULL) ")
	public Page<Cliente> findByFilters(@Param("nome") String nome, Pageable pageable);
	
	

}

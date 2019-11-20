package com.projetoalana.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetoalana.model.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{
		
	@Query("FROM Produto produto "
			+ "WHERE "
			+ "(produto.nome LIKE '%' || :nome || '%' OR :nome IS NULL) "
			+ " AND (produto.valor LIKE '%' || :valor || '%' OR :valor IS NULL) "
			+ " AND (produto.descricao LIKE '%' || :valor || '%' OR :descricao IS NULL) ")
	
		public Page<Produto> findByFilters(@Param("nome") String nome, @Param("valor") Double valor, 
			@Param("descricao") String descricao, Pageable pageable);
	
}

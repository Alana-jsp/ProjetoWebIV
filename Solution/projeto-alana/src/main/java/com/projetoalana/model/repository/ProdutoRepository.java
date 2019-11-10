package com.projetoalana.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoalana.model.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{

}

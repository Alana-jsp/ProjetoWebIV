package com.projetoalana.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoalana.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}

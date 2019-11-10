package com.projetoalana.model.entity;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode( callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class VendaProduto extends AbstractEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2666241962296837405L;

	/*
	 * Atributos
	 */
	@NotNull
	private Double desconto;
	
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private Double valor;
	
	@ManyToOne(targetEntity = Produto.class, fetch = FetchType.LAZY, optional = false)
	private Produto produto;
	
	@ManyToOne(targetEntity = Venda.class, fetch = FetchType.EAGER, optional = false)
	private Venda venda;
	/*
	 * Metodos
	 */
}

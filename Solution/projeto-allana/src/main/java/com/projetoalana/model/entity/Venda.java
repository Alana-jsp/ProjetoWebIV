package com.projetoalana.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Venda extends AbstractEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/*
	 * Atributos
	 */
	
	
	
	@NotNull
	private LocalDateTime dataHora;
	
	@NotNull
	private Double valorTotal;
	
	@NotNull
	private Integer quantidade;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private PagamentoEnum formaPagamento;
	
	@ManyToOne(targetEntity = Funcionario.class, fetch = FetchType.LAZY, optional = false)
    private  Funcionario funcionario;
	
	@ManyToOne(targetEntity = Cliente.class, fetch = FetchType.LAZY, optional = false)
    private Cliente cliente;
	
	@OneToMany(targetEntity = VendaProduto.class, mappedBy = "venda", fetch = FetchType.LAZY, 
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
	private List<VendaProduto> itens = new ArrayList<VendaProduto>();
	/*
	 *Metodos 
	 */
}

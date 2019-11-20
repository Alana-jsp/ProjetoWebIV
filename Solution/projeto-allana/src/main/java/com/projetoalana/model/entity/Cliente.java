package com.projetoalana.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * Atributos
	 */
	@Column(nullable = false, length = 100)
	@NotBlank
	private String nome;
	
	/*
	 * Metodos
	 */
	
}

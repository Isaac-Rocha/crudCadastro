package com.crud.cadastro.dto;

import java.io.Serializable;

import org.modelmapper.ModelMapper;

import com.crud.cadastro.entities.Vendedor;
import com.fasterxml.jackson.annotation.JsonProperty;


public class VendedorDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id; 
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("cpf")
	private String cpf;
	
	@JsonProperty("cnpj")
	private String cnpj;

	public static VendedorDto create(Vendedor vendedor) {

	return new ModelMapper().map(vendedor, VendedorDto.class);
	}
	
	public Vendedor createVendedor() {
	
		return new Vendedor(id, nome, cpf, cnpj);
	}
	
	public Vendedor updadeVendedor(Vendedor vendedor) {
		vendedor.setNome(nome);
		vendedor.setCpf(cpf);
		vendedor.setCnpj(cnpj);
		return vendedor;
	}
	
	public VendedorDto() {
		
	}

	public VendedorDto(Long id, String nome, String cpf, String cnpj) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cnpj = cnpj;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	
}

package com.crud.cadastro.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.modelmapper.ModelMapper;

import com.crud.cadastro.entities.Cliente;
import com.crud.cadastro.entities.Vendedor;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ClienteDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id; 
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("cpf")
	private String cpf;
	
	@JsonProperty("dataNascimento")
	private String dataNascimento;
	
	@JsonProperty("vendedor")
	private Long vendedorId;

	public static ClienteDto create(Cliente cliente) {		
		
	return new ModelMapper().map(cliente, ClienteDto.class);	
	}
	
	public Cliente createCliente(Vendedor vendedor) throws ParseException {	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		return new Cliente(id, nome, cpf, sdf.parse(dataNascimento), vendedor);
	}
	
	public Cliente updateCliente(Cliente cliente, Vendedor vendedor) throws ParseException {	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setDataNascimento(sdf.parse(dataNascimento));
		cliente.setVendedor(vendedor);
		return cliente;
	}
	
	public ClienteDto() {
		
	}
	
	public ClienteDto(Long id, String nome, String cpf, String dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public Long getVendedorId() {
		return vendedorId;
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

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	
}

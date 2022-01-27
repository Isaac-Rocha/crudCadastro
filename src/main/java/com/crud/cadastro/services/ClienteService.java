package com.crud.cadastro.services;

import java.text.ParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crud.cadastro.dto.ClienteDto;
import com.crud.cadastro.entities.Cliente;
import com.crud.cadastro.entities.Vendedor;
import com.crud.cadastro.exceptions.ResourceNotFoundException;
import com.crud.cadastro.repositories.ClienteRepository;
import com.crud.cadastro.repositories.VendedorRepository;

@Service 
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	public ClienteDto create(ClienteDto clienteDto) throws ParseException {
		Optional<Vendedor> vendedor = vendedorRepository.findById(clienteDto.getVendedorId());
		if(!vendedor.isPresent()) {
			throw new ResourceNotFoundException("Vendedor não encontrado!");
		}
		Cliente cliente = clienteDto.createCliente(vendedor.get());

		return ClienteDto.create(clienteRepository.save(cliente));
	}
	
	public ClienteDto update(ClienteDto clienteDto) throws ParseException {
		Optional<Cliente> clienteOptional = clienteRepository.findById(clienteDto.getId());
		if(!clienteOptional.isPresent()) {
			throw new ResourceNotFoundException("Cliente não encontrado!");
		}
		Optional<Vendedor> vendedor = vendedorRepository.findById(clienteDto.getVendedorId());
		if(!vendedor.isPresent()) {
			throw new ResourceNotFoundException("Vendedor não encontrado!");
		}
		Cliente cliente = clienteDto.updateCliente(clienteOptional.get(), vendedor.get());
		clienteRepository.save(cliente);
		return ClienteDto.create(cliente);
	}
	
	public void delete(Long id) {
		var entity = clienteRepository.findById(id);
		clienteRepository.delete(entity.get());
	}
	
	public Page<ClienteDto> findAll(Pageable pageable){
		var page = clienteRepository.findAll(pageable);
		return page.map(this::convertToClienteDto);
	}
	
	public Page<ClienteDto> findByCpfOrCnpj(String cpfOrNome, Pageable pageable, Long vendedorId){
		Optional<Vendedor> vendedor = vendedorRepository.findById(vendedorId);
		Page<Cliente> cliente = clienteRepository.findByCpfOrNome(cpfOrNome, pageable, vendedor.get());
		if(cliente.isEmpty())
			throw new ResourceNotFoundException("Cliente não encontrado na base de dados!");
		return cliente.map(this::convertToClienteDto);
	}		
	
	public ClienteDto convertToClienteDto(Cliente cliente) {
		
		return ClienteDto.create(cliente);
	}
}

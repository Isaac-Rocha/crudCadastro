package com.crud.cadastro.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crud.cadastro.dto.VendedorDto;
import com.crud.cadastro.entities.Vendedor;
import com.crud.cadastro.exceptions.ResourceNotFoundException;
import com.crud.cadastro.repositories.VendedorRepository;

@Service
public class VendedorService {
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	public VendedorDto create(VendedorDto vendedorDto) {
		if (!(vendedorDto.getCpf()!= null) && !(vendedorDto.getCnpj()!= null)) {
			throw new ResourceNotFoundException("É necessario informar pelo menos um CPF ou CNPJ!");
		}
		Vendedor vendedor = vendedorDto.createVendedor();

		return VendedorDto.create(vendedorRepository.save(vendedor));
	}
	
	public VendedorDto update(VendedorDto vendedorDto) {
		Optional<Vendedor> vendedorOptional = vendedorRepository.findById(vendedorDto.getId());
		if (!vendedorOptional.isPresent()) {
			throw new ResourceNotFoundException("Vendedor não encontrado na base de dados!");
		}
		Vendedor vendedor = vendedorDto.updadeVendedor(vendedorOptional.get());
		vendedorRepository.save(vendedor);
		return VendedorDto.create(vendedor);
	}
	
	public void delete(Long id) {
		var entity = vendedorRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Vendedor não encontrado na base de dados!"));
		vendedorRepository.delete(entity);
	}
	
	public Page<VendedorDto> findAll(Pageable pageable){
		var page = vendedorRepository.findAll(pageable);
		return page.map(this::convertToVendedorDto);
	}

	public List<VendedorDto> findByCpfOrCnpj(String cpfOrCnpj){
		List<Vendedor> vendedor = vendedorRepository.findByCpfOrCnpj(cpfOrCnpj);
		if(vendedor.isEmpty())
			throw new ResourceNotFoundException("Vendedor não encontrado na base de dados!");
		return vendedor.stream().map(this::convertToVendedorDto).collect(Collectors.toList());
	}		

	public VendedorDto convertToVendedorDto(Vendedor vendedor) {
		
		return VendedorDto.create(vendedor);
	}
	
}

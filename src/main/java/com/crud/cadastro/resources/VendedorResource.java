package com.crud.cadastro.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.cadastro.dto.VendedorDto;
import com.crud.cadastro.services.VendedorService;

@RestController
@RequestMapping("/vendedor")
public class VendedorResource {

	@Autowired
	private VendedorService vendedorService;
	
	@PostMapping
	public VendedorDto create(@RequestBody VendedorDto vendedorDto) {
		VendedorDto venDto = vendedorService.create(vendedorDto);
		
		return venDto;
	}

	@PutMapping
	public VendedorDto update(@RequestBody VendedorDto vendedorDto) {
		VendedorDto venDto = vendedorService.update(vendedorDto);
		
		return venDto;
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		Pageable pageable = PageRequest.of(0, 5, Direction.ASC, "nome");
		Page<VendedorDto> vendedores= vendedorService.findAll(pageable);
		
		return new ResponseEntity<>(vendedores, HttpStatus.OK);
	}
	
	@GetMapping("/findCpfCnpj/{cpfCnpj}")
	public ResponseEntity<?> findByCpfOrCnpj(@PathVariable("cpfCnpj") String cpfOrCnpj){
		List<VendedorDto> vendedores = vendedorService.findByCpfOrCnpj(cpfOrCnpj);
		
		return new ResponseEntity<>(vendedores, HttpStatus.OK);
	}
	
}

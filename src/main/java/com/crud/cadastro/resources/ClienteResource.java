package com.crud.cadastro.resources;

import java.text.ParseException;

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

import com.crud.cadastro.dto.ClienteDto;
import com.crud.cadastro.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ClienteDto create(@RequestBody ClienteDto clienteDto) throws ParseException {
		ClienteDto cliDto = clienteService.create(clienteDto);
		
		return cliDto;
	}

	@PutMapping
	public ClienteDto update(@RequestBody ClienteDto clienteDto) throws ParseException {
		ClienteDto cliDto = clienteService.update(clienteDto);
		
		return cliDto;
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		
		Pageable pageable = PageRequest.of(0, 5, Direction.ASC, "nome");
		Page<ClienteDto> clientes= clienteService.findAll(pageable);
		
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@GetMapping("/findCpfNome/{id}/vendedor/{vendedorId}")
	public ResponseEntity<?> findByCpfOrNome(@PathVariable("id") String cpfOrNome, @PathVariable("vendedorId") Long vendedorId){
		
		Pageable pageable = PageRequest.of(0, 5, Direction.ASC, "nome");
		Page<ClienteDto> clientes= clienteService.findByCpfOrCnpj(cpfOrNome, pageable, vendedorId);
		
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	} 
}
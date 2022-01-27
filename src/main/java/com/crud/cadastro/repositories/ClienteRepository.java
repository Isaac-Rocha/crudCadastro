package com.crud.cadastro.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.cadastro.entities.Cliente;
import com.crud.cadastro.entities.Vendedor;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query("select c from Cliente c where (c.nome = ?1 or c.cpf = ?1)")
	Page<Cliente> findByCpfOrNome(String cpfOrNome, Pageable pageable, Vendedor vendedor);

}

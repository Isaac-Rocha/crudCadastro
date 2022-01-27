package com.crud.cadastro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.cadastro.entities.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{
	@Query("select v from Vendedor v where v.cpf = ?1 or v.cnpj = ?1")
	List<Vendedor> findByCpfOrCnpj(String cpfOrCnpj);

}

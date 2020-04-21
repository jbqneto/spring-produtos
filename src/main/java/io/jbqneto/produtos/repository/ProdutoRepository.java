package io.jbqneto.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.jbqneto.produtos.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	Produto findById(long id);
	Produto findByNome(String nome);
}

package br.com.glandata.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.glandata.web.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query("SELECT p, c FROM Produto p LEFT JOIN p.categoria c WHERE c.nome = :nome")
	List<Produto> findByCategoriaNome(String nome);
}

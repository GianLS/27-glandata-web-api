package br.com.glandata.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.glandata.web.model.Categoria;
import br.com.glandata.web.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	List<Produto> findByCategoria(Categoria categoria);
}

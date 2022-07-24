package br.com.glandata.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.glandata.web.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

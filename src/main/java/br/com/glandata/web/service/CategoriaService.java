package br.com.glandata.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.glandata.web.model.Categoria;
import br.com.glandata.web.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Optional<Categoria> buscarPorId(Long id) {
		return categoriaRepository.findById(id);
	}

	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria delete(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);

		categoriaRepository.delete(categoria.get());

		return categoria.get();
	}

}

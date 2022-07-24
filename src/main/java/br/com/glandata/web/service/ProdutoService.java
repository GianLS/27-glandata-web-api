package br.com.glandata.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.glandata.web.model.Categoria;
import br.com.glandata.web.model.Produto;
import br.com.glandata.web.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Optional<Produto> findById(Long id) {
		return produtoRepository.findById(id);
	}

	public void delete(Produto produto) {
		produtoRepository.delete(produto);
	}

	public List<Produto> produtosPorCategoria(Long id) {
		Categoria categoria = new Categoria(id);
		
		return produtoRepository.findByCategoria(categoria);
	}

	public Produto delete(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		produtoRepository.delete(produto.get());

		return produto.get();
	}
}

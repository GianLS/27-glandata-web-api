package br.com.glandata.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.glandata.web.dto.ProdutoBasicoDto;
import br.com.glandata.web.dto.ProdutoDto;
import br.com.glandata.web.model.Produto;
import br.com.glandata.web.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	public Page<Produto> findAllPageable(Pageable pageable) {
		return produtoRepository.findAll(pageable);
	}


	public List<ProdutoDto> findAllDto() {
		List<ProdutoDto> produtosDto = new ArrayList<>();

		List<Produto> produtos = produtoRepository.findAll();

		produtos.forEach(p -> produtosDto.add(modelMapper.map(p, ProdutoDto.class)));

		return produtosDto;
	}

	public List<ProdutoBasicoDto> findAllBasicoDto() {
		
		List<ProdutoBasicoDto> produtosDto = new ArrayList<>();

		List<Produto> produtos = produtoRepository.findAll();

		produtos.forEach(p -> produtosDto.add(modelMapper.map(p, ProdutoBasicoDto.class)));

		return produtosDto;
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

	public List<Produto> produtosPorCategoria(String nome) {

		return produtoRepository.findByCategoriaNome(nome);
	}

	public Produto delete(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		produtoRepository.delete(produto.get());

		return produto.get();
	}

}

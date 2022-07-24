package br.com.glandata.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.glandata.web.dto.ProdutoDto;
import br.com.glandata.web.model.Produto;
import br.com.glandata.web.service.ProdutoService;

@RestController
@RequestMapping("api/produtos")
public class ProdutoRestController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("")
	@Cacheable(value = "listaDeProdutos")
	public List<ProdutoDto> listarProdutos() {
		List<ProdutoDto> produtos = new ArrayList<ProdutoDto>();

		produtoService.findAll().forEach(p -> produtos.add(new ProdutoDto(p)));

		return produtos;
	}

	@GetMapping("{id}")
	public ResponseEntity<ProdutoDto> getProduto(@PathVariable Long id) {
		Optional<Produto> produto = produtoService.findById(id);

		if (produto.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ProdutoDto>(new ProdutoDto(produto.get()), HttpStatus.OK);
	}

	@GetMapping("categoria/{id}")
	public List<ProdutoDto> listarProdutosPorCategoria(@PathVariable Long id) {

		List<ProdutoDto> produtos = new ArrayList<ProdutoDto>();
		produtoService.produtosPorCategoria(id).forEach(p -> produtos.add(new ProdutoDto(p)));

		return produtos;
	}

	@CacheEvict(value = "listaDeProdutos", allEntries = true)
	@PostMapping("")
	public ResponseEntity<ProdutoDto> postProduto(@RequestBody Produto produto) {
		return ResponseEntity.ok(new ProdutoDto(produtoService.save(produto)));
	}
	
	@CacheEvict(value = "listaDeProdutos", allEntries = true)
	@PutMapping("")
	public ResponseEntity<ProdutoDto> putProduto(@RequestBody Produto produto) {
		return ResponseEntity.ok(new ProdutoDto(produtoService.save(produto)));
	}

	@CacheEvict(value = "listaDeProdutos", allEntries = true)
	@DeleteMapping("{id}")
	public ResponseEntity<ProdutoDto> deleteProduto(@PathVariable Long id) {
		Optional<Produto> produto = produtoService.findById(id);

		if (produto.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(new ProdutoDto(produtoService.delete(id)));
	}
}

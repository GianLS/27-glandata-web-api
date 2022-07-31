package br.com.glandata.web.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import com.fasterxml.jackson.annotation.JsonView;

import br.com.glandata.web.dto.ProdutoBasicoDto;
import br.com.glandata.web.dto.ProdutoDto;
import br.com.glandata.web.dto.ProdutoDto.ProdutoView;
import br.com.glandata.web.model.Produto;
import br.com.glandata.web.service.ProdutoService;

@RestController
@RequestMapping("api/produtos")
public class ProdutoRestController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("")
	public List<ProdutoDto> listarProdutos() {
		return produtoService.findAllDto();
	}

	@GetMapping("listarBasicos")
	public List<ProdutoBasicoDto> listarProdutosBasico() {
		return produtoService.findAllBasicoDto();
	}

	@GetMapping("basicos")
	@JsonView(value = ProdutoView.Basico.class)
	public List<ProdutoDto> listarBasico() {
		return produtoService.findAllDto();
	}

	@GetMapping("{id}")
	public ResponseEntity<ProdutoDto> getProduto(@PathVariable Long id) {
		Optional<Produto> produto = produtoService.findById(id);

		if (produto.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ProdutoDto>(modelMapper.map(produto.get(), ProdutoDto.class), HttpStatus.OK);
	}

	@GetMapping("categoria/{nome}")
	public List<ProdutoDto> listarProdutosPorCategoria(@PathVariable String nome) {
		return produtoService.produtosPorCategoria(nome).stream().map(p -> modelMapper.map(p, ProdutoDto.class))
				.collect(Collectors.toList());
	}

	@PostMapping("")
	public ResponseEntity<ProdutoDto> postProduto(@RequestBody @Valid Produto produto) {
		return ResponseEntity.ok(modelMapper.map(produtoService.save(produto), ProdutoDto.class));
	}

	@PutMapping("")
	public ResponseEntity<ProdutoDto> putProduto(@RequestBody Produto produto) {
		return ResponseEntity.ok(modelMapper.map(produtoService.save(produto), ProdutoDto.class));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ProdutoDto> deleteProduto(@PathVariable Long id) {
		Optional<Produto> produto = produtoService.findById(id);

		if (produto.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(modelMapper.map(produtoService.delete(id), ProdutoDto.class));
	}

	@GetMapping("paginado")
	public ResponseEntity<Page<Produto>> listaPaginada(@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Produto> produtos = produtoService.findAllPageable(pageable);
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
}

package br.com.glandata.web.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import br.com.glandata.web.dto.CategoriaDto;
import br.com.glandata.web.model.Categoria;
import br.com.glandata.web.service.CategoriaService;

@RestController
@RequestMapping("api/categorias")
public class CategoriaRestController {

		@Autowired
		private CategoriaService categoriaService;
		
		@Autowired
		private ModelMapper modelMapper;
	
		@GetMapping("")
		@Cacheable(value = "listaDeCategorias")
		public List<CategoriaDto> listarCategorias() {
			return categoriaService.findAll().stream().map(c -> modelMapper.map(c, CategoriaDto.class)).collect(Collectors.toList());
		}
		
		@GetMapping("{id}")
		public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Long id){
			Optional<Categoria> categoria = categoriaService.buscarPorId(id);
			
			if (categoria.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<CategoriaDto>(modelMapper.map(categoria.get(), CategoriaDto.class), HttpStatus.OK);			
		}
		
		@CacheEvict(value = "listaDeCategorias", allEntries = true)
		@PostMapping("")
		public ResponseEntity<CategoriaDto> postCategoria(@RequestBody Categoria categoria){
			return ResponseEntity.ok(modelMapper.map(categoriaService.save(categoria), CategoriaDto.class));
		}
		
		@CacheEvict(value = "listaDeCategorias", allEntries = true)
		@PutMapping("")
		public ResponseEntity<CategoriaDto> putCategoria(@RequestBody Categoria categoria){
			return ResponseEntity.ok(modelMapper.map(categoriaService.save(categoria), CategoriaDto.class));
		}
		
		@CacheEvict(value = "listaDeCategorias", allEntries = true)
		@DeleteMapping("{id}")
		public ResponseEntity<CategoriaDto> deleteCategoria(@PathVariable Long id){
			Optional<Categoria> categoria = categoriaService.buscarPorId(id);

			if (categoria.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			if (!categoria.get().getProdutos().isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			return ResponseEntity.ok(modelMapper.map(categoriaService.delete(id), CategoriaDto.class));
		}
}

package br.com.glandata.web.dto;

import br.com.glandata.web.model.Categoria;
import lombok.Getter;

public class CategoriaDto {
	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
	}
	
	@Getter
	private Long id;
	
	@Getter
	private String nome;
	
	@Getter
	private String descricao;
}

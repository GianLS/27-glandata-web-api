package br.com.glandata.web.dto;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.glandata.web.dto.ProdutoDto.ProdutoView;
import lombok.Getter;
import lombok.Setter;

public class CategoriaDto {
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	@JsonView({ ProdutoView.Basico.class })
	private String nome;

	@Getter
	@Setter
	private String descricao;
}

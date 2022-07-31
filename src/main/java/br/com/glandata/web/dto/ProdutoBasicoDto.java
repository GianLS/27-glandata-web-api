package br.com.glandata.web.dto;

import lombok.Getter;
import lombok.Setter;

public class ProdutoBasicoDto {

	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	private String descricao;

	@Getter
	@Setter
	private String categoria;
}

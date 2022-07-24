package br.com.glandata.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.glandata.web.model.Produto;
import lombok.Getter;

public class ProdutoDto {
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.categoria = new CategoriaDto(produto.getCategoria()).getNome();
		this.preco = produto.getPreco();
	}

	@Getter
	private Long id;

	@Getter
	private String nome;

	@Getter
	private String descricao;

	@Getter
	private BigDecimal preco;

	@Getter
	private String categoria;

	@Getter
	private LocalDate dataCadastro = LocalDate.now();
}

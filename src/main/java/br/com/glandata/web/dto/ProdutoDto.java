package br.com.glandata.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoDto {
	public interface ProdutoView {
		public static interface Basico {
		}

		public static interface SemCategoria {
		}

		public static interface ApenasNome {
		}
	}

	@Getter
	@Setter
	@JsonView({ ProdutoView.SemCategoria.class })
	private Long id;

	@Getter
	@Setter
	@JsonView({ ProdutoView.Basico.class, ProdutoView.ApenasNome.class, ProdutoView.SemCategoria.class })
	private String nome;

	@Getter
	@Setter
	@JsonView({ ProdutoView.Basico.class, ProdutoView.SemCategoria.class })
	private String descricao;

	@Getter
	@Setter
	@JsonView({ ProdutoView.Basico.class, ProdutoView.SemCategoria.class })
	private BigDecimal preco;

	@Getter
	@Setter
	@JsonView({ ProdutoView.Basico.class })
	private CategoriaDto categoria;

	@Getter
	@Setter
	@JsonView({ ProdutoView.SemCategoria.class })
	private LocalDate dataCadastro;
}

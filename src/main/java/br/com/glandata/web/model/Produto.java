package br.com.glandata.web.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produtos", indexes = { @Index(name = "id_produto", columnList = "id") })
public class Produto {

	public Produto() {
	}

	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@NotBlank(message = "Informe um nome para o produto.")
	@Size(max = 30, message = "O nome do produto deve ter no máximo 30 caracteres.")
	private String nome;

	@Getter
	@Setter
	@Size(max = 75, message = "A descrição do produto deve ter no máximo 75 caracteres.")
	private String descricao;

	@Getter
	@Setter
	@NotNull(message = "Informe um preço para o produto.")
	@Column(name = "preco", precision = 19, scale = 4, columnDefinition = "Decimal(19,4)")
	@NumberFormat(pattern = "###.##")
	private BigDecimal preco;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "Informe uma categoria para o produto.")
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_categoria"))
	@JsonIgnore
	private Categoria categoria;

	@Getter
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro = LocalDate.now();
}

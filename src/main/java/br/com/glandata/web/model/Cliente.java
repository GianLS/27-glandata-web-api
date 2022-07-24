package br.com.glandata.web.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "clientes")
@ToString
public class Cliente {
	public Cliente() {
	}

	public Cliente(Long id) {
		this.id = id;
	}

	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@Column(length = 50)
	@Size(min = 3, max = 50, message = "O nome do cliente deve ter entre 3 e 50 caracteres")
	@NotBlank(message = "O nome do cliente não pode ser vazio")
	private String nome;

	@Getter
	@Setter
	@CPF(message = "Informe um CPF válido")
	@NotBlank(message = "O cpf do cliente não pode ser vazio")
	private String cpf;

	@Getter
	@Setter
	@Email(message = "Digite um e-mail válido")
	/* @Pattern(regexp = "^(.+)@(.+)$", message = "E-mail inválido") */
	private String email;

	@Getter
	@Setter
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
}

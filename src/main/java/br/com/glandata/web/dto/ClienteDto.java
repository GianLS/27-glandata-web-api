package br.com.glandata.web.dto;

import java.time.LocalDate;

import br.com.glandata.web.model.Cliente;
import lombok.Getter;
import lombok.Setter;

public class ClienteDto {
	public ClienteDto() {
	}

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.email = cliente.getEmail();
		this.dataNascimento = cliente.getDataNascimento();
	}

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	private String cpf;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private LocalDate dataNascimento;
}

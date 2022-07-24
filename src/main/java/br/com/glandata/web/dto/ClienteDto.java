package br.com.glandata.web.dto;

import java.time.LocalDate;

import br.com.glandata.web.model.Cliente;
import lombok.Getter;

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
	private Long id;

	@Getter
	private String nome;

	@Getter
	private String cpf;

	@Getter
	private String email;

	@Getter
	private LocalDate dataNascimento;
}

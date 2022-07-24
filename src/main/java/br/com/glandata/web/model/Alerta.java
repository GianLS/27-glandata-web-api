package br.com.glandata.web.model;

import lombok.Getter;
import lombok.Setter;

public class Alerta {
	public Alerta(String tipo, String mensagem, String icone) {
		this.tipo = tipo;
		this.mensagem = mensagem;
		this.icone = icone;
	}

	@Getter
	@Setter
	private String tipo;
	
	@Getter
	@Setter
	private String mensagem;
	
	@Getter
	@Setter
	private String icone;
}

package br.com.glandata.web.model;

import java.time.Duration;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Acesso {
	@Getter
	@Setter
	private String path;
	
	@Getter
	@Setter
	private LocalDateTime data;
	
	@Getter
	@Setter
	private Duration duracao;
}

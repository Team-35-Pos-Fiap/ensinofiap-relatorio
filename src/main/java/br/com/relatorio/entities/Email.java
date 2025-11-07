package br.com.relatorio.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Email {
	private String destinatario;
	private String remetente;
	private String texto;
	private String assunto;
}
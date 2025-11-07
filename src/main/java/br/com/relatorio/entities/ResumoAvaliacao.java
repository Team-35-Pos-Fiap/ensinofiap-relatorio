package br.com.relatorio.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResumoAvaliacao {
	private String nomeCurso;
	private Integer total;
	private Double media;
}

package br.com.relatorio.repositories.interfaces;

import java.time.LocalDate;
import java.util.List;

import br.com.relatorio.entities.ResumoAvaliacao;

public interface IResumoAvaliacaoRepository {
	List<ResumoAvaliacao> buscarResumoPorPeriodo(LocalDate dataInicial, LocalDate dataFinal);
}
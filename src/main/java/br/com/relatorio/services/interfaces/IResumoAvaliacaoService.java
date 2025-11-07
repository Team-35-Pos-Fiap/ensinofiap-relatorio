package br.com.relatorio.services.interfaces;

import java.time.LocalDate;
import java.util.List;

import br.com.relatorio.entities.ResumoAvaliacao;

public interface IResumoAvaliacaoService {
	List<ResumoAvaliacao> buscarAvaliacoesPorPeriodo(LocalDate dataInicial, LocalDate dataFinal);
}

package br.com.relatorio.services.interfaces;

import java.time.LocalDate;
import java.util.List;

import br.com.relatorio.entities.Resumo;

public interface IResumoAvaliacaoService {
	List<Resumo> buscarAvaliacoesPorPeriodo(LocalDate dataInicial, LocalDate dataFinal);
}

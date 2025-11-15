package br.com.relatorio.repositories.interfaces;

import java.time.LocalDate;
import java.util.List;

import br.com.relatorio.entities.Resumo;

public interface IResumoAvaliacaoRepository {
	List<Resumo> buscarResumoPorPeriodo(LocalDate dataInicial, LocalDate dataFinal);
}
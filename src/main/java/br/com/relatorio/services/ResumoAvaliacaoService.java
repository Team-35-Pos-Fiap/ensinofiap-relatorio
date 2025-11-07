package br.com.relatorio.services;

import java.time.LocalDate;
import java.util.List;

import br.com.relatorio.entities.ResumoAvaliacao;
import br.com.relatorio.repositories.interfaces.IResumoAvaliacaoRepository;
import br.com.relatorio.services.interfaces.IResumoAvaliacaoService;
import jakarta.inject.Inject;

public class ResumoAvaliacaoService implements IResumoAvaliacaoService {
	@Inject
	private IResumoAvaliacaoRepository resumoAvaliacaoRepository;
	
	@Override
	public List<ResumoAvaliacao> buscarAvaliacoesPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		return resumoAvaliacaoRepository.buscarResumoPorPeriodo(dataInicial, dataFinal);
	}
}
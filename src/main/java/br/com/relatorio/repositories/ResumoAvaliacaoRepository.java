package br.com.relatorio.repositories;

import java.time.LocalDate;
import java.util.List;

import br.com.relatorio.entities.Resumo;
import br.com.relatorio.entities.db.AvaliacaoDB;
import br.com.relatorio.repositories.interfaces.IResumoAvaliacaoDbRepository;
import br.com.relatorio.repositories.interfaces.IResumoAvaliacaoRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class ResumoAvaliacaoRepository implements IResumoAvaliacaoRepository{

	//@Inject
	//private IResumoAvaliacaoDbRepository avaliacaoDbRepository;
	
	@Override
	public List<Resumo> buscarResumoPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		return AvaliacaoDB.buscarResumoPorPeriodo(dataInicial, dataFinal);
	}
}
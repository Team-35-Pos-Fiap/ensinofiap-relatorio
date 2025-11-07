package br.com.relatorio.repositories;

import java.time.LocalDate;
import java.util.List;

import br.com.relatorio.entities.ResumoAvaliacao;
import br.com.relatorio.repositories.interfaces.IResumoAvaliacaoDbRepository;
import br.com.relatorio.repositories.interfaces.IResumoAvaliacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ResumoAvaliacaoRepository implements IResumoAvaliacaoRepository{

	@Inject
	private IResumoAvaliacaoDbRepository avaliacaoDbRepository;
	
	@Override
	public List<ResumoAvaliacao> buscarResumoPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		return avaliacaoDbRepository.find("select a.curso.nome, count(*) as total, avg(a.nota) as media "
										+ "  from Avaliacao a "
										+ " where a.dataCriacao between ?1 and ?2 "
										+ " group by a.curso.nome "
										+ " order by a.curso.nome", dataInicial, dataFinal)
									.project(ResumoAvaliacao.class)
									.list();
	}
}
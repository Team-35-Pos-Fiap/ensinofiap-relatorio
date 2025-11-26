package br.com.relatorio.services;

import java.util.List;

import br.com.relatorio.entities.db.CursoDB;
import br.com.relatorio.repositories.interfaces.ICursoRepository;
import br.com.relatorio.services.interfaces.ICursoService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CursoService implements ICursoService{

	@Inject
	private ICursoRepository cursoRepository;

	@Override
	public List<CursoDB> buscarCursos() {
		return cursoRepository.buscarTodos();
	}
}
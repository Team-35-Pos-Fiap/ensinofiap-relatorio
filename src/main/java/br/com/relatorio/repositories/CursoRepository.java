package br.com.relatorio.repositories;

import java.util.List;

import br.com.relatorio.entities.db.CursoDB;
import br.com.relatorio.repositories.interfaces.ICursoRepository;

public class CursoRepository implements ICursoRepository {

	@Override
	public List<CursoDB> buscarTodos() {
		return CursoDB.buscarTodos();
	}
}
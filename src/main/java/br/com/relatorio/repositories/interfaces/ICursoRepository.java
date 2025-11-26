package br.com.relatorio.repositories.interfaces;

import java.util.List;

import br.com.relatorio.entities.db.CursoDB;

public interface ICursoRepository {
	List<CursoDB> buscarTodos();
}
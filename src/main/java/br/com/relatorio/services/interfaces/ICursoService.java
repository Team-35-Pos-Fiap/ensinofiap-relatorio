package br.com.relatorio.services.interfaces;

import java.util.List;

import br.com.relatorio.entities.db.CursoDB;

public interface ICursoService {
	List<CursoDB> buscarCursos();
}
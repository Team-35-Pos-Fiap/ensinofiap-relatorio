package br.com.relatorio.services.interfaces;

import java.util.List;

import br.com.relatorio.entities.db.UsuarioDB;

public interface IUsuarioService {
	List<UsuarioDB> buscarAdministradores();
}
package br.com.relatorio.repositories.interfaces;

import java.util.List;

import br.com.relatorio.entities.db.UsuarioDB;

public interface IUsuarioRepository {
	List<UsuarioDB> buscarUsuarios();
}
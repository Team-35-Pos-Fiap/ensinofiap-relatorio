package br.com.relatorio.repositories;

import java.util.List;

import br.com.relatorio.entities.db.UsuarioDB;
import br.com.relatorio.repositories.interfaces.IUsuarioRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UsuarioRepository implements IUsuarioRepository{

	@Override
	public List<UsuarioDB> buscarUsuarios() {
		return UsuarioDB.buscarUsuariosAdministradores();
	}
}
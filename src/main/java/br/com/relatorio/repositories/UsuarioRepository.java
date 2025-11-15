package br.com.relatorio.repositories;

import java.util.List;

import br.com.relatorio.entities.db.UsuarioDB;
import br.com.relatorio.repositories.interfaces.IUsuarioDbRepository;
import br.com.relatorio.repositories.interfaces.IUsuarioRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UsuarioRepository implements IUsuarioRepository{

	//@Inject
	private IUsuarioDbRepository usuarioRepository;
	
	@Override
	public List<UsuarioDB> buscarUsuarios() {
		return UsuarioDB.buscarUsuariosAdministradores();
	}
}
package br.com.relatorio.repositories;

import java.util.List;

import br.com.relatorio.entities.db.UsuarioDB;
import br.com.relatorio.repositories.interfaces.IUsuarioDbRepository;
import br.com.relatorio.repositories.interfaces.IUsuarioRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioRepository implements IUsuarioRepository{

	@Inject
	private IUsuarioDbRepository usuarioRepository;
	
	@Override
	public List<UsuarioDB> buscarUsuarios() {
		PanacheQuery<UsuarioDB> usuarios = usuarioRepository.find("select u from UsuarioDB u where u.perfil.id = 2 and u.ativo = 1");
		
		return usuarios.list();
	}
}
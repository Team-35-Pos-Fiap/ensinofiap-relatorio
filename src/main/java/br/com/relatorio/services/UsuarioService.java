package br.com.relatorio.services;

import java.util.List;

import br.com.relatorio.entities.db.UsuarioDB;
import br.com.relatorio.repositories.interfaces.IUsuarioRepository;
import br.com.relatorio.services.interfaces.IUsuarioService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UsuarioService implements IUsuarioService {

    @Inject
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioDB> buscarAdministradores() {
        return usuarioRepository.buscarUsuarios();
    }
}
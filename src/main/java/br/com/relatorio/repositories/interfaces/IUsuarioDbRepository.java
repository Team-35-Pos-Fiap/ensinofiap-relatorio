package br.com.relatorio.repositories.interfaces;

import br.com.relatorio.entities.db.UsuarioDB;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface IUsuarioDbRepository extends PanacheRepository<UsuarioDB>{
}
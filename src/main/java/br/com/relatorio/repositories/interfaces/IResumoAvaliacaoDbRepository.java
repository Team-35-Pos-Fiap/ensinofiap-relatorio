package br.com.relatorio.repositories.interfaces;

import br.com.relatorio.entities.db.AvaliacaoDB;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface IResumoAvaliacaoDbRepository extends PanacheRepository<AvaliacaoDB>{
}
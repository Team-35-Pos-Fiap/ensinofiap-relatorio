package br.com.relatorio.entities.db;

import java.util.List;
import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "curso")
@Getter
@Setter
@ToString
public class CursoDB extends PanacheEntityBase {
    @Id
	private UUID id;
    
	private String nome;
	
	@OneToMany
	private List<AvaliacaoDB> avaliacoes;
	
	public static List<CursoDB> buscarTodos() {
		return CursoDB.listAll();
	}
}
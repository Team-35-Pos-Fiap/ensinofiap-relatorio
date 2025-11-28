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
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;

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
	
	@OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
	private List<AvaliacaoDB> avaliacoes;
	
	public static List<CursoDB> buscarTodos() {
		return CursoDB.listAll();
	}
}
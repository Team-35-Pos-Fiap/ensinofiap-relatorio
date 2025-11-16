package br.com.relatorio.entities.db;

import java.util.List;
import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
@Getter
@Setter
public class UsuarioDB extends PanacheEntityBase{
	@Id
	private UUID id;
	
	private String nome;
	
	private String email;
	
	private Boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "id_perfil", columnDefinition = "int")
	private PerfilDB perfil;

	public static List<UsuarioDB> buscarUsuariosAdministradores() {
		return list("select u from UsuarioDB u where u.perfil.id = 2 and u.ativo = true");
	}
}
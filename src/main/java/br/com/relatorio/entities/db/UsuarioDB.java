package br.com.relatorio.entities.db;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class UsuarioDB {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String nome;
	
	private String email;
	
	private Boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "id_perfil", columnDefinition = "int")
	private PerfilDB idPerfil;
}
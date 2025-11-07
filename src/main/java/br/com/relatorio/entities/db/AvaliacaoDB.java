package br.com.relatorio.entities.db;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "avaliacao")
@Getter
@Setter
public class AvaliacaoDB {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String descricao;
	
	@Column(name = "id_curso", nullable = false)
	private UUID idCurso;
	
	@Column(name = "id_aluno", nullable = false)
	private UUID idAluno;
	
	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao;
	
	@Column(name = "is_urgente", nullable = false)
	private Boolean isUrgente;
	
	private Integer nota;
}

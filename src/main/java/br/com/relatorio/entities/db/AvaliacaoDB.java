package br.com.relatorio.entities.db;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.relatorio.entities.Resumo;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "avaliacao")
@Getter
@Setter
@ToString()
public class AvaliacaoDB extends PanacheEntityBase{
	@Id
	private UUID id;
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_curso")
	@ToString.Exclude
	private CursoDB curso;
		
	@ManyToOne
	@JoinColumn(name = "id_aluno")
	private UsuarioDB aluno;
	
	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao;
	
	@Column(name = "is_urgente")
	private Boolean isUrgente;
	
	private Integer nota;

	public static List<Resumo> buscarResumoPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		return find("select a.curso.nome, count(a.id), avg(a.nota) "
				  + "  from AvaliacaoDB a "
				  + " where cast(a.dataCriacao as date) between ?1 and ?2 "
				  + " group by a.curso.nome "
				  + " order by a.curso.nome", dataInicial, dataFinal)
				.project(Resumo.class)
				.list();
	}

	public static List<String> buscarAvaliacoesFrequentesPorCurso(LocalDate dataInicial, LocalDate dataFinal, UUID idCurso) {
		return find("select a.descricao "
				  + "  from AvaliacaoDB a "
				  + " where cast(a.dataCriacao as date) between ?1 and ?2 "
				  + "   and a.curso.id = ?3 "
				  + " group by a.descricao ", dataInicial, dataFinal, idCurso)
			   .project(String.class)
			   .list();
	}
}
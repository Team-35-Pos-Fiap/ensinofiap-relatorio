package br.com.relatorio.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.relatorio.entities.Resumo;
import br.com.relatorio.entities.db.AvaliacaoDB;
import br.com.relatorio.entities.db.CursoDB;
import br.com.relatorio.entities.db.UsuarioDB;
import br.com.relatorio.services.interfaces.ICursoService;
import br.com.relatorio.services.interfaces.IEmailService;
import br.com.relatorio.services.interfaces.IRelatorioService;
import br.com.relatorio.services.interfaces.IResumoAvaliacaoService;
import br.com.relatorio.services.interfaces.IUsuarioService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class RelatorioService implements IRelatorioService {

	@Inject
	private IResumoAvaliacaoService resumoAvaliacaoService;
	
	@Inject
	private IEmailService emailService;
	
	@Inject
	private IUsuarioService usuarioService;
	
	@Inject
	private ICursoService cursoService;
	
	@Override
	public void gerarRelatorioSemanal() {
		enviarEmailResumoAvaliacoes();
		enviarEmailComentariosFrequentes();
	}
		
	private List<Resumo> buscarResumoAvaliacoes() {
		return resumoAvaliacaoService.buscarAvaliacoesPorPeriodo(getDataInicial(), getDataFinal());
	}
	
	private LocalDate getDataFinal() {
		return getDataAtual().minusDays(1);
	}

	private LocalDate getDataInicial() {
		return getDataAtual().minusDays(30);
	}
	
	private LocalDate getDataAtual() {
		return LocalDate.now();
	}

	private void enviarEmailResumoAvaliacoes() {
		emailService.enviarEmailResumoAvaliacoes(buscarResumoAvaliacoes(), buscarEmailsDestinatarios());
	}

	private void enviarEmailComentariosFrequentes() {
		List<CursoDB> cursos = buscarCursos();
		List<String> mensagens = recuperaComentarios(cursos);
		
		//filtra as avaliações frequentes dentro do período
		
		emailService.enviarEmailComentariosFrequentesAvaliacoes(mensagens, buscarEmailsDestinatarios());
	}
	
	private List<String> buscarEmailsDestinatarios() {
		List<UsuarioDB> usuarios = usuarioService.buscarAdministradores();
		
		return usuarios.stream().map(u -> u.getEmail()).collect(Collectors.toList());
	}
	
	private List<CursoDB> buscarCursos() {
		return cursoService.buscarCursos();
	}
	
	public String recuperarComentario(CursoDB curso) {
		List<AvaliacaoDB> avaliacoes = curso.getAvaliacoes();
		
		String descricaoCurso = "<b>Curso: " + curso.getNome() + "</b>" + System.lineSeparator();
		
		String comentarios = avaliacoes.stream()
									   .map(a -> "  <li>" + a.getDescricao() + "  </li>")
									   .collect(Collectors.joining("\n", "<ul>\n", "\n</ul>"));		
		
		return descricaoCurso + comentarios + System.lineSeparator();
	}
	
	public List<String> recuperaComentarios(List<CursoDB> cursos) {
		return cursos.stream().map(c -> recuperarComentario(c)).collect(Collectors.toList());
	}
}
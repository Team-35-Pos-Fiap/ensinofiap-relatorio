package br.com.relatorio.services;

import java.util.List;
import java.util.stream.Collectors;

import br.com.relatorio.entities.db.AvaliacaoDB;
import br.com.relatorio.entities.db.CursoDB;
import br.com.relatorio.services.interfaces.ICursoService;
import br.com.relatorio.services.interfaces.IEmailService;
import br.com.relatorio.services.interfaces.IRelatorioService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.Map;

@RequestScoped
public class AvaliacoesFrequentesService implements IRelatorioService {

	@Inject
	private IEmailService emailService;
	
	@Inject
	private ICursoService cursoService;
	
	private final String ASSUNTO = "Relatório semanal com os comentários frequentes das avaliações.";	
	
	private String mensagemAvaliacoesFrequentes = 
	""" 
		<html>
			<body>
				<p>Olá administradores,</p>
				<p>Segue relatório com as avaliações frequentes dos cursos.</p>
				<br/>
				%s	
			</body>
		</html>
	""";
	
	@Override
	public void gerarRelatorio() {
		enviarEmailAvaliacoesFrequentes(montarMensagem(recuperaAvaliacoesFrequentes(buscarCursos())));
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

	private void enviarEmailAvaliacoesFrequentes(String mensagem) {
		emailService.enviarEmail(mensagem, ASSUNTO);
	}
	
	private List<CursoDB> buscarCursos() {
		return cursoService.buscarCursos();
	}
	
	public List<String> recuperaAvaliacoesFrequentes(List<CursoDB> cursos) {
		return cursos.stream().map(c -> trataAvaliacoes(c)).collect(Collectors.toList());
	}
	
	public String trataAvaliacoes(CursoDB curso) {
		return montaMensagemDescricaoCurso(curso.getNome()) + montaMensagemDescricaoAvaliacoes(AvaliacaoDB.buscarAvaliacoesFrequentesPorCurso(getDataInicial(), getDataFinal(), curso.getId())) + System.lineSeparator();
	}
	
	private String montaMensagemAvaliacoesFrequentes(List<String> avaliacoes) {
		return avaliacoes.stream().collect(Collectors.joining("\n"));	
	}
	
	private String montarMensagem(List<String> avaliacoes) {
		return String.format(mensagemAvaliacoesFrequentes, montaMensagemAvaliacoesFrequentes(avaliacoes));
	}
	
	private String montaMensagemDescricaoCurso(String nome) {
		return "<b>Curso: " + nome + "</b> <br/>" ;
	}

	private String montaMensagemDescricaoAvaliacoes(List<String> avaliacoes) {
		return avaliacoes.stream().map(d -> "  <li>" + d + "  </li>").collect(Collectors.joining("\n", "<ul>\n", "</ul>"));
	}
}
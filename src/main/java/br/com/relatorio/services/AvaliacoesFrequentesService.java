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
				<p>Segue relatório com os avaliações frequentes dos cursos.</p>
				<br/>
				%s	
			</body>
		</html>
	""";
	
	@Override
	public void gerarRelatorio() {
		enviarEmailAvaliacoesFrequentes(montarMensagem(recuperaAvaliacoesFrequentes(buscarCursos())));
	}
	
	/*private LocalDate getDataFinal() {
		return getDataAtual().minusDays(1);
	}

	private LocalDate getDataInicial() {
		return getDataAtual().minusDays(30);
	}
	
	private LocalDate getDataAtual() {
		return LocalDate.now();
	}*/

	private void enviarEmailAvaliacoesFrequentes(String mensagem) {
		//filtra as avaliações frequentes dentro do período
		
		//emailService.enviarEmail(montaMensagemAvaliacoesFrequentes(recuperaAvaliacoesFrequentes(buscarCursos())), buscarEmailsDestinatarios());
		emailService.enviarEmail(mensagem, ASSUNTO);
	}
	
	private List<CursoDB> buscarCursos() {
		return cursoService.buscarCursos();
	}
	
	public List<String> recuperaAvaliacoesFrequentes(List<CursoDB> cursos) {
		return cursos.stream().map(c -> trataAvaliacoes(c)).collect(Collectors.toList());
	}
	
	public String trataAvaliacoes(CursoDB curso) {
		//List<AvaliacaoDB> avaliacoes = curso.getAvaliacoes();
		
		//String descricaoCurso = "<b>Curso: " + curso.getNome() + "</b>" + System.lineSeparator();
		
		return montaMensagemDescricaoCurso(curso.getNome()) + montaMensagemDescricaoAvaliacoes(curso.getAvaliacoes()) + System.lineSeparator();
	}
	
	private String montaMensagemAvaliacoesFrequentes(List<String> avaliacoes) {
		return avaliacoes.stream().collect(Collectors.joining("\n"));	
	}
	
	private String montarMensagem(List<String> avaliacoes) {
		return String.format(mensagemAvaliacoesFrequentes, montaMensagemAvaliacoesFrequentes(avaliacoes));
	}
	
	private String montaMensagemDescricaoCurso(String nome) {
		return "<b>Curso: " + nome + "</b>" + System.lineSeparator();
	}
	
	private String montaMensagemDescricaoAvaliacoes(List<AvaliacaoDB> avaliacoes) {
		return avaliacoes.stream()
				         .map(a -> "  <li>" + a.getDescricao() + "  </li>")
				         .collect(Collectors.joining("\n", "<ul>\n", "\n</ul>"));
	}
}
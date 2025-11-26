package br.com.relatorio.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.relatorio.entities.Resumo;
import br.com.relatorio.services.interfaces.IEmailService;
import br.com.relatorio.services.interfaces.IRelatorioService;
import br.com.relatorio.services.interfaces.IResumoAvaliacaoService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class ResumoAvaliacoesService implements IRelatorioService {

	@Inject
	private IResumoAvaliacaoService resumoAvaliacaoService;
	
	@Inject
	private IEmailService emailService;
	
	private final String ASSUNTO = "Relatório semanal com o resumo das avaliações.";

	private String resumo = "Curso: %s. Quantidade total de avaliações: %d. Média das avaliações: %.2f. \r\n";
	private String mensagemResumoAvaliacoes = 
	""" 
		<html>
			<body>
				<p>Olá administradores,</p>
				<p>Segue relatório semanal das avaliações.</p>
				<br/>
				%s	
			</body>
		</html>
	""";
	
	@Override
	public void gerarRelatorio() {
		enviarEmailResumoAvaliacoes(montarMensagemResumo(buscarResumoAvaliacoes()));
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

	private String montarMensagemResumo(List<Resumo> resumos) {
		return String.format(mensagemResumoAvaliacoes, montaListaResumos(resumos));
	}

	private String montaListaResumos(List<Resumo> resumos) {
		return resumos.stream()
					  .map(r -> "  <li>" + String.format(resumo, r.nomeCurso(), r.total(), r.media()) + "  </li>")
					  .collect(Collectors.joining("\n", "<ul>\n", "\n</ul>"));		
	}
	
	private void enviarEmailResumoAvaliacoes(String mensagem) {
		emailService.enviarEmail(mensagem, ASSUNTO);
	}
}
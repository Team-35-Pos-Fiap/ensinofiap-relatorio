package br.com.relatorio.services;

import java.util.List;
import java.util.stream.Collectors;

import br.com.relatorio.entities.Resumo;
import br.com.relatorio.services.interfaces.IEmailService;
import br.com.relatorio.services.interfaces.IEnvioEmailService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class EmailService implements IEmailService {

	@Inject
	private IEnvioEmailService envioEmailService;
	
	private final String REMETENTE = "DoNotReply@5acd0ae5-f401-4bb4-b1ad-b49425ca624f.azurecomm.net";
	private final String ASSUNTO = "Relatório semanal de avaliações";	
	private String resumo = "Curso: %s. Quantidade total de avaliações: %d. Média das avaliações: %.2f. \r\n";
	private String mensagem = """ 
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
	public void enviarEmail(List<Resumo> resumos, List<String> destinatarios) {
		enviarEmail(montarMensagemResumo(resumos), destinatarios);
	}

	private String montarMensagemResumo(List<Resumo> resumos) {
		return String.format(mensagem, montaLista(resumos));
	}
	
	private void enviarEmail(String mensagem, List<String> destinatarios) {
		envioEmailService.enviar(mensagem, destinatarios, REMETENTE, ASSUNTO);
	}

	private String montaLista(List<Resumo> resumos) {
		return resumos.stream()
					  .map(r -> "  <li>" + String.format(resumo, r.nomeCurso(), r.total(), r.media()) + "  </li>")
					  .collect(Collectors.joining("\n", "<ul>\n", "\n</ul>"));		
	}
}	
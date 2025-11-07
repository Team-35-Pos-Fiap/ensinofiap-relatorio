package br.com.relatorio.services;

import java.util.List;

import br.com.relatorio.entities.ResumoAvaliacao;
import br.com.relatorio.services.interfaces.IEmailService;
import br.com.relatorio.services.interfaces.IEnvioEmailService;
import jakarta.inject.Inject;

public class EmailService implements IEmailService {

	@Inject
	private IEnvioEmailService envioEmailService;
	
	private final String REMETENTE = "DoNotReply@5acd0ae5-f401-4bb4-b1ad-b49425ca624f.azurecomm.net";
	private final String MENSAGEM = "Olá administradores.\r\nSegue relatório semanal das avaliações.\r\n";
	private final String RESUMO = "Curso: %s - Média: %s - Total de avaliações: %s. \r\n";
	private final String ASSUNTO = "Relatório semanal de avaliações";	
		
	@Override
	public void enviarEmail(List<ResumoAvaliacao> resumos, List<String> destinatarios) {
		enviarEmail(montarMensagemResumo(resumos), destinatarios);
	}

	private String montarMensagemResumo(List<ResumoAvaliacao> resumos) {
		resumos.stream().forEach(r -> String.format(RESUMO, r.getNomeCurso(), r.getMedia(), r.getTotal()));
		
		return MENSAGEM + RESUMO;
	}
	
	private void enviarEmail(String mensagem, List<String> destinatarios) {
		envioEmailService.enviar(mensagem, destinatarios, REMETENTE, ASSUNTO);
	}
}	
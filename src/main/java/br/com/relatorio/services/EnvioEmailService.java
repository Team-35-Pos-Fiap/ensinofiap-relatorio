package br.com.relatorio.services;

import java.util.List;
import java.util.stream.Collectors;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailAddress;
import com.azure.communication.email.models.EmailMessage;

import br.com.relatorio.entities.db.UsuarioDB;
import br.com.relatorio.services.interfaces.IEnvioEmailService;
import br.com.relatorio.services.interfaces.IUsuarioService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class EnvioEmailService implements IEnvioEmailService {

	@Inject
	private IUsuarioService usuarioService;
	
	private String connectionString = "endpoint=https://cms-ensino.brazil.communication.azure.com/;accesskey=FeH4iXIV2wZEQbrGSoaKkpLenuQCkTtcpjWATrRcFLtzF7evUaKeJQQJ99BKACULyCp9qFJVAAAAAZCS2Jpc";

	private EmailClient emailClient = new EmailClientBuilder().connectionString(connectionString).buildClient();

	private final String REMETENTE = "DoNotReply@5acd0ae5-f401-4bb4-b1ad-b49425ca624f.azurecomm.net";
	
	@Override
	public void enviar(String mensagem, String assunto) {
		emailClient.beginSend(montarEmail(mensagem, assunto));
	}
	
	private EmailMessage montarEmail(String mensagem, String assunto) {
		return new EmailMessage().setSenderAddress(REMETENTE)
								 .setToRecipients(buscarEmailsDestinatarios())
								 .setSubject(assunto)
								 .setBodyHtml(mensagem);
	}
	
	/*private List<EmailAddress> recuperarDestinatarios(List<String> destinatarios) {
		return destinatarios.stream().map(d -> new EmailAddress(d)).collect(Collectors.toList());
	}*/
	
	private List<EmailAddress> buscarEmailsDestinatarios() {
		List<UsuarioDB> usuarios = usuarioService.buscarAdministradores();
		
		return usuarios.stream().map(u -> new EmailAddress(u.getEmail())).collect(Collectors.toList());
	} 
}
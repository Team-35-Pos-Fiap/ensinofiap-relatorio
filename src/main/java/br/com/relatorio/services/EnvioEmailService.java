package br.com.relatorio.services;

import java.util.List;
import java.util.stream.Collectors;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailAddress;
import com.azure.communication.email.models.EmailMessage;

import br.com.relatorio.services.interfaces.IEnvioEmailService;

public class EnvioEmailService implements IEnvioEmailService {

	private String connectionString = "endpoint=https://cms-ensino.brazil.communication.azure.com/;accesskey=FeH4iXIV2wZEQbrGSoaKkpLenuQCkTtcpjWATrRcFLtzF7evUaKeJQQJ99BKACULyCp9qFJVAAAAAZCS2Jpc";

	private EmailClient emailClient = new EmailClientBuilder().connectionString(connectionString).buildClient();
		
	@Override
	public void enviar(String mensagem, List<String> destinatarios, String remetente, String assunto) {
		emailClient.beginSend(montarEmail(mensagem, destinatarios, remetente, assunto));
	}
	
	private EmailMessage montarEmail(String mensagem, List<String> destinatarios, String remetente, String assunto) {
		EmailMessage email = new EmailMessage().setSenderAddress(remetente)
											   .setToRecipients(recuperarDestinatarios(destinatarios))
											   .setSubject(assunto)
											   .setBodyPlainText(mensagem);

		return email;
	}
	
	private List<EmailAddress> recuperarDestinatarios(List<String> destinatarios) {
		return destinatarios.stream().map(d -> new EmailAddress(d)).collect(Collectors.toList());
	}
}
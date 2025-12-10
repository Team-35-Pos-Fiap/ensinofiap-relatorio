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
	
	private String connectionString = "endpoint=https://mail-estudosthiago.brazil.communication.azure.com/;accesskey=1X2X92W9NAvFfloEfzo5QddIc87sckGO0xgcbqUgjd5fF13W9RCeJQQJ99BLACULyCpwSar0AAAAAZCS8g7e";

	private EmailClient emailClient = new EmailClientBuilder().connectionString(connectionString).buildClient();

	private final String REMETENTE = "DoNotReply@60d624b9-1dac-4fa4-b734-42fb93bffc91.azurecomm.net";
	
	@Override
	public void enviar(String mensagem, String assunto) {
		System.out.println("Enviando email... Assunto: " + assunto + " Mensagem: " + mensagem);

		emailClient.beginSend(montarEmail(mensagem, assunto));

		System.out.println("Email enviado com sucesso!");
	}
	
	private EmailMessage montarEmail(String mensagem, String assunto) {
		return new EmailMessage().setSenderAddress(REMETENTE)
								 .setToRecipients(buscarEmailsDestinatarios())
								 .setSubject(assunto)
								 .setBodyHtml(mensagem);
	}

	private List<EmailAddress> buscarEmailsDestinatarios() {
		List<UsuarioDB> usuarios = usuarioService.buscarAdministradores();
		
		usuarios.stream().forEach(u -> System.out.println("Email destinatario: " + u.getEmail()));
		return usuarios.stream().map(u -> new EmailAddress(u.getEmail())).collect(Collectors.toList());
	} 
}
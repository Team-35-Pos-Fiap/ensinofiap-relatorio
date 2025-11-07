package br.com.relatorio.services.interfaces;

import java.util.List;

public interface IEnvioEmailService {
	void enviar(String mensagem, List<String> destinatarios, String remetente, String assunto);
}
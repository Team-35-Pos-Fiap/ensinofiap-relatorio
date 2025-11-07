package br.com.relatorio.services.interfaces;

import java.util.List;

import br.com.relatorio.entities.ResumoAvaliacao;

public interface IEmailService {
	void enviarEmail(List<ResumoAvaliacao> resumos, List<String> destinatarios);
}
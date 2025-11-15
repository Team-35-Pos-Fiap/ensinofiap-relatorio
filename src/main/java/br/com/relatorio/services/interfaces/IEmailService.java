package br.com.relatorio.services.interfaces;

import java.util.List;

import br.com.relatorio.entities.Resumo;

public interface IEmailService {
	void enviarEmail(List<Resumo> resumos, List<String> destinatarios);
}
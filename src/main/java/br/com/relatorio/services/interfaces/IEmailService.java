package br.com.relatorio.services.interfaces;

import java.util.List;

import br.com.relatorio.entities.Resumo;

public interface IEmailService {
	void enviarEmailResumoAvaliacoes(List<Resumo> resumos, List<String> destinatarios);
	void enviarEmailComentariosFrequentesAvaliacoes(List<String> comentarios, List<String> destinatarios);
}
package br.com.relatorio.services.interfaces;

public interface IEmailService {
	//void enviarEmailResumoAvaliacoes(List<Resumo> resumos, List<String> destinatarios);
	//void enviarEmailComentariosFrequentesAvaliacoes(List<String> comentarios, List<String> destinatarios);
	void enviarEmail(String mensagem, String assunto);
}
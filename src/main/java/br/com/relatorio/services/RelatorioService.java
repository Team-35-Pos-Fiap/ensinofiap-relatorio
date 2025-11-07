package br.com.relatorio.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.relatorio.entities.ResumoAvaliacao;
import br.com.relatorio.entities.db.UsuarioDB;
import br.com.relatorio.services.interfaces.IEmailService;
import br.com.relatorio.services.interfaces.IRelatorioService;
import br.com.relatorio.services.interfaces.IResumoAvaliacaoService;
import br.com.relatorio.services.interfaces.IUsuarioService;
import jakarta.inject.Inject;

public class RelatorioService implements IRelatorioService {

	@Inject
	private IResumoAvaliacaoService resumoAvaliacaoService;
	
	@Inject
	private IEmailService emailService;
	
	@Inject
	private IUsuarioService usuarioService;
	
	@Override
	public void gerarRelatorioSemanal() {
		enviarEmail(buscarResumoAvaliacoes());
	}
		
	private List<ResumoAvaliacao> buscarResumoAvaliacoes() {
		return resumoAvaliacaoService.buscarAvaliacoesPorPeriodo(getDataInicial(), getDataFinal());
	}
	
	private LocalDate getDataFinal() {
		return getDataAtual().minusDays(8);
	}

	private LocalDate getDataInicial() {
		return getDataAtual().minusDays(1);
	}
	
	private LocalDate getDataAtual() {
		return LocalDate.now();
	}

	private void enviarEmail(List<ResumoAvaliacao> resumos) {
		emailService.enviarEmail(resumos, buscarEmailsDestinatarios());
	}
	
	private List<String> buscarEmailsDestinatarios() {
		List<UsuarioDB> usuarios = usuarioService.buscarAdministradores();
		
		return usuarios.stream().map(u -> u.getEmail()).collect(Collectors.toList());
	}
}
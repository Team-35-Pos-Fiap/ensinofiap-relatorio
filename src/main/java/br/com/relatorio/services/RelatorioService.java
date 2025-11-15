package br.com.relatorio.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.relatorio.entities.Resumo;
import br.com.relatorio.entities.db.UsuarioDB;
import br.com.relatorio.services.interfaces.IEmailService;
import br.com.relatorio.services.interfaces.IRelatorioService;
import br.com.relatorio.services.interfaces.IResumoAvaliacaoService;
import br.com.relatorio.services.interfaces.IUsuarioService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
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
		
	private List<Resumo> buscarResumoAvaliacoes() {
		return resumoAvaliacaoService.buscarAvaliacoesPorPeriodo(getDataInicial(), getDataFinal());
	}
	
	private LocalDate getDataFinal() {
		return getDataAtual().minusDays(1);
	}

	private LocalDate getDataInicial() {
		return getDataAtual().minusDays(30);
	}
	
	private LocalDate getDataAtual() {
		return LocalDate.now();
	}

	private void enviarEmail(List<Resumo> resumos) {
		resumos.stream().forEach(r -> System.out.println("Dados das avaliações: " + r.toString()));

		emailService.enviarEmail(resumos, buscarEmailsDestinatarios());
	}
	
	private List<String> buscarEmailsDestinatarios() {
		List<UsuarioDB> usuarios = usuarioService.buscarAdministradores();
		
		usuarios.stream().forEach(r -> System.out.println("Dados dos usuários: " + r.toString()));

		return usuarios.stream().map(u -> u.getEmail()).collect(Collectors.toList());
	}
}
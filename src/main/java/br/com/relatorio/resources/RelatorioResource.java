package br.com.relatorio.resources;

import br.com.relatorio.services.interfaces.IRelatorioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.util.List;
import java.util.ArrayList;
import br.com.relatorio.services.ResumoAvaliacoesService;
import br.com.relatorio.services.AvaliacoesFrequentesService;
import jakarta.enterprise.inject.Instance;

@Path("/ensino/relatorios")
public class RelatorioResource {

	@Inject
	private Instance<IRelatorioService> relatoriosService;
	
    @POST
    @Path("/processar")
    public void processarRelatorios() {
    	relatoriosService.stream().forEach(IRelatorioService::gerarRelatorio);
    }
}
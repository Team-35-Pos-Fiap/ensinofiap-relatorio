package br.com.relatorio.resources;

import br.com.relatorio.services.interfaces.IRelatorioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/relatorio")
public class RelatorioResource {

	@Inject
	private IRelatorioService relatorioService;
	
    @GET
    @Path("/semanal")
    public void gerarRelatorioSemanal() {
        System.out.println("iniciando o envio dos relatórios.");

    	relatorioService.gerarRelatorio();
    }
}
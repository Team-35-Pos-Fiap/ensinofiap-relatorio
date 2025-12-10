package br.com.relatorio.resources;

import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;

import br.com.relatorio.services.interfaces.IRelatorioService;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

//@Path("/ensino/relatorios")
public class RelatorioResource {

	@Inject
	private Instance<IRelatorioService> relatoriosService;
	
    @FunctionName("fnc-relatorio-avaliacoes")
    public void processarRelatorios(@TimerTrigger(name = "agendamento", schedule = "0 */1 * * * *") String agendamento) {
    	relatoriosService.stream().forEach(IRelatorioService::gerarRelatorio);
    }

    /*@POST
    @Path("/processar")
    public void processarRelatorios() {
    	relatoriosService.stream().forEach(IRelatorioService::gerarRelatorio);
    }*/
}
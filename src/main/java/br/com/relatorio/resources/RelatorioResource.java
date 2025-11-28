package br.com.relatorio.resources;

import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;

import br.com.relatorio.services.interfaces.IRelatorioService;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

//@Path("/ensino/relatorios")
public class RelatorioResource {

	@Inject
	private Instance<IRelatorioService> relatoriosService;
	
    //@POST
    //@Path("/processar")
    @FunctionName("fnc-ensinorelatorio")
    public void processarRelatorios(@TimerTrigger(name = "agendamento", schedule = "0 */1 * * * *") String agendamento) {
    	relatoriosService.stream().forEach(IRelatorioService::gerarRelatorio);
    }
}
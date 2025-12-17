package br.com.relatorio.resources;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.TimerTrigger;

import br.com.relatorio.services.interfaces.IRelatorioService;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.Optional;

//@Path("/ensino/relatorios")
public class RelatorioResource {

	@Inject
	private Instance<IRelatorioService> relatoriosService;

//    @POST
    @FunctionName("fnc-relatorio-avaliacoes")
    public void processarRelatorios(
            @TimerTrigger(name = "agendamento", schedule = "0 */1 * * * *")
            String agendamento, final ExecutionContext execucao,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

//    public void processarRelatorios(@TimerTrigger(name = "agendamento", schedule = "0 */1 * * * *") String agendamento, final ExecutionContext execucao) {
    	relatoriosService.stream().forEach(IRelatorioService::gerarRelatorio);
    }

    /*@POST
    @Path("/processar")
    public void processarRelatorios() {
    	relatoriosService.stream().forEach(IRelatorioService::gerarRelatorio);
    }*/
}

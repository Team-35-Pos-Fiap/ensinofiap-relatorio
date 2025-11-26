package br.com.relatorio.resources;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.relatorio.entities.db.AvaliacaoDB;
import br.com.relatorio.entities.db.CursoDB;

public class Teste {
	public static void main(String[] args) {
		var c1 = new CursoDB(null, "Info", null);
		var c2 = new CursoDB(null, "Culinaria", null);

		var a1 = new AvaliacaoDB(null, "Teste", c1, null, LocalDateTime.now(), true, 1);
		var a2 = new AvaliacaoDB(null, "Teste2", c1, null, LocalDateTime.now(), true, 2);

		var avaliacoes = new ArrayList<AvaliacaoDB>();
		
		avaliacoes.add(a1);
		avaliacoes.add(a2);
	
		c1.setAvaliacoes(avaliacoes);
		c2.setAvaliacoes(avaliacoes);
		
		var cursos = new ArrayList<CursoDB>();
		
		cursos.add(c1);
		cursos.add(c2);
		
		List<String> mensagens;
		try {
			mensagens = recuperaComentarios(cursos);

			//System.out.println(mensagens);
			System.out.println(montaListaComentariosFrequentesAvaliacoes(mensagens));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public static String recuperarComentario(CursoDB curso) {
		List<AvaliacaoDB> avaliacoes = curso.getAvaliacoes();
		
		String descricaoCurso = "<b>Curso: " + curso.getNome() + "</b>" + System.lineSeparator();
		
		String comentarios = avaliacoes.stream()
									   .map(a -> "  <li>" + a.getDescricao() + "  </li>")
									   .collect(Collectors.joining("\n", "<ul>\n", "\n</ul>"));		
		
		return descricaoCurso + comentarios + System.lineSeparator();
	}
	
	public static List<String> recuperaComentarios(List<CursoDB> cursos) {
		return cursos.stream().map(c -> recuperarComentario(c)).collect(Collectors.toList());
	}
	
	private static String montaListaComentariosFrequentesAvaliacoes(List<String> comentarios) {
		String mensagem = "";
		
		
		return 		comentarios.stream().collect(Collectors.joining("\n"));		
	}
}

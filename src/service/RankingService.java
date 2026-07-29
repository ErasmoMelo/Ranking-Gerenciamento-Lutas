package service;

import model.CategoriaPeso;
import model.Lutador;
import model.Organizacao;
import model.Ranking;

/**
 * Classe responsável por gerar e exibir o ranking dos lutadores de uma
 * organização com base na pontuação calculada.
 */
public class RankingService {

	/**
	 * Gera e exibe o ranking...
	 */
	public void gerarRanking(Organizacao organizacao) {

		if (organizacao == null) {
			System.out.println("Organização inválida.");
			return;
		}

		if (organizacao.getLutadores().isEmpty()) {
			System.out.println("Nenhum lutador cadastrado na organização.");
			return;
		}

		System.out.println("\n================================");
		System.out.println(" RANKING - " + organizacao.getNome());
		System.out.println("================================");

		// Adiciona ao ranking apenas os lutadores da categoria atual.
		for (CategoriaPeso categoria : CategoriaPeso.values()) {

			Ranking ranking = new Ranking(categoria);

			for (Lutador lutador : organizacao.getLutadores()) {

				if (lutador.getCategoria() == categoria) {
					ranking.adicionarLutador(lutador);
				}

			}

			// Organiza e exibe o ranking da categoria.
			ranking.ordenarRanking();
			ranking.mostrarRanking();

		}

	}

}
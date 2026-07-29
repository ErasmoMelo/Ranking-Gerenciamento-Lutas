package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Representa o ranking de uma categoria de peso, armazenando os lutadores e
 * realizando sua ordenação conforme os critérios definidos pelo sistema.
 */
public class Ranking {

	private final CategoriaPeso categoria;
	private final List<Lutador> lutadores;

	/**
	 * Cria um ranking para uma categoria de peso.
	 *
	 * @param categoria Categoria do ranking.
	 */
	public Ranking(CategoriaPeso categoria) {
		this.categoria = categoria;
		this.lutadores = new ArrayList<>();
	}

	public CategoriaPeso getCategoria() {
		return categoria;
	}

	public List<Lutador> getLutadores() {
		return lutadores;
	}

	/**
	 * Adiciona um lutador ao ranking.
	 *
	 * @param lutador Lutador a ser adicionado.
	 */
	public void adicionarLutador(Lutador lutador) {

		if (lutador != null) {
			lutadores.add(lutador);
		}

	}

	/**
	 * Ordena o ranking, priorizando os campeões e, em seguida, a maior pontuação
	 * dos lutadores.
	 */
	public void ordenarRanking() {

		// Ordena os lutadores por campeão e pontuação.
		lutadores.sort(Comparator.comparing(Lutador::isCampeao).reversed()
				.thenComparing(Comparator.comparingDouble(Lutador::calcularPontuacao).reversed()));

	}

	/**
	 * Exibe o ranking da categoria no console.
	 */
	public void mostrarRanking() {

		System.out.println("\n======================");
		System.out.println(categoria.getDescricao());
		System.out.println("======================");

		if (lutadores.isEmpty()) {
			System.out.println("Nenhum lutador cadastrado.");
			return;
		}

		int posicao = 1;

		// Percorre e exibe todos os lutadores do ranking.
		for (Lutador lutador : lutadores) {

			String titulo = lutador.isCampeao() ? " 🏆 CAMPEÃO" : "";

			System.out.println(posicao + "º " + lutador.getNome() + titulo);

			System.out.printf("Pontuação: %.1f%n", lutador.calcularPontuacao());

			System.out.println("----------------------");

			posicao++;
		}
	}
}
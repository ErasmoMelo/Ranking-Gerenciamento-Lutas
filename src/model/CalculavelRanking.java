package model;

/**
 * Interface que define o contrato para o cálculo da pontuação utilizada na
 * geração do ranking.
 */
public interface CalculavelRanking {

	/**
	 * Calcula a pontuação do objeto para o ranking.
	 *
	 * @return Pontuação calculada.
	 */
	double calcularPontuacao();

}
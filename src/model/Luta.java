package model;

/**
 * Representa uma luta realizada entre dois lutadores, armazenando o vencedor, o
 * perdedor, o método da vitória e o round em que a luta foi encerrada.
 */
public class Luta {

	private final Lutador vencedor;
	private final Lutador perdedor;

	private final String metodo;
	private final int round;

	/**
	 * Cria um registro de uma luta.
	 *
	 * @param vencedor Lutador vencedor da luta.
	 * @param perdedor Lutador derrotado.
	 * @param metodo   Método da vitória.
	 * @param round    Round em que a luta terminou.
	 */
	public Luta(Lutador vencedor, Lutador perdedor, String metodo, int round) {

		this.vencedor = vencedor;
		this.perdedor = perdedor;
		this.metodo = metodo;
		this.round = round;
	}

	public Lutador getVencedor() {
		return vencedor;
	}

	public Lutador getPerdedor() {
		return perdedor;
	}

	public String getMetodo() {
		return metodo;
	}

	public int getRound() {
		return round;
	}
}
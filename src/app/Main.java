package app;

import util.Menu;

/**
 * Classe principal da aplicação. Responsável por iniciar o sistema de ranking
 * de lutadores.
 */
public class Main {

	/**
	 * Método responsável por iniciar a execução do programa.
	 *
	 * @param args argumentos da linha de comando.
	 */
	public static void main(String[] args) {

		Menu menu = new Menu();
		menu.iniciar();

	}

}
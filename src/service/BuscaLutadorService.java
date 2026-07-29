package service;

import model.Lutador;

/**
 * Classe responsável por realizar a busca de lutadores utilizando a API interna
 * do sistema.
 */
public class BuscaLutadorService {

	private LutadorAPIInternaService api;

	/**
	 * Inicializa o serviço responsável pela comunicação com a API interna de busca
	 * de lutadores.
	 */
	public BuscaLutadorService() {

		api = new LutadorAPIInternaService();

	}

	/**
	 * Realiza a busca de um lutador pelo nome.
	 *
	 * @param nome Nome do lutador.
	 * @return Lutador encontrado ou null caso não exista.
	 */
	public Lutador buscar(String nome) {

		return api.buscarLutador(nome);

	}

}

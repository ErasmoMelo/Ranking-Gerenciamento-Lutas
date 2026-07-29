package service;

import java.util.ArrayList;
import java.util.List;

import model.CategoriaPeso;
import model.Lutador;

/**
 * Classe que simula uma API interna contendo uma base de dados de lutadores
 * pré-cadastrados. Permite a busca de atletas pelo nome.
 */
public class LutadorAPIInternaService {

	private List<Lutador> lutadores;

	/**
	 * Inicializa a lista de lutadores e carrega os dados disponíveis na API
	 * interna.
	 */
	public LutadorAPIInternaService() {

		lutadores = new ArrayList<>();

		carregarLutadores();

	}

	/**
	 * Carrega na memória os lutadores disponíveis para consulta através da API
	 * interna.
	 */
	private void carregarLutadores() {

		// PESO MOSCA

		lutadores.add(criar("Alexandre Pantoja", "Brasil", CategoriaPeso.MOSCA, 29, 5, 8, 5, true));

		lutadores.add(criar("Manel Kape", "Angola", CategoriaPeso.MOSCA, 20, 6, 11, 3, false));

		lutadores.add(criar("Tatsuro Taira", "Japão", CategoriaPeso.MOSCA, 16, 0, 4, 10, false));

		lutadores.add(criar("Brandon Royval", "Estados Unidos", CategoriaPeso.MOSCA, 17, 7, 3, 9, false));

		// PESO GALO

		lutadores.add(criar("Petr Yan", "Rússia", CategoriaPeso.GALO, 18, 5, 7, 1, true));

		lutadores.add(criar("Merab Dvalishvili", "Geórgia", CategoriaPeso.GALO, 19, 4, 3, 1, false));

		lutadores.add(criar("Sean O'Malley", "Estados Unidos", CategoriaPeso.GALO, 18, 2, 12, 0, false));

		lutadores.add(criar("Umar Nurmagomedov", "Rússia", CategoriaPeso.GALO, 18, 1, 7, 5, false));

		// PESO PENA

		lutadores.add(criar("Alexander Volkanovski", "Austrália", CategoriaPeso.PENA, 26, 4, 13, 3, true));

		lutadores.add(criar("Movsar Evloev", "Rússia", CategoriaPeso.PENA, 19, 0, 3, 7, false));

		lutadores.add(criar("Diego Lopes", "Brasil", CategoriaPeso.PENA, 26, 6, 10, 12, false));

		lutadores.add(criar("Yair Rodriguez", "México", CategoriaPeso.PENA, 19, 5, 7, 4, false));

		// PESO LEVE

		lutadores.add(criar("Islam Makhachev", "Rússia", CategoriaPeso.LEVE, 28, 1, 5, 12, false));

		lutadores.add(criar("Ilia Topuria", "Espanha", CategoriaPeso.LEVE, 17, 0, 14, 0, true));

		lutadores.add(criar("Charles Oliveira", "Brasil", CategoriaPeso.LEVE, 35, 10, 10, 16, false));

		lutadores.add(criar("Justin Gaethje", "Estados Unidos", CategoriaPeso.LEVE, 27, 5, 20, 1, false));

		// MEIO-MÉDIO

		lutadores.add(criar("Jack Della Maddalena", "Austrália", CategoriaPeso.MEIO_MEDIO, 18, 2, 12, 2, true));

		lutadores.add(criar("Belal Muhammad", "Estados Unidos", CategoriaPeso.MEIO_MEDIO, 24, 4, 5, 1, false));

		lutadores.add(criar("Leon Edwards", "Inglaterra", CategoriaPeso.MEIO_MEDIO, 22, 4, 7, 3, false));

		lutadores.add(criar("Kamaru Usman", "Nigéria", CategoriaPeso.MEIO_MEDIO, 20, 4, 9, 1, false));

		// PESO MÉDIO

		lutadores.add(criar("Dricus Du Plessis", "África do Sul", CategoriaPeso.MEDIO, 22, 2, 9, 10, true));

		lutadores.add(criar("Khamzat Chimaev", "Rússia", CategoriaPeso.MEDIO, 15, 0, 6, 5, false));

		lutadores.add(criar("Israel Adesanya", "Nigéria", CategoriaPeso.MEDIO, 24, 5, 16, 0, false));

		lutadores.add(criar("Sean Strickland", "Estados Unidos", CategoriaPeso.MEDIO, 29, 6, 12, 5, false));

		// MEIO-PESADO

		lutadores.add(criar("Alex Pereira", "Brasil", CategoriaPeso.MEIO_PESADO, 12, 3, 10, 0, true));

		lutadores.add(criar("Magomed Ankalaev", "Rússia", CategoriaPeso.MEIO_PESADO, 19, 2, 9, 1, false));

		lutadores.add(criar("Jiri Prochazka", "República Tcheca", CategoriaPeso.MEIO_PESADO, 31, 5, 26, 3, false));

		lutadores.add(criar("Jamahal Hill", "Estados Unidos", CategoriaPeso.MEIO_PESADO, 12, 3, 7, 0, false));

		// PESADO

		lutadores.add(criar("Tom Aspinall", "Inglaterra", CategoriaPeso.PESADO, 15, 3, 12, 3, true));

		lutadores.add(criar("Ciryl Gane", "França", CategoriaPeso.PESADO, 13, 2, 5, 3, false));

		lutadores.add(criar("Alexander Volkov", "Rússia", CategoriaPeso.PESADO, 38, 11, 22, 3, false));

		lutadores.add(criar("Sergei Pavlovich", "Rússia", CategoriaPeso.PESADO, 19, 3, 15, 1, false));

	}

	/**
	 * Procura um lutador pelo nome na base de dados da API.
	 *
	 * @param nome Nome do lutador.
	 * @return Lutador encontrado ou null caso não exista.
	 */
	public Lutador buscarLutador(String nome) {

		for (Lutador lutador : lutadores) {

			if (lutador.getNome().equalsIgnoreCase(nome)) {

				return lutador;

			}

		}

		return null;

	}

	/**
	 * Cria e inicializa um objeto Lutador com os dados informados.
	 *
	 * @param nome         Nome do lutador.
	 * @param pais         País de origem.
	 * @param categoria    Categoria de peso.
	 * @param vitorias     Quantidade de vitórias.
	 * @param derrotas     Quantidade de derrotas.
	 * @param nocautes     Quantidade de vitórias por nocaute.
	 * @param finalizacoes Quantidade de vitórias por finalização.
	 * @param campeao      Indica se o lutador é campeão.
	 * @return Objeto Lutador configurado.
	 */
	private Lutador criar(String nome, String pais, CategoriaPeso categoria, int vitorias, int derrotas, int nocautes,
			int finalizacoes, boolean campeao) {

		Lutador lutador = new Lutador(nome, pais, categoria, "UFC");

		lutador.setVitorias(vitorias);

		lutador.setDerrotas(derrotas);

		lutador.setNocautes(nocautes);

		lutador.setFinalizacoes(finalizacoes);

		lutador.setCampeao(campeao);

		return lutador;

	}

}
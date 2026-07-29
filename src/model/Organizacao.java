package model;

import java.util.ArrayList;
import java.util.List;

import exception.SistemaException;

/**
 * Representa uma organização de lutas, armazenando seus lutadores e o histórico
 * das lutas realizadas.
 */
public class Organizacao {

	private final String nome;
	private final List<Lutador> lutadores;
	private final List<Luta> historicoLutas;

	/**
	 * Cria uma organização com o nome informado.
	 *
	 * @param nome Nome da organização.
	 */
	public Organizacao(String nome) {

		this.nome = nome;
		this.lutadores = new ArrayList<>();
		this.historicoLutas = new ArrayList<>();

	}

	public String getNome() {
		return nome;
	}

	public List<Lutador> getLutadores() {
		return lutadores;
	}

	public List<Luta> getHistoricoLutas() {
		return historicoLutas;
	}

	/**
	 * Adiciona uma luta ao histórico da organização.
	 *
	 * @param luta Luta realizada.
	 */
	public void adicionarLuta(Luta luta) {

		if (luta != null) {
			historicoLutas.add(luta);
		}

	}

	/**
	 * Adiciona um novo lutador à organização, verificando regras de cadastro e
	 * campeões.
	 *
	 * @param lutador Lutador a ser adicionado.
	 * @throws SistemaException Caso o cadastro seja inválido.
	 */
	public void adicionarLutador(Lutador lutador) throws SistemaException {

		if (lutador == null) {

			throw new SistemaException("Lutador inválido.");

		}

		// Verifica se já existe um lutador com o mesmo nome.
		for (Lutador l : lutadores) {

			if (l.getNome().equalsIgnoreCase(lutador.getNome())) {

				throw new SistemaException("Já existe um lutador com esse nome nesta organização.");

			}

		}

		// Garante que exista apenas um campeão por categoria.
		if (lutador.isCampeao()) {

			for (Lutador l : lutadores) {

				if (l.getCategoria() == lutador.getCategoria() && l.isCampeao()) {

					throw new SistemaException(
							"Já existe um campeão na categoria " + lutador.getCategoria().getDescricao());

				}

			}

		}

		lutadores.add(lutador);

	}

}
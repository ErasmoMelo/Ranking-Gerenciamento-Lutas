package service;

import java.util.ArrayList;
import java.util.List;

import exception.SistemaException;
import model.Luta;
import model.Lutador;
import model.Organizacao;

/**
 * Classe responsável pelo gerenciamento das organizações, cadastro de lutadores
 * e consultas realizadas no sistema.
 */
public class CadastroService {

	private List<Organizacao> organizacoes;

	private ArquivoService arquivoService;

	/**
	 * Inicializa a lista de organizações e realiza o carregamento dos dados
	 * armazenados em arquivos.
	 */
	public CadastroService() {

		organizacoes = new ArrayList<>();

		arquivoService = new ArquivoService();

		arquivoService.carregarDados(organizacoes);

	}

	/**
	 * Adiciona uma nova organização ao sistema e salva as informações no arquivo de
	 * persistência.
	 *
	 * @param nome Nome da organização.
	 */
	public void adicionarOrganizacao(String nome) throws SistemaException {

		if (nome == null || nome.trim().isEmpty()) {

			throw new SistemaException("O nome da organização não pode estar vazio.");

		}

		for (Organizacao organizacao : organizacoes) {

			if (organizacao.getNome().equalsIgnoreCase(nome)) {

				throw new SistemaException("Já existe uma organização com esse nome.");

			}

		}

		organizacoes.add(new Organizacao(nome));

		arquivoService.salvarOrganizacoes(organizacoes);

		System.out.println("Organização cadastrada com sucesso!");

	}

	public List<Organizacao> getOrganizacoes() {

		return organizacoes;

	}

	/**
	 * Exibe todas as organizações cadastradas.
	 */
	public void listarOrganizacoes() {

		if (organizacoes.isEmpty()) {

			System.out.println("Nenhuma organização cadastrada.");
			return;

		}

		for (int i = 0; i < organizacoes.size(); i++) {

			System.out.println((i + 1) + " - " + organizacoes.get(i).getNome());

		}

	}

	/**
	 * Retorna a organização correspondente ao índice informado.
	 *
	 * @param indice Índice da organização.
	 * @return Organização encontrada ou null caso não exista.
	 */
	public Organizacao buscarOrganizacao(int indice) {

		if (indice >= 0 && indice < organizacoes.size()) {

			return organizacoes.get(indice);

		}

		return null;

	}

	/**
	 * Exibe todos os lutadores cadastrados em cada organização.
	 */
	public void listarLutadores() {

		if (organizacoes.isEmpty()) {

			System.out.println("Nenhuma organização cadastrada.");
			return;

		}

		for (Organizacao org : organizacoes) {

			System.out.println("\n========================");
			System.out.println(org.getNome());
			System.out.println("========================");

			if (org.getLutadores().isEmpty()) {

				System.out.println("Nenhum lutador cadastrado.");

			} else {

				for (Lutador lutador : org.getLutadores()) {

					System.out.println("Nome: " + lutador.getNome());
					System.out.println("País: " + lutador.getPais());
					System.out.println("Categoria: " + lutador.getCategoria().getDescricao());

					System.out.println("Cartel: " + lutador.getVitorias() + "-" + lutador.getDerrotas() + "-"
							+ lutador.getEmpates());

					System.out.printf("Pontuação: %.1f%n", lutador.calcularPontuacao());

					System.out.println("------------------------");

				}

			}

		}

	}

	/**
	 * Exibe os campeões de cada organização, separados por categoria de peso.
	 */
	public void mostrarCampeoes() {

		if (organizacoes.isEmpty()) {

			System.out.println("Nenhuma organização cadastrada.");
			return;

		}

		for (Organizacao organizacao : organizacoes) {

			System.out.println("\n==============================");
			System.out.println("CAMPEÕES - " + organizacao.getNome());
			System.out.println("==============================");

			boolean encontrou = false;

			for (Lutador lutador : organizacao.getLutadores()) {

				if (lutador.isCampeao()) {

					System.out.println(lutador.getCategoria().getDescricao() + " 🏆 " + lutador.getNome());

					encontrou = true;

				}

			}

			if (!encontrou) {

				System.out.println("Nenhum campeão cadastrado.");

			}

		}

	}

	/**
	 * Procura um lutador pelo nome em todas as organizações.
	 *
	 * @param nome Nome do lutador.
	 * @return Lutador encontrado ou null.
	 */
	public Lutador buscarLutador(String nome) {

		for (Organizacao organizacao : organizacoes) {

			for (Lutador lutador : organizacao.getLutadores()) {

				if (lutador.getNome().equalsIgnoreCase(nome)) {

					return lutador;

				}

			}

		}

		return null;

	}

	/**
	 * Localiza a organização à qual o lutador pertence.
	 *
	 * @param lutador Lutador informado.
	 * @return Organização correspondente ou null.
	 */
	public Organizacao buscarOrganizacaoDoLutador(Lutador lutador) {

		for (Organizacao organizacao : organizacoes) {

			if (organizacao.getLutadores().contains(lutador)) {

				return organizacao;

			}

		}

		return null;

	}

	public void mostrarHistoricoLutas() {

		arquivoService.mostrarHistorico();

	}

	/**
	 * Salva as organizações e os lutadores nos arquivos de persistência.
	 */
	public void salvarDados() {

		arquivoService.salvarOrganizacoes(organizacoes);

		arquivoService.salvarLutadores(organizacoes);

	}

}
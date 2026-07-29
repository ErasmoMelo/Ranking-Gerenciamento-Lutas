package service;

import exception.SistemaException;
import model.CategoriaPeso;
import model.Luta;
import model.Lutador;
import model.Organizacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Classe responsável pela persistência dos dados do sistema. Realiza a leitura
 * e escrita das informações de organizações, lutadores e histórico de lutas em
 * arquivos texto.
 */
public class ArquivoService {

	private static final String PASTA = "dados";

	private static final String ARQ_ORGANIZACOES = PASTA + "/organizacoes.txt";

	private static final String ARQ_LUTADORES = PASTA + "/lutadores.txt";

	private static final String ARQ_HISTORICO = PASTA + "/historico.txt";

	/**
	 * Inicializa a estrutura de arquivos utilizada pelo sistema.
	 */
	public ArquivoService() {

		criarArquivos();

	}

	/**
	 * Cria a pasta e os arquivos necessários para armazenar as informações da
	 * aplicação, caso ainda não existam.
	 */
	private void criarArquivos() {

		try {

			File pasta = new File(PASTA);

			if (!pasta.exists()) {

				pasta.mkdir();

			}

			criarArquivo(ARQ_ORGANIZACOES);
			criarArquivo(ARQ_LUTADORES);
			criarArquivo(ARQ_HISTORICO);

		} catch (IOException e) {

			System.out.println("Erro ao criar arquivos: " + e.getMessage());

		}

	}

	/**
	 * Cria um arquivo caso ele ainda não exista.
	 *
	 * @param caminho Caminho do arquivo.
	 * @throws IOException Caso ocorra erro durante a criação.
	 */
	private void criarArquivo(String caminho) throws IOException {

		File arquivo = new File(caminho);

		if (!arquivo.exists()) {

			arquivo.createNewFile();

		}

	}

	/**
	 * Salva todas as organizações cadastradas em arquivo.
	 *
	 * @param organizacoes Lista de organizações.
	 */
	public void salvarOrganizacoes(List<Organizacao> organizacoes) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQ_ORGANIZACOES))) {

			for (Organizacao organizacao : organizacoes) {

				writer.write(organizacao.getNome());

				writer.newLine();

			}

		} catch (IOException e) {

			System.out.println("Erro ao salvar organizações: " + e.getMessage());

		}

	}

	/**
	 * Salva todos os lutadores cadastrados em arquivo.
	 *
	 * @param organizacoes Lista de organizações contendo os lutadores.
	 */
	public void salvarLutadores(List<Organizacao> organizacoes) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQ_LUTADORES))) {

			for (Organizacao organizacao : organizacoes) {

				for (Lutador lutador : organizacao.getLutadores()) {

					writer.write(

							lutador.getNome() + ";"

									+ lutador.getPais() + ";"

									+ lutador.getCategoria().name() + ";"

									+ lutador.getOrganizacao() + ";"

									+ lutador.getVitorias() + ";"

									+ lutador.getDerrotas() + ";"

									+ lutador.getEmpates() + ";"

									+ lutador.getNocautes() + ";"

									+ lutador.getFinalizacoes() + ";"

									+ lutador.isCampeao()

					);

					writer.newLine();

				}

			}

		} catch (IOException e) {

			System.out.println("Erro ao salvar lutadores: " + e.getMessage());

		}

	}

	/**
	 * Registra uma luta no arquivo de histórico.
	 *
	 * @param luta Luta realizada.
	 */
	public void salvarLuta(Luta luta) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQ_HISTORICO, true))) {

			writer.write(

					luta.getVencedor().getNome() + ";"

							+ luta.getPerdedor().getNome() + ";"

							+ luta.getMetodo() + ";"

							+ luta.getRound()

			);

			writer.newLine();

		} catch (IOException e) {

			System.out.println("Erro ao salvar luta: " + e.getMessage());

		}

	}

	/**
	 * Carrega todas as organizações e lutadores armazenados nos arquivos do
	 * sistema.
	 *
	 * @param organizacoes Lista onde os dados serão carregados.
	 */
	public void carregarDados(List<Organizacao> organizacoes) {

		organizacoes.clear();

		carregarOrganizacoes(organizacoes);

		carregarLutadores(organizacoes);

	}

	/**
	 * Realiza a leitura das organizações cadastradas.
	 *
	 * @param organizacoes Lista que receberá as organizações.
	 */
	private void carregarOrganizacoes(List<Organizacao> organizacoes) {

		try (BufferedReader reader = new BufferedReader(new FileReader(ARQ_ORGANIZACOES))) {

			String linha;

			while ((linha = reader.readLine()) != null) {

				if (!linha.trim().isEmpty()) {

					organizacoes.add(new Organizacao(linha));

				}

			}

		} catch (IOException e) {

			System.out.println("Erro ao carregar organizações: " + e.getMessage());

		}

	}

	/**
	 * Realiza a leitura dos lutadores cadastrados e os associa às respectivas
	 * organizações.
	 *
	 * @param organizacoes Lista de organizações carregadas.
	 */
	private void carregarLutadores(List<Organizacao> organizacoes) {

		try (BufferedReader reader = new BufferedReader(new FileReader(ARQ_LUTADORES))) {

			String linha;

			while ((linha = reader.readLine()) != null) {

				String[] dados = linha.split(";");

				if (dados.length != 10) {

					continue;

				}

				// Reconstrói o objeto Lutador utilizando os dados do arquivo.
				Lutador lutador = new Lutador(dados[0],

						dados[1],

						CategoriaPeso.valueOf(dados[2]),

						dados[3]

				);

				lutador.setVitorias(Integer.parseInt(dados[4]));

				lutador.setDerrotas(Integer.parseInt(dados[5]));

				lutador.setEmpates(Integer.parseInt(dados[6]));

				lutador.setNocautes(Integer.parseInt(dados[7]));

				lutador.setFinalizacoes(Integer.parseInt(dados[8]));

				lutador.setCampeao(Boolean.parseBoolean(dados[9]));

				for (Organizacao organizacao : organizacoes) {

					if (organizacao.getNome().equalsIgnoreCase(lutador.getOrganizacao())) {

						try {

							// Adiciona o lutador à organização correspondente.
							organizacao.adicionarLutador(lutador);
						} catch (SistemaException e) {

							System.out.println(e.getMessage());

						}

						break;

					}

				}

			}

		} catch (IOException e) {

			System.out.println("Erro ao carregar lutadores: " + e.getMessage());

		}

	}

	/**
	 * Exibe todas as lutas registradas no histórico da aplicação.
	 */
	public void mostrarHistorico() {

		try (BufferedReader reader = new BufferedReader(new FileReader(ARQ_HISTORICO))) {

			String linha;

			boolean vazio = true;

			System.out.println("\n========= HISTÓRICO DE LUTAS =========");

			// Lê cada linha do arquivo até o final.
			while ((linha = reader.readLine()) != null) {
				vazio = false;

				// Separa os dados gravados no arquivo.
				String[] dados = linha.split(";");
				if (dados.length < 4) {
					continue;
				}

				System.out.println(dados[0] + " venceu " + dados[1]);

				System.out.println("Método: " + dados[2]);

				System.out.println("Round: " + dados[3]);

				System.out.println("------------------------------------------------");

			}

			if (vazio) {

				System.out.println("Nenhuma luta registrada.");

			}

		} catch (IOException e) {

			System.out.println("Erro ao carregar histórico: " + e.getMessage());

		}

	}

}
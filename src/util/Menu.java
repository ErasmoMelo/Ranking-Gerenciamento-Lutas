package util;

import java.util.Scanner;

import exception.SistemaException;
import model.CategoriaPeso;
import model.Lutador;
import model.Organizacao;
import service.BuscaLutadorService;
import service.CadastroService;
import service.LutaService;
import service.RankingService;

/**
 * Classe responsável pela interface em modo texto do sistema. Exibe o menu
 * principal e recebe as opções informadas pelo usuário.
 */
public class Menu {

	private Scanner scanner;
	private CadastroService cadastro;
	private RankingService rankingService;
	private LutaService lutaService;
	private BuscaLutadorService buscaLutadorService;

	/**
	 * Inicializa os serviços utilizados pelo sistema e o objeto responsável pela
	 * leitura das entradas do usuário.
	 */
	public Menu() {

		scanner = new Scanner(System.in);

		cadastro = new CadastroService();

		rankingService = new RankingService();

		lutaService = new LutaService();

		buscaLutadorService = new BuscaLutadorService();

	}

	/**
	 * Exibe o menu principal e executa as funcionalidades escolhidas pelo usuário.
	 */
	public void iniciar() {

		int opcao;

		do {

			System.out.println("\n===============================");
			System.out.println(" SISTEMA DE RANKING DE LUTADORES");
			System.out.println("===============================");
			System.out.println("1 - Adicionar Organização");
			System.out.println("2 - Listar Organizações");
			System.out.println("3 - Adicionar Lutador");
			System.out.println("4 - Listar Lutadores");
			System.out.println("5 - Mostrar Ranking");
			System.out.println("6 - Registrar Luta");
			System.out.println("7 - Mostrar Campeões");
			System.out.println("8 - Mostrar Histórico de Lutas");
			System.out.println("9 - Buscar Lutador");
			System.out.println("0 - Sair");

			System.out.print("Escolha: ");

			try {

				opcao = scanner.nextInt();
				scanner.nextLine();

			} catch (Exception e) {

				System.out.println("Opção inválida.");

				scanner.nextLine();

				opcao = -1;

				continue;

			}

			switch (opcao) {

			// Cadastro de organizações
			case 1:

				try {

					System.out.print("Nome da organização: ");

					String nome = scanner.nextLine();

					cadastro.adicionarOrganizacao(nome);

					cadastro.salvarDados();

				} catch (SistemaException e) {

					System.out.println(e.getMessage());

				}

				pausar();

				break;

			// Listagem das organizações cadastradas
			case 2:

				cadastro.listarOrganizacoes();

				pausar();

				break;

			// Cadastro de um novo lutador
			case 3:

				try {

					if (cadastro.getOrganizacoes().isEmpty()) {

						System.out.println("Cadastre uma organização primeiro.");

						pausar();

						break;

					}

					System.out.print("Nome do lutador: ");

					String nomeLutador = scanner.nextLine();

					System.out.print("País: ");

					String pais = scanner.nextLine();

					System.out.println("\nEscolha a organização:");

					cadastro.listarOrganizacoes();

					System.out.print("Opção: ");

					int escolhaOrg = scanner.nextInt();

					scanner.nextLine();

					Organizacao organizacao = cadastro.buscarOrganizacao(escolhaOrg - 1);

					if (organizacao == null) {

						System.out.println("Organização inválida.");

						pausar();

						break;

					}

					System.out.println("\nEscolha a categoria:");

					CategoriaPeso[] categorias = CategoriaPeso.values();

					for (int i = 0; i < categorias.length; i++) {

						System.out.println(

								(i + 1) + " - " + categorias[i].getDescricao()

						);

					}

					System.out.print("Opção: ");

					int escolhaCategoria = scanner.nextInt();

					scanner.nextLine();

					Lutador lutador = new Lutador(

							nomeLutador,

							pais,

							categorias[escolhaCategoria - 1],

							organizacao.getNome()

					);

					System.out.print("Vitórias: ");

					lutador.setVitorias(scanner.nextInt());

					System.out.print("Derrotas: ");

					lutador.setDerrotas(scanner.nextInt());

					System.out.print("Empates: ");

					lutador.setEmpates(scanner.nextInt());

					System.out.print("Nocautes: ");

					lutador.setNocautes(scanner.nextInt());

					System.out.print("Finalizações: ");

					lutador.setFinalizacoes(scanner.nextInt());

					scanner.nextLine();

					System.out.println("\nPossui cinturão?");

					System.out.println("1 - Sim");

					System.out.println("2 - Não");

					System.out.print("Escolha: ");

					int possuiCinturao = scanner.nextInt();

					scanner.nextLine();

					if (possuiCinturao == 1) {

						lutador.setCampeao(true);

					}

					organizacao.adicionarLutador(lutador);

					cadastro.salvarDados();

					System.out.println("\nLutador cadastrado com sucesso!");

				} catch (SistemaException e) {

					System.out.println(e.getMessage());

				}

				pausar();

				break;

			case 4:

				cadastro.listarLutadores();

				pausar();

				break;

			// Exibição do ranking da organização
			case 5:

				if (cadastro.getOrganizacoes().isEmpty()) {

					System.out.println("Nenhuma organização cadastrada.");

					pausar();

					break;

				}

				cadastro.listarOrganizacoes();

				System.out.print("Escolha a organização: ");

				int escolhaRanking = scanner.nextInt();

				scanner.nextLine();

				Organizacao organizacaoRanking = cadastro.buscarOrganizacao(escolhaRanking - 1);

				if (organizacaoRanking != null) {

					rankingService.gerarRanking(organizacaoRanking);

				} else {

					System.out.println("Organização inválida.");

				}

				pausar();

				break;

			// Registro de uma nova luta
			case 6:

				try {

					System.out.print("Nome do vencedor: ");

					String nomeVencedor = scanner.nextLine();

					Lutador vencedor = cadastro.buscarLutador(nomeVencedor);

					System.out.print("Nome do perdedor: ");

					String nomePerdedor = scanner.nextLine();

					Lutador perdedor = cadastro.buscarLutador(nomePerdedor);

					if (vencedor == null || perdedor == null) {

						throw new SistemaException("Lutador não encontrado.");

					}

					Organizacao organizacaoLuta = cadastro.buscarOrganizacaoDoLutador(vencedor);

					System.out.println("\nMétodo da vitória:");

					System.out.println("1 - Decisão");

					System.out.println("2 - Nocaute");

					System.out.println("3 - Finalização");

					System.out.print("Escolha: ");

					int metodoEscolha = scanner.nextInt();

					scanner.nextLine();

					String metodo = "Decisão";

					if (metodoEscolha == 2) {

						metodo = "Nocaute";

					}

					if (metodoEscolha == 3) {

						metodo = "Finalização";

					}

					lutaService.registrarVitoria(

							organizacaoLuta,

							vencedor,

							perdedor,

							metodo

					);

					cadastro.salvarDados();

				} catch (SistemaException e) {

					System.out.println(e.getMessage());

				}

				pausar();

				break;

			// Exibição dos campeões das organizações
			case 7:

				cadastro.mostrarCampeoes();

				pausar();

				break;

			// Exibição do histórico de lutas
			case 8:

				cadastro.mostrarHistoricoLutas();

				pausar();

				break;

			// Busca de um lutador utilizando a API interna
			case 9:

				try {

					System.out.print("Digite o nome do lutador: ");

					String nomeBusca = scanner.nextLine();

					Lutador lutadorEncontrado = buscaLutadorService.buscar(nomeBusca);

					if (lutadorEncontrado == null) {

						throw new SistemaException("Lutador não encontrado no sistema.");

					}

					System.out.println("\n===== LUTADOR ENCONTRADO =====");

					System.out.println("Nome: " + lutadorEncontrado.getNome());

					System.out.println("País: " + lutadorEncontrado.getPais());

					System.out.println("Categoria: " + lutadorEncontrado.getCategoria().getDescricao());

					System.out.println("Organização: " + lutadorEncontrado.getOrganizacao());

					System.out.println("Vitórias: " + lutadorEncontrado.getVitorias());

					System.out.println("Derrotas: " + lutadorEncontrado.getDerrotas());

					System.out.println("Nocautes: " + lutadorEncontrado.getNocautes());

					System.out.println("\nAdicionar ao ranking?");

					System.out.println("1 - Sim");
					System.out.println("2 - Não");

					System.out.print("Escolha: ");

					int confirmar = scanner.nextInt();

					scanner.nextLine();

					if (confirmar == 1) {

						if (cadastro.getOrganizacoes().isEmpty()) {

							System.out.println("Nenhuma organização cadastrada.");

							pausar();

							break;

						}

						System.out.println("\nEscolha a organização:");

						cadastro.listarOrganizacoes();

						System.out.print("Opção: ");

						int escolhaOrgAPI = scanner.nextInt();

						scanner.nextLine();

						Organizacao organizacaoAPI = cadastro.buscarOrganizacao(escolhaOrgAPI - 1);

						if (organizacaoAPI == null) {

							throw new SistemaException("Organização inválida.");

						}

						organizacaoAPI.adicionarLutador(lutadorEncontrado);

						cadastro.salvarDados();

						System.out.println("\nLutador adicionado ao ranking com sucesso!");

					} else {

						System.out.println("Cadastro cancelado.");

					}

				} catch (SistemaException e) {

					System.out.println(e.getMessage());

				}

				pausar();

				break;

			// Encerramento do sistema
			case 0:

				cadastro.salvarDados();

				System.out.println("Programa encerrado.");

				break;

			default:

				System.out.println("Opção inválida.");

			}

		} while (opcao != 0);

		scanner.close();

	}

	/**
	 * Aguarda o usuário pressionar ENTER antes de retornar ao menu.
	 */
	private void pausar() {

		System.out.println("\nPressione ENTER para continuar...");

		scanner.nextLine();

	}

}
package service;

import exception.SistemaException;
import model.Luta;
import model.Lutador;
import model.Organizacao;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo registro das lutas, atualização do cartel dos
 * lutadores e gerenciamento da troca de campeões.
 */
public class LutaService {

	private ArquivoService arquivoService;
	private static final int ROUND_PADRAO = 1;

	/**
	 * Inicializa o serviço responsável pela persistência das informações em
	 * arquivos.
	 */
	public LutaService() {

		arquivoService = new ArquivoService();

	}

	/**
	 * Registra uma luta entre dois lutadores, atualizando o cartel, verificando a
	 * troca de campeão e salvando o histórico da luta.
	 *
	 * @param organizacaoLuta Organização onde ocorreu a luta.
	 * @param vencedor        Lutador vencedor.
	 * @param perdedor        Lutador derrotado.
	 * @param metodo          Método da vitória.
	 * @throws SistemaException Caso a luta possua informações inválidas.
	 */
	public void registrarVitoria(Organizacao organizacaoLuta, Lutador vencedor, Lutador perdedor, String metodo)
			throws SistemaException {

		if (organizacaoLuta == null) {
			throw new SistemaException("Organização inválida.");
		}

		if (vencedor == null || perdedor == null) {
			throw new SistemaException("Os lutadores devem ser informados.");
		}

		if (metodo == null || metodo.trim().isEmpty()) {
			throw new SistemaException("Método da luta inválido.");
		}

		if (vencedor.equals(perdedor)) {
			throw new SistemaException("Um lutador não pode lutar contra ele mesmo.");
		}

		if (vencedor.getCategoria() != perdedor.getCategoria()) {
			throw new SistemaException("Os lutadores precisam ser da mesma categoria de peso.");
		}

		if (vencedor.getOrganizacao() == null || perdedor.getOrganizacao() == null
				|| !vencedor.getOrganizacao().equalsIgnoreCase(perdedor.getOrganizacao())) {

			throw new SistemaException("Os lutadores precisam pertencer à mesma organização.");
		}

		vencedor.setVitorias(vencedor.getVitorias() + 1);

		perdedor.setDerrotas(perdedor.getDerrotas() + 1);

		if (metodo.equalsIgnoreCase("Nocaute")) {
			vencedor.setNocautes(vencedor.getNocautes() + 1);
		}

		if (metodo.equalsIgnoreCase("Finalização")) {
			vencedor.setFinalizacoes(vencedor.getFinalizacoes() + 1);
		}

		// Atualiza o campeão da categoria, caso o cinturão mude de dono.
		if (perdedor.isCampeao()) {
			perdedor.setCampeao(false);
			vencedor.setCampeao(true);

			System.out.println("Novo campeão: " + vencedor.getNome());
		}

		// Cria o registro da luta e adiciona ao histórico da organização.
		Luta luta = new Luta(vencedor, perdedor, metodo, ROUND_PADRAO);

		// Salva o histórico da luta e atualiza os dados dos lutadores.
		organizacaoLuta.adicionarLuta(luta);
		arquivoService.salvarLuta(luta);
		List<Organizacao> organizacoes = new ArrayList<>();

		organizacoes.add(organizacaoLuta);

		arquivoService.salvarLutadores(organizacoes);

		System.out.println("Luta registrada!");
	}
}
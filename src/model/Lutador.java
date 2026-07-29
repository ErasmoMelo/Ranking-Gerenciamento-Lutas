package model;

/**
 * Representa um lutador do sistema, armazenando suas informações pessoais,
 * estatísticas de luta e organização à qual pertence.
 */
public class Lutador extends Pessoa implements CalculavelRanking {

	private CategoriaPeso categoria;
	private String organizacao;

	private int vitorias;
	private int derrotas;
	private int empates;

	private int nocautes;
	private int finalizacoes;

	private boolean campeao;

	/**
	 * Cria um lutador com todas as informações necessárias.
	 *
	 * @param nome        Nome do lutador.
	 * @param pais        País de origem.
	 * @param categoria   Categoria de peso.
	 * @param organizacao Organização à qual pertence.
	 */
	public Lutador(String nome, String pais, CategoriaPeso categoria, String organizacao) {

		super(nome, pais);

		this.categoria = categoria;
		this.organizacao = organizacao;

		this.vitorias = 0;
		this.derrotas = 0;
		this.empates = 0;
		this.nocautes = 0;
		this.finalizacoes = 0;
		this.campeao = false;
	}

	/**
	 * Cria um lutador utilizando valores padrão para categoria e organização.
	 *
	 * @param nome Nome do lutador.
	 * @param pais País de origem.
	 */
	public Lutador(String nome, String pais) {
		this(nome, pais, CategoriaPeso.LEVE, "Sem Organização");
	}

	// Getters

	public CategoriaPeso getCategoria() {
		return categoria;
	}

	public String getOrganizacao() {
		return organizacao;
	}

	public int getVitorias() {
		return vitorias;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public int getEmpates() {
		return empates;
	}

	public int getNocautes() {
		return nocautes;
	}

	public int getFinalizacoes() {
		return finalizacoes;
	}

	public boolean isCampeao() {
		return campeao;
	}

	// Setters

	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}

	public void setNocautes(int nocautes) {
		this.nocautes = nocautes;
	}

	public void setFinalizacoes(int finalizacoes) {
		this.finalizacoes = finalizacoes;
	}

	public void setCampeao(boolean campeao) {
		this.campeao = campeao;
	}

	/**
	 * Calcula a pontuação do lutador utilizada para geração do ranking.
	 *
	 * @return Pontuação calculada.
	 */
	@Override
	public double calcularPontuacao() {
		return (vitorias * 3.0) + (nocautes * 1.5) + (finalizacoes * 1.2) - (derrotas * 2.0);
	}
}
package model;

/**
 * Classe abstrata que representa uma pessoa do sistema, armazenando informações
 * básicas como nome e país.
 */
public abstract class Pessoa {

	protected String nome;
	protected String pais;

	/**
	 * Inicializa os dados básicos de uma pessoa.
	 *
	 * @param nome Nome da pessoa.
	 * @param pais País de origem.
	 */
	public Pessoa(String nome, String pais) {
		this.nome = nome;
		this.pais = pais;
	}

	public String getNome() {
		return nome;
	}

	public String getPais() {
		return pais;
	}
}
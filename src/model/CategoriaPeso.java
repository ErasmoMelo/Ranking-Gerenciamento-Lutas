package model;

/**
 * Enumeração que representa as categorias de peso utilizadas pelo sistema de
 * ranking de lutadores.
 */
public enum CategoriaPeso {

	MOSCA("Peso Mosca"), GALO("Peso Galo"), PENA("Peso Pena"), LEVE("Peso Leve"), MEIO_MEDIO("Peso Meio-Médio"),
	MEDIO("Peso Médio"), MEIO_PESADO("Peso Meio-Pesado"), PESADO("Peso Pesado");

	private String descricao;

	CategoriaPeso(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Retorna a descrição da categoria de peso.
	 *
	 * @return Descrição da categoria.
	 */
	public String getDescricao() {
		return descricao;
	}
}

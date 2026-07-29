package exception;

/**
 * Exceção personalizada utilizada para representar erros de negócio durante a
 * execução do sistema.
 */
public class SistemaException extends Exception {

	/**
	 * Cria uma exceção com a mensagem informada.
	 *
	 * @param mensagem Descrição do erro ocorrido.
	 */
	public SistemaException(String mensagem) {
		super(mensagem);
	}

}
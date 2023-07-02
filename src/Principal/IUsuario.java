package Principal;

public interface IUsuario {
	public void emprestimoLivro(String codigoLivro) throws Exception;

	public void reservarLivro(String codigoLivro) throws Exception;

	public void devolverLivro(String codigolivro);
	
	public void removerReservaAtual(Livro livro);

	public void adicionarReservaHistorico(Reserva reserva);
	
	public String getNome();
	
	public String getCodigo();
	
	public String exibir();
}

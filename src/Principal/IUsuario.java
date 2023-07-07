package Principal;

import java.util.List;

public interface IUsuario {
	public void emprestimoLivro(String codigoLivro) throws Exception;

	public void reservarLivro(String codigoLivro) throws Exception;

	public void devolverLivro(String codigolivro);
	
	public void removerReservaAtual(Livro livro);

	public void adicionarReservaHistorico(Reserva reserva);

	public List<Emprestimo> getEmprestimosAtuais();

	public List<Emprestimo> getHistoricoEmprestimos();
	
	public List<Reserva> getReservasAtuais();
	
	public List<Reserva> getHistoricoReservas();
	
	public String getCodigo();
	
	public String getNome();
	

}

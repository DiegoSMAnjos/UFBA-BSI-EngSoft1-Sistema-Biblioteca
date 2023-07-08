package Principal;

import java.util.List;

import Fachada.SistemaBiblioteca;

public interface IUsuario {
	public void emprestimoLivro(String codigoLivro, SistemaBiblioteca bib) throws Exception;

	public void reservarLivro(String codigoLivro, SistemaBiblioteca bib) throws Exception;

	public void devolverLivro(String codigolivro);
	
	public void removerReservaAtual(Exemplar exemplar);

	public void adicionarReservaHistorico(Reserva reserva);
	
	public String getCodigo();
	
	public String getNome();
	

}

package model.entities;

import java.util.ArrayList;
import java.util.List;

import facade_singleton.SistemaBiblioteca;
import model.services.Emprestimo;
import model.services.Reserva;
import observer.Observer;
import observer.Subject;

public class Livro {
	private String codigo;
	private String titulo;
	private String editora;
	private String autor;
	private String edicao;
	private String anoPublicacao;

	public Livro(String codigo, String titulo, String editora, String autor, String edicao, String anopublicacao) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.editora = editora;
		this.autor = autor;
		this.edicao = edicao;
		this.anoPublicacao = anopublicacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getEditora() {
		return editora;
	}

	public String getAutor() {
		return autor;
	}

	public String getEdicao() {
		return edicao;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	/*
	 * 
	 * public void reservarItem(IUsuario usuario, Reserva reserva) {
	 * this.reservaAtual = reserva; reserva.getExemplar().setStatus("Reservado"); if
	 * (getLivrosReservadoCodigo(this).size() > LimiteReservasObserver) {
	 * notifyObserver(this); }
	 * 
	 * }
	 */
	/*
	 * 
	 * private List<Livro> getLivrosReservadoCodigo(Livro livro) {
	 * 
	 * return SistemaBiblioteca.getInstance().getListaLivros().stream() .filter(liv
	 * -> liv.getCodigoLivro().equals(this.getCodigoLivro())) .filter(l ->
	 * l.getStatus().equals("Reservado")).toList(); }
	 */
	/*
	 * public void emprestarItem(IUsuario usuario, Emprestimo emprestimo) {
	 * this.status = "Emprestado";
	 * 
	 * if (reservaAtual != null && reservaAtual.getUsuario().equals(usuario)) {
	 * 
	 * if (this.reservaAtual != null) {
	 * usuario.adicionarReservaHistorico(this.reservaAtual); }
	 * 
	 * usuario.removerReservaAtual(this); this.usuarioAlugado = usuario;
	 * this.emprestimoAtual = emprestimo; } else if (reservaAtual == null ||
	 * !reservaAtual.getUsuario().equals(usuario)) {
	 * 
	 * this.usuarioAlugado = usuario; this.emprestimoAtual = emprestimo; } }
	 * 
	 * public boolean identificadorExemplar(Object obj) {
	 * 
	 * if (this == obj) { return true; }
	 * 
	 * if (obj == null || obj.getClass() != this.getClass()) { return false; }
	 * 
	 * Livro livro = (Livro) obj;
	 * 
	 * return ((livro.getCodigoExemplar() == this.codigoExemplar) &&
	 * (livro.getCodigoLivro().equals(this.codigo))); }
	 */
	/*
	 * 
	 * public String exibir() {
	 * 
	 * String reserva = ""; String emprestimo = ""; if (this.reservaAtual != null) {
	 * reserva = ("\nReserva Atual:" + this.reservaAtual.getUsuario().getNome()); }
	 * 
	 * if (this.emprestimoAtual != null) { emprestimo =
	 * ("\nEmprestimo Atual:  / Usuario: " +
	 * this.emprestimoAtual.getUsuario().getNome() + "   / Data Emprestimo: " +
	 * this.emprestimoAtual.getDataEmprestimo() + "   / Data Devolução: " +
	 * this.emprestimoAtual.getDataDevolucaoPrevisao()); }
	 * 
	 * String exibicao =" \nCodigo Exemplar: " + this.codigoExemplar + "\nTitulo: "
	 * + this.titulo + reserva + "\nStatus: " + this.status + emprestimo;
	 * 
	 * return exibicao; }
	 */

}

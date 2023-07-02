package Principal;

import java.util.ArrayList;
import java.util.List;

import Fachada.SistemaBiblioteca;
import Observer.Observer;
import Observer.Subject;

public class Livro implements Subject{
	private String codigo;
	private String codigoExemplar;
	private String titulo;
	private String editora;
	private String autor;
	private String edicao;
	private String anopublicacao;
	private String status;
	private IUsuario usuarioAlugado;
	private Reserva reservaAtual;
	private Emprestimo EmprestimoAtual;
	private static int geradorCodigo = 1;
	private List<Reserva> reservas;
	private List<Observer> observadores;
	private int LimiteReservasObserver = 1;
	
	public Livro(String codigo, String titulo, String editora, String autor, String edicao, String anopublicacao) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.edicao = edicao;
		this.anopublicacao = anopublicacao;
		this.geradorCodigo = ++Livro.geradorCodigo;
		this.codigoExemplar = String.valueOf(geradorCodigo);
		this.status = "Livre";
		this.reservas = new ArrayList<>();
		this.observadores = new ArrayList<>();
	}
	
	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getEdicao() {
		return edicao;
	}

	public String getAnoPublicacao() {
		return anopublicacao;
	}
	
	public IUsuario getUsuarioAlugado(IUsuario usuario) {
		return usuarioAlugado;
	}

	public String getCodigoLivro() {
		return this.codigo;

	}
	
	public String getCodigoExemplar() {
		return codigoExemplar;
	}
	
	public String getStatus() {
		return this.status;
	}

	public Reserva getReservaAtual() {
		return reservaAtual;
	}
	
	public void devolverItem(IUsuario usuario, Livro livro, Emprestimo emprestimo) {
		this.usuarioAlugado = null;
		this.status = "Livre";
		this.EmprestimoAtual = null;

	}
	
	public void reservarItem(IUsuario usuario, Reserva reserva) {
		this.reservaAtual = reserva;
		this.status = "Reservado";
		if (getLivrosReservadoCodigo(this).size() > LimiteReservasObserver) {
			notifyObserver(this);
		}

	}
	
	private List<Livro> getLivrosReservadoCodigo(Livro livro) {

		return SistemaBiblioteca.getInstanciaSistemaBiblioteca().getListaLivros().stream()
				.filter(liv -> liv.getCodigoLivro().equals(this.getCodigoLivro()))
				.filter(l -> l.getStatus().equals("Reservado")).toList();
	}
	
	public void emprestarItem(IUsuario usuario, Emprestimo emprestimo) {
		this.status = "Emprestado";

		if (reservaAtual != null && reservaAtual.getUsuario().equals(usuario)) {

			if (this.reservaAtual != null) {
				usuario.adicionarReservaHistorico(this.reservaAtual);
			}

			usuario.removerReservaAtual(this);
			this.usuarioAlugado = usuario;
			this.EmprestimoAtual = emprestimo;
		} else if (reservaAtual == null || !reservaAtual.getUsuario().equals(usuario)) {

			this.usuarioAlugado = usuario;
			this.EmprestimoAtual = emprestimo;
		}
	}
	
	public boolean identificadorExemplar(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Livro livro = (Livro) obj;

		return ((livro.getCodigoExemplar() == this.codigoExemplar) && (livro.getCodigoLivro().equals(this.codigo)));
	}
	
	
	public String exibir() {
		
		String reserva = "";
		String emprestimo = "";
		if (this.reservaAtual != null) {
			 reserva = ("\nReserva Atual:" + this.reservaAtual.getUsuario().getNome());
		}

		if (this.EmprestimoAtual != null) {
			 emprestimo = ("\nEmprestimo Atual:  / Usuario: " + this.EmprestimoAtual.getUsuario().getNome()
							+ "   / Data Emprestimo: " + this.EmprestimoAtual.getDataEmprestimo()
							+ "   / Data Devolução: " + this.EmprestimoAtual.getDataDevolucaoPrevisao());
		}

		String exibicao =" \nCodigo Exemplar: " + this.codigoExemplar + "\nTitulo: " + this.titulo + reserva + "\nStatus: " + this.status
				+ emprestimo;

		return exibicao;
	}

	@Override
	public void addObserver(Observer observador) {
		this.observadores.add(observador);		
	}

	@Override
	public void removeObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserver(Livro livro) {
		this.observadores.forEach(ob -> ob.update(this));		
	}

}

package model.entities;

import java.time.LocalDate;
import java.util.List;

import facade_singleton.SistemaBiblioteca;
import model.services.Emprestimo;
import model.services.Reserva;
import observer.Observer;

import java.util.ArrayList;

public class Professor implements IUsuario, Observer {
	private String nome;
	private String codigo;

	private int devolucao = 7;
	private int quantidadeNotificacoes = 0;

	public Professor(String nome, String codigo) {
		this.nome = nome;
		this.codigo = codigo;

	}

	public String getNome() {
		return nome;
	}

	public String getCodigo() {
		return codigo;
	}

	@Override
	public void emprestimoLivro(String codigoLivro, SistemaBiblioteca bib) throws Exception {
		

	}

	@Override
	public void reservarLivro(String codigoLivro, SistemaBiblioteca bib) throws Exception {
		if (bib.getReservasAtuais(this).size() >= limiteReservas) {
			throw new Exception("Você não pode realizar mais reservas pois excedeu o limite!");
		}
		if (this.verificaDevedor()) {
			throw new Exception("O usuário está com status devedor!");
		}
		if (this.reservasAtuais.stream().anyMatch(res -> res.getLivro().getCodigoLivro().equals(codigoLivro))) {
			throw new Exception("O usuário já reservou este livro!");
		}

		List<Livro> livrosDisponiveis = getExemplaresDisponiveis(codigoLivro);

		if (livrosDisponiveis.size() > 0) {
			adicionarReserva(livrosDisponiveis.get(0));
			return;
		}

		throw new Exception("O livro não possui exemplares disponíveis");

	}

	@Override
	public void devolverLivro(String codigoLivro) {
		Emprestimo emprestimo = obterEmprestimoAtual(codigoLivro);
		emprestimo.setDataDevolucaoReal(LocalDate.now());
		emprestimos.add(emprestimo);
		emprestimosAtuais.remove(emprestimo);
		emprestimo.getLivro().devolverItem(this, emprestimo.getLivro(), emprestimo);

	}

	@Override
	public void removerReservaAtual(Exemplar exemplar) {
		reservasAtuais.removeIf(reserva -> reserva.getExemplar().equals(exemplar));

	}

	@Override
	public void adicionarReservaHistorico(Reserva reserva) {
		reserva.setIsAtiva(false);
		historicoReservas.add(reserva);

	}

	private void adicionarEmprestimo(Exemplar exemplar) {
		Emprestimo emprestimo = new Emprestimo(this, exemplar, LocalDate.now(), LocalDate.now().plusDays(devolucao));
		exemplar.emprestarItem(this, emprestimo);
		emprestimosAtuais.add(emprestimo);
	}

	private void adicionarReserva(Livro exemplar) {
		Reserva reserva = new Reserva(this, exemplar);
		exemplar.reservarItem(this, reserva);
		this.reservasAtuais.add(reserva);
	}

	private List<Livro> getExemplaresReservados(String codigo) {

		return SistemaBiblioteca.getInstance().getListaLivros().stream().filter(l -> l.getStatus().equals("Reservado"))
				.filter(livro -> livro.getCodigoLivro().equals(codigo)).toList();

	}

	private boolean verificaDevedor() {
		return this.emprestimosAtuais.stream()
				.anyMatch(emprestimo -> emprestimo.getDataDevolucaoPrevisao().isBefore(LocalDate.now()));
	}

	@Override
	public int getQuantidadeNotificacoes() {
		return this.quantidadeNotificacoes;
	}

	@Override
	public void update(Livro livro) {
		System.out.println("Existem mais de dois exemplares do livro: / Titulo: " + livro.getTitulo() + "/ Codigo: "
				+ livro.getCodigoLivro());
		this.quantidadeNotificacoes++;
	}
}

package Principal;

import java.time.LocalDate;
import java.util.List;

import Fachada.SistemaBiblioteca;

import java.util.ArrayList;


public class AlunoPosGrad implements IUsuario{
	private String nome;
	private String codigo;

	private int limiteReservas = 3;
	private int devolucao = 4;
	
	public AlunoPosGrad(String nome, String codigo) {
		this.nome = nome;
		this.codigo = codigo;
		this.historicoReservas = new ArrayList<>();
		this.reservasAtuais = new ArrayList<>();
		this.emprestimos = new ArrayList<>();
		this.emprestimosAtuais = new ArrayList<>();

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
		if (reservasAtuais.size() >= limiteReservas) {
			throw new Exception("Você não pode realizar mais reservas pois excedeu o limite!");
		}
		if (this.verificaDevedor()) {
			throw new Exception("O usuário está com status devedor!");
		}
		if (this.reservasAtuais.stream().anyMatch(res -> res.getLivro().getCodigoLivro().equals(codigoLivro))) {
			throw new Exception("O usuário já reservou este livro!");
		}

		List<Livro> livrosDisponiveis = getLivrosDisponiveis(codigoLivro);

		if (livrosDisponiveis.size() > 0) {
			adicionarReserva(livrosDisponiveis.get(0));
		} else {
			throw new Exception("O livro não possui exemplares disponíveis");
		}

		
		
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
	public void removerReservaAtual(Livro livro) {
		reservasAtuais.removeIf(reserva -> reserva.getLivro().equals(livro));
		
	}
	@Override
	public void adicionarReservaHistorico(Reserva reserva) {
		reserva.setIsAtiva(false);
		historicoReservas.add(reserva);
		
	}
	
	
	private void adicionarEmprestimo(Livro livro) {
		Emprestimo emprestimo = new Emprestimo(this, livro, LocalDate.now(),
				LocalDate.now().plusDays(devolucao));
		livro.emprestarItem(this, emprestimo);
		emprestimosAtuais.add(emprestimo);
	}
	
	private void adicionarReserva(Livro exemplar) {
		Reserva reserva = new Reserva(this, exemplar);
		exemplar.reservarItem(this, reserva);
		this.reservasAtuais.add(reserva);
	}
	
	private Reserva obterReserva(String codigoLivro) {

		List<Reserva> reservas = reservasAtuais.stream()
				.filter(reserva -> reserva.getLivro().getCodigoLivro().equals(codigoLivro)).toList();
		if (reservas.size() > 0) {
			return reservas.get(0);
		}
		return null;
	}




	private boolean verificaDevedor() {
		return this.emprestimosAtuais.stream()
				.anyMatch(emprestimo -> emprestimo.getDataDevolucaoPrevisao().isBefore(LocalDate.now()));
	}
	
	
}

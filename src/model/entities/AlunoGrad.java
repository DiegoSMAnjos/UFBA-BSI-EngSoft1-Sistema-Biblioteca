package Principal;

import java.time.LocalDate;
import java.util.List;
import Fachada.SistemaBiblioteca;
import Strategy.DevolucaoAlunoGradStrategy;
import Strategy.DevolucaoStrategy;
import Strategy.EmprestimoAlunoGradStrategy;
import Strategy.EmprestimoStrategy;
import Strategy.ReservaAlunoGradStrategy;
import Strategy.ReservaStrategy;

import java.util.ArrayList;

public class AlunoGrad implements IUsuario {
	private String nome;

	private String codigo;
	private EmprestimoStrategy emprestimoStrategy;
	private ReservaStrategy reservaStrategy;
	private DevolucaoStrategy devolucaoStrategy;

	public AlunoGrad(String nome, String codigo) {
		this.nome = nome;
		this.codigo = codigo;
		this.emprestimoStrategy = new EmprestimoAlunoGradStrategy();
		this.reservaStrategy = new ReservaAlunoGradStrategy();
		this.devolucaoStrategy = new DevolucaoAlunoGradStrategy();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EmprestimoStrategy getEmprestimoStrategy() {
		return emprestimoStrategy;
	}

	public void setEmprestimoStrategy(EmprestimoStrategy emprestimoStrategy) {
		this.emprestimoStrategy = emprestimoStrategy;
	}

	public ReservaStrategy getReservaStrategy() {
		return reservaStrategy;
	}

	public void setReservaStrategy(ReservaStrategy reservaStrategy) {
		this.reservaStrategy = reservaStrategy;
	}

	public DevolucaoStrategy getDevolucaoStrategy() {
		return devolucaoStrategy;
	}

	public void setDevolucaoStrategy(DevolucaoStrategy devolucaoStrategy) {
		this.devolucaoStrategy = devolucaoStrategy;
	}

	public String getCodigo() {
		return codigo;
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
		Emprestimo emprestimo = new Emprestimo(this, livro, LocalDate.now(), LocalDate.now().plusDays(devolucao));
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

	private boolean verificaDevedor(SistemaBiblioteca bib) {
		return bib.getEmprestimosAtuais(this).stream()
				.anyMatch(emprestimo -> emprestimo.getDataDevolucaoPrevisao().isBefore(LocalDate.now()));
	}

}

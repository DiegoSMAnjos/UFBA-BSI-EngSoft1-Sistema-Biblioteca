package Principal;

import java.time.LocalDate;
import java.util.List;
import Fachada.SistemaBiblioteca;
import java.util.ArrayList;


public class AlunoGraduacao implements IUsuario{
	private String nome;
	private String codigo;
	private int limiteEmprestimos = 3;
	private int limiteReservas = 3;
	private int devolucao = 3;
	private List<Reserva> historicoReservas;
	private List<Reserva> reservasAtuais;
	private List<Emprestimo> emprestimos;
	private List<Emprestimo> emprestimosAtuais;
	
	public AlunoGraduacao(String nome, String codigo) {
		this.nome = nome;
		this.codigo = codigo;
		this.historicoReservas = new ArrayList<>();
		this.reservasAtuais = new ArrayList<>();
		this.emprestimos = new ArrayList<>();
		this.emprestimosAtuais = new ArrayList<>();

	}
	
	@Override
	public void emprestimoLivro(String codigoLivro) throws Exception {
		if (emprestimosAtuais.size() >= limiteEmprestimos) {
			throw new Exception("Você não pode realizar mais emprestimos pois excedeu o limite!");
		}
		if (this.verificaDevedor()) {
			throw new Exception("O usuário está com status devedor!");
		}
		if (this.emprestimosAtuais.stream().anyMatch(emp -> emp.getLivro().getCodigoLivro().equals(codigoLivro))) {
			throw new Exception("O usuário já pegou este livro emprestado!");
		}

		Reserva reserva = obterReserva(codigoLivro);

		if (reserva == null) {

			List<Livro> livrosDisponiveis = getLivrosDisponiveis(codigoLivro);

			if (livrosDisponiveis.size() <= 0) {
				throw new Exception("Esse livro não está disponível!");
			}

			adicionarEmprestimo(livrosDisponiveis.get(0));

		} else {
			adicionarEmprestimo(reserva.getLivro());
		}
		
	}
	@Override
	public void reservarLivro(String codigoLivro) throws Exception {
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
		reserva.setReservaAtiva(false);
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
	
	private Emprestimo obterEmprestimoAtual(String codigoLivro) {

		List<Emprestimo> emprestimos = emprestimosAtuais.stream()
				.filter(emprestimo -> emprestimo.getLivro().getCodigoLivro().equals(codigoLivro)).toList();
		if (emprestimos.size() > 0) {
			return emprestimos.get(0);
		}
		return null;
	}
	
	private List<Livro> getLivrosDisponiveis(String codigo) {

		return SistemaBiblioteca.getInstanciaSistemaBiblioteca().getListaLivros().stream()
				.filter(l -> l.getStatus().equals("Livre"))
				.filter(livro -> livro.getCodigoLivro().equals(codigo)).toList();

	}



	private boolean verificaDevedor() {
		return this.emprestimosAtuais.stream()
				.anyMatch(emprestimo -> emprestimo.getDataDevolucaoPrevisao().isBefore(LocalDate.now()));
	}
	
	
	@Override
	public String getCodigo() {
		return this.codigo;
	}
	
	@Override
	public String getNome() {
		return this.nome;
	}
	
	public List<Emprestimo> getHistoricoEmprestimos() {
		return this.emprestimos;
	}
	
	public List<Reserva> getHistoricoReservas() {
		return this.historicoReservas;
	}
	
	public List<Emprestimo> getEmprestimosAtuais() {
		return this.emprestimosAtuais;
	}
	
	public List<Reserva> getReservasAtuais() {
		return this.reservasAtuais;
	}
	
	
	@Override
	public String exibir() {
		String todosEmprestimos = "";
		String todasReservas = "";
		int i = 0;
		for (i = 0; i < this.emprestimosAtuais.size(); i++) {
			todosEmprestimos += (" \n / Título do Livro: " + this.emprestimosAtuais.get(i).getLivro().getTitulo()
							+ " / Data do Empréstimo: " + this.emprestimosAtuais.get(i).getDataEmprestimo()
							+ " / Situação do Empréstimo: Em curso" + " / Data de Devolução: "
							+ this.emprestimosAtuais.get(i).getDataDevolucaoPrevisao().toString());
		}
		
		for (i = 0; i < this.emprestimos.size(); i++) {
			todosEmprestimos += ("\n / Título do Livro: " + this.emprestimos.get(i).getLivro().getTitulo()
							+ " / Data do Empréstimo: " + this.emprestimos.get(i).getDataEmprestimo()
							+ " / Situação do Empréstimo: Finalizado" + " / Data de Devolução: "
							+ this.emprestimos.get(i).getDataDevolucaoReal().toString());
		}
		for (i = 0; i < this.reservasAtuais.size(); i++) {
			todasReservas += ("\n / Título do Livro: " + this.reservasAtuais.get(i).getLivro().getTitulo()
							+ " / Data de solicitação da Reserva: " + this.reservasAtuais.get(i).getData());
		}
		for (i = 0; i < this.historicoReservas.size(); i++) {
			todasReservas += ("\n / Título do Livro: " + this.historicoReservas.get(i).getLivro().getTitulo()
							+ " / Data de solicitação da Reserva: " + this.historicoReservas.get(i).getData());
		}
		return todosEmprestimos + todasReservas; 
		
	}
}

package pattern.strategy;

import java.time.LocalDate;
import java.util.List;

import model.entities.Livro;
import model.services.Emprestimo;
import model.services.Reserva;
import pattern.facade_singleton.SistemaBiblioteca;

public class EmprestimoAlunoPosGradStrategy implements EmprestimoStrategy{
	private int maxEmprestimosAbertos = 4;
	private int tempoEmprestimo = 4;
	
	@Override
	public void realizarEmprestimo(String codigoLivro, SistemaBiblioteca bib) {
		if (emprestimosAtuais.size() >= maxEmprestimosAbertos) {
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
	
	
	public void adicionarEmprestimo(Livro livro) {
		Emprestimo emprestimo = new Emprestimo(this, livro, LocalDate.now(),
				LocalDate.now().plusDays(devolucao));
		livro.emprestarItem(this, emprestimo);
		emprestimosAtuais.add(emprestimo);
	}
	
}
package Strategy;

import java.util.List;

import Fachada.SistemaBiblioteca;
import Principal.Livro;
import Principal.Reserva;

public class EmprestimoAlunoGradStrategy implements EmprestimoStrategy{
	private int limiteEmprestimos = 3;

	private int devolucao = 3;
	
	@Override
	public void realizarEmprestimo(String codigoLivro, SistemaBiblioteca bib) {
		if (bib.getEmprestimosAtuais(this).size() >= limiteEmprestimos) {
			throw new Exception("Você não pode realizar mais emprestimos pois excedeu o limite!");
		}
		if (this.verificaDevedor(bib)) {
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
	
}

package strategy;

import java.util.List;

import facade_singleton.SistemaBiblioteca;
import model.entities.Exemplar;
import model.entities.Livro;
import model.services.Reserva;

public class EmprestimoProfessorStrategy implements EmprestimoStrategy {

	@Override
	public void realizarEmprestimo(String codigoLivro, SistemaBiblioteca bib) {
		if (this.verificaDevedor()) {
			throw new Exception("O usuário está com status devedor!");
		}
		if (bib.getEmprestimosAtuais(this).stream()
				.anyMatch(emp -> emp.getExemplar().getLivro().getCodigoLivro().equals(codigoLivro))) {
			throw new Exception("O usuário já pegou este livro emprestado!");
		}

		Reserva reserva = obterReserva(codigoLivro);

		if (reserva == null) {

			List<Exemplar> livrosDisponiveis = getExemplaresDisponiveis(codigoLivro);
			if (livrosDisponiveis.size() > 0) {
				adicionarEmprestimo(livrosDisponiveis.get(0));
				return;
			}

			List<Livro> livrosReservados = getExemplaresReservados(codigoLivro);
			if (livrosReservados.size() > 0) {
				adicionarEmprestimo(livrosReservados.get(0));
			}

			throw new Exception("O livro não está disponível!");

		} else {
			adicionarEmprestimo(reserva.getExemplar());
		}
		
	}

}

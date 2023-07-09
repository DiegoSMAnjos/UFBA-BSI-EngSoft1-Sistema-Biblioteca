package pattern.strategy;

import java.time.LocalDate;
import java.util.List;

import model.entities.Exemplar;
import model.entities.Livro;
import model.services.Emprestimo;
import model.services.Reserva;
import pattern.facade_singleton.SistemaBiblioteca;

public class EmprestimoProfessorStrategy implements EmprestimoStrategy {
	// private int maxEmprestimosAbertos = Ilimitado;
	private int tempoEmprestimo = 7;
	
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

	private void adicionarEmprestimo(Exemplar exemplar) {
		Emprestimo emprestimo = new Emprestimo(this, exemplar, LocalDate.now(), LocalDate.now().plusDays(devolucao));
		exemplar.emprestarItem(this, emprestimo);
		emprestimosAtuais.add(emprestimo);
	}
}

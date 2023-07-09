package pattern.strategy;

import java.time.LocalDate;
import java.util.List;

import model.entities.Exemplar;
import model.entities.Livro;
import model.entities.Usuario;
import model.services.Emprestimo;
import model.services.Reserva;
import pattern.facade_singleton.SistemaBiblioteca;

public class EmprestimoProfessorStrategy implements EmprestimoStrategy {
	// private int maxEmprestimosAbertos = Ilimitado;
	private int tempoEmprestimo = 7;
	
	@Override
	public void realizarEmprestimo(String codUsuario, String codigoLivro) {
		SistemaBiblioteca bib = SistemaBiblioteca.getInstance();
		Usuario usuario = bib.getUsuarioByCodigo(codUsuario);
		
		if (bib.verificaDevedor(usuario)) {
			System.out.println("O usuário está com status devedor!");
			return;
		}

		for (Reserva reserva : bib.getReservasAtuais(usuario)) {
			if (reserva.getIsAtiva() == true && reserva.getExemplar().getCodigoLivro().equals(codigoLivro)) {
				reserva.getExemplar().setStatus("Emprestado");
				reserva.setIsAtiva(false);
				bib.getListaEmprestimos().add(new Emprestimo(usuario, reserva.getExemplar(), LocalDate.now(), LocalDate.now().plusDays(tempoEmprestimo)));
				System.out.println("Empréstimo realizado com sucesso!");
				return;
			}
		}
		for (Exemplar exemplar : bib.getExemplaresByCodLivro(codigoLivro)) {
			if (exemplar.getStatus().equals("Disponível")) {
				bib.getListaEmprestimos().add(new Emprestimo(usuario, exemplar, LocalDate.now(), LocalDate.now().plusDays(tempoEmprestimo)));
				System.out.println("Empréstimo realizado com sucesso!");
				return;
			}
		}
		for (Reserva reserva : bib.getListaReservas()) {
			if (reserva.getIsAtiva() == true && reserva.getExemplar().getCodigoLivro().equals(codigoLivro)) {
				reserva.getExemplar().setStatus("Emprestado");
				reserva.setIsAtiva(false);
				bib.getListaEmprestimos().add(new Emprestimo(usuario, reserva.getExemplar(), LocalDate.now(), LocalDate.now().plusDays(tempoEmprestimo)));
				System.out.println("Empréstimo realizado com sucesso!");
				return;
			}
		}
		
		
	}

}

package pattern.strategy;

import java.time.LocalDate;
import model.entities.Exemplar;
import model.entities.Usuario;
import model.services.Emprestimo;
import model.services.Reserva;
import pattern.facade_singleton.SistemaBiblioteca;

public class EmprestimoAlunoStrategy implements EmprestimoStrategy{

	
	@Override
	public void realizarEmprestimo(String codUsuario, String codigoLivro) {
		int maxEmprestimosAbertos = 0;
		int tempoEmprestimo = 0;
		SistemaBiblioteca bib = SistemaBiblioteca.getInstance();
		Usuario usuario = bib.getUsuarioByCodigo(codUsuario);
		if (usuario.getTipoUsuario().equals("Aluno de Graduação")) {
			maxEmprestimosAbertos = 3;
			tempoEmprestimo = 3;
		}
		else if (usuario.getTipoUsuario().equals("Aluno de Pós-graduação")) {
			maxEmprestimosAbertos = 4;
			tempoEmprestimo = 4;
		}
		
		if (bib.getEmprestimosAtuais(usuario).size() >= maxEmprestimosAbertos) {
			System.out.println("Você não pode realizar mais empréstimos pois excedeu o limite!");
			return;
		}
		if (bib.verificaDevedor(usuario)) {
			System.out.println("O usuário está com status devedor!");
			return;
		}
		for (Emprestimo emp : bib.getEmprestimosAtuais(usuario)) {
			if(emp.getExemplar().getCodigoLivro().equals(codigoLivro)) {
				System.out.println("O usuário já pegou este livro emprestado!");
				return;
			}
		}
		for (Reserva reserva : bib.getReservasAtuaisByUsuario(usuario)) {
			if (reserva.getIsAtiva() == true && reserva.getExemplar().getCodigoLivro().equals(codigoLivro)) {
				reserva.getExemplar().setStatus("Emprestado");
				reserva.setIsAtiva(false);
				bib.getLivroByCodigo(reserva.getExemplar().getCodigoLivro()).removeReservasSimultaneas();;
				bib.getListaEmprestimos().add(new Emprestimo(usuario, reserva.getExemplar(), LocalDate.now(), LocalDate.now().plusDays(tempoEmprestimo)));
				System.out.println("Empréstimo realizado com sucesso!");
				System.out.println("Usuário: " + usuario.getNome());
				System.out.println("Livro: " + bib.getLivroByCodigo(codigoLivro).getTitulo());
				return;
			}
		}
		for (Exemplar exemplar : bib.getExemplaresByCodLivro(codigoLivro)) {
			if (exemplar.getStatus().equals("Disponível")) {
				bib.getListaEmprestimos().add(new Emprestimo(usuario, exemplar, LocalDate.now(), LocalDate.now().plusDays(tempoEmprestimo)));
				System.out.println("Empréstimo realizado com sucesso!");
				System.out.println("Usuário: " + usuario.getNome());
				System.out.println("Livro: " + bib.getLivroByCodigo(codigoLivro).getTitulo());
				return;
			}
		}
		
	}
	
}

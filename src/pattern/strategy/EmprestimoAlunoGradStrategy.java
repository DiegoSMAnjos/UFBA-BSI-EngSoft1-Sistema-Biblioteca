package pattern.strategy;

import java.time.LocalDate;
import model.entities.Exemplar;
import model.entities.Usuario;
import model.services.Emprestimo;
import model.services.Reserva;
import pattern.facade_singleton.SistemaBiblioteca;

public class EmprestimoAlunoGradStrategy implements EmprestimoStrategy{
	private int maxEmprestimosAbertos = 3;
	private int tempoEmprestimo = 3;
	
	public int getMaxEmprestimosAbertos() {
		return maxEmprestimosAbertos;
	}

	public int getTempoEmprestimo() {
		return tempoEmprestimo;
	}

	public void setMaxEmprestimosAbertos(int maxEmprestimosAbertos) {
		this.maxEmprestimosAbertos = maxEmprestimosAbertos;
	}

	public void setTempoEmprestimo(int tempoEmprestimo) {
		this.tempoEmprestimo = tempoEmprestimo;
	}

	@Override
	public void realizarEmprestimo(String codUsuario, String codigoLivro) {
		SistemaBiblioteca bib = SistemaBiblioteca.getInstance();
		Usuario usuario = bib.getUsuarioByCodigo(codUsuario);
		
		if (bib.getEmprestimosAtuais(usuario).size() >= getMaxEmprestimosAbertos()) {
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
				bib.getListaEmprestimos().add(new Emprestimo(usuario, reserva.getExemplar(), LocalDate.now(), LocalDate.now().plusDays(getTempoEmprestimo())));
				System.out.println("Empréstimo realizado com sucesso!");
				System.out.println("Usuário: " + usuario.getNome());
				System.out.println("Livro: " + bib.getLivroByCodigo(codigoLivro).getTitulo());
				return;
			}
		}
		for (Exemplar exemplar : bib.getLivroByCodigo(codigoLivro).getListaExemplares()) {
			if (exemplar.getStatus().equals("Disponível")) {
				bib.getListaEmprestimos().add(new Emprestimo(usuario, exemplar, LocalDate.now(), LocalDate.now().plusDays(getTempoEmprestimo())));
				exemplar.setStatus("Emprestado");
				System.out.println("Empréstimo realizado com sucesso!");
				System.out.println("Usuário: " + usuario.getNome());
				System.out.println("Livro: " + bib.getLivroByCodigo(codigoLivro).getTitulo());
				return;
			}
		}
		
	}
	
}

package pattern.strategy;

import java.time.LocalDate;
import java.util.List;

import model.entities.Livro;
import model.entities.Usuario;
import model.services.Emprestimo;
import model.services.Reserva;
import pattern.facade_singleton.SistemaBiblioteca;

public class EmprestimoAlunoGradStrategy implements EmprestimoStrategy{
	private int maxEmprestimosAbertos = 3;
	private int tempoEmprestimo = 3;
	
	@Override
	public void realizarEmprestimo(String codigoLivro, String codUsuario, SistemaBiblioteca bib) {
		Usuario usuario = bib.getUsuarioByCodigo(codUsuario);
		
		if (bib.getEmprestimosAtuais(usuario).size() >= maxEmprestimosAbertos) {
			throw new Exception("Você não pode realizar mais emprestimos pois excedeu o limite!");
			return;
		}
		if (bib.verificaDevedor(usuario)) {
			throw new Exception("O usuário está com status devedor!");
			return;
		}
		if (bib.getEmprestimosAtuais(usuario)) {
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
	
	private void adicionarEmprestimo(Livro livro) {
		Emprestimo emprestimo = new Emprestimo(this, livro, LocalDate.now(), LocalDate.now().plusDays(devolucao));
		livro.emprestarItem(this, emprestimo);
		emprestimosAtuais.add(emprestimo);
	}
	
}

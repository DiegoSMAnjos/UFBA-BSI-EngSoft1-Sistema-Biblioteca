package strategy;

import java.time.LocalDate;

import facade_singleton.SistemaBiblioteca;
import model.services.Emprestimo;

public class DevolucaoAlunoGradStrategy implements DevolucaoStrategy {

	@Override
	public void realizarDevolucao(String codigoLivro, SistemaBiblioteca bib) {

		Emprestimo emprestimo = obterEmprestimoAtual(codigoLivro);
		emprestimo.setDataDevolucaoReal(LocalDate.now());
		emprestimos.add(emprestimo);
		emprestimosAtuais.remove(emprestimo);
		emprestimo.getLivro().devolverItem(this, emprestimo.getLivro(), emprestimo);

	}

}

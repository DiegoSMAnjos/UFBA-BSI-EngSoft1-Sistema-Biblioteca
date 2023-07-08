package Strategy;

import java.time.LocalDate;

import Fachada.SistemaBiblioteca;
import Principal.Emprestimo;

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

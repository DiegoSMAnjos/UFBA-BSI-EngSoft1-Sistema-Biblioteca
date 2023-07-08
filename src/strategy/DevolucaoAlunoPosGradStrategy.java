package strategy;

import java.time.LocalDate;

import model.services.Emprestimo;

public class DevolucaoAlunoPosGradStrategy implements DevolucaoStrategy {
	private int devolucao = 4;
	@Override
	public void realizarDevolucao() {
		Emprestimo emprestimo = obterEmprestimoAtual(codigoLivro);
		emprestimo.setDataDevolucaoReal(LocalDate.now());
		emprestimos.add(emprestimo);
		emprestimosAtuais.remove(emprestimo);
		emprestimo.getLivro().devolverItem(this, emprestimo.getLivro(), emprestimo);
		
	}

}

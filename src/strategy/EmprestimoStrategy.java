package strategy;

import facade_singleton.SistemaBiblioteca;

public interface EmprestimoStrategy {
	public void realizarEmprestimo(String codigoLivro, SistemaBiblioteca bib);
}

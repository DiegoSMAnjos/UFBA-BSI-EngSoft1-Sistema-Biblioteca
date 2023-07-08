package Strategy;

import Fachada.SistemaBiblioteca;

public interface EmprestimoStrategy {
	public void realizarEmprestimo(String codigoLivro, SistemaBiblioteca bib);
}

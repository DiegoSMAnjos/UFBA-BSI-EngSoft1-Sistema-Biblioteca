package Strategy;

import Fachada.SistemaBiblioteca;

public interface DevolucaoStrategy {
	public void realizarDevolucao(String codigoLivro, SistemaBiblioteca bib);
}

package strategy;

import facade_singleton.SistemaBiblioteca;

public interface DevolucaoStrategy {
	public void realizarDevolucao(String codigoLivro, SistemaBiblioteca bib);
}

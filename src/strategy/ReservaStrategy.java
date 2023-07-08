package strategy;

import facade_singleton.SistemaBiblioteca;

public interface ReservaStrategy {
	public void realizarReserva(String codigoLivro, SistemaBiblioteca bib);
}

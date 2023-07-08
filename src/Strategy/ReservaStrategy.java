package Strategy;

import Fachada.SistemaBiblioteca;

public interface ReservaStrategy {
	public void realizarReserva(String codigoLivro, SistemaBiblioteca bib);
}

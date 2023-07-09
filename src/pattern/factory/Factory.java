package pattern.factory;

import model.entities.Exemplar;
import model.entities.Livro;
import pattern.facade_singleton.SistemaBiblioteca;

public class Factory {
	
	
	public Exemplar criaExemplar(){
		Livro livro = SistemaBiblioteca.getInstance().getLivroByCodigo(codigoLivro);

		if (livro == null) {
			throw new IllegalArgumentException("Livro não encontrado na lista.");
		} else {
			livro.getListaExemplares().add(this);
		}
	}
	
}

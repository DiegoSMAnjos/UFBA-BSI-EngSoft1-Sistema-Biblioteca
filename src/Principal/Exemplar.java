package Principal;

import Fachada.SistemaBiblioteca;

public class Exemplar {
	private String codigo;
	private Livro livro;
	private String status;
	
	public Exemplar(String codigoLivro, String codigo) {
		this.codigo = codigo;
	    Livro livro = SistemaBiblioteca.getInstance().getLivroByCodigo(codigoLivro);

	    if (livro != null) {
	        this.livro = livro;
	        this.status = "Disponível";
	    } else {
	        throw new IllegalArgumentException("Livro não encontrado na lista.");
	    }
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

package Principal;

public class Exemplar {
	private String codigo;
	private Livro livro;
	private String status;
	
	public Exemplar(String codigo, Livro livro, String status) {
		super();
		this.codigo = codigo;
		this.livro = livro;
		this.status = status;
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

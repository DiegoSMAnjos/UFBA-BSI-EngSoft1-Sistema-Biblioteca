package model.entities;

public class Exemplar {
	private String codigoLivro;
	private String codigo;
	private String status;

	public Exemplar(String codigoLivro, String codigo, String status) {
		this.codigoLivro = codigoLivro;
		this.codigo = codigo;
		this.status = status;
	}

	public String getCodigoLivro() {
		return codigoLivro;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getStatus() {
		return status;
	}

	public void setCodigoLivro(String codigoLivro) {
		this.codigoLivro = codigoLivro;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

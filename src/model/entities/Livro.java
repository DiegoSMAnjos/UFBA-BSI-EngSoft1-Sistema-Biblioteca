package model.entities;

public class Livro {
	private String codigo;
	private String titulo;
	private String editora;
	private String autor;
	private String edicao;
	private String anoPublicacao;

	public Livro(String codigo, String titulo, String editora, String autor, String edicao, String anopublicacao) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.editora = editora;
		this.autor = autor;
		this.edicao = edicao;
		this.anoPublicacao = anopublicacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getEditora() {
		return editora;
	}

	public String getAutor() {
		return autor;
	}

	public String getEdicao() {
		return edicao;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

}

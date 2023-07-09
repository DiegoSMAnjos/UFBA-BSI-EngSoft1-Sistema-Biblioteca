package model.entities;

import pattern.observer.Observer;
import pattern.strategy.*;

public class Usuario implements Observer {
	private String codigo;
	private String tipoUsuario;
	private String nome;
	private EmprestimoStrategy emprestimoStrategy;
	private int quantidadeNotificacoes = 0;

	public Usuario(String codigo, String tipoUsuario, String nome) {
		super();
		this.codigo = codigo;
		this.tipoUsuario = tipoUsuario;
		this.nome = nome;
		if (tipoUsuario == "Aluno de Graduação") {
			this.emprestimoStrategy = new EmprestimoAlunoGradStrategy();
		} else if (tipoUsuario == "Aluno de Pós-graduação") {
			this.emprestimoStrategy = new EmprestimoAlunoPosGradStrategy();
		} else if (tipoUsuario == "Professor") {
			this.emprestimoStrategy = new EmprestimoProfessorStrategy();
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public String getNome() {
		return nome;
	}

	public EmprestimoStrategy getEmprestimoStrategy() {
		return emprestimoStrategy;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmprestimoStrategy(EmprestimoStrategy emprestimoStrategy) {
		this.emprestimoStrategy = emprestimoStrategy;
	}

	@Override
	public void update(Livro livro) {
		System.out.println("Existem mais de dois exemplares do livro: / Titulo: " + livro.getTitulo() + "/ Codigo: "
				+ livro.getCodigo());
		this.quantidadeNotificacoes++;
	}

	@Override
	public int getQuantidadeNotificacoes() {
		return this.quantidadeNotificacoes;
	}

}

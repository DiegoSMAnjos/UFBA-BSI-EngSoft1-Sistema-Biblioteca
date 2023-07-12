package model.entities;

import pattern.strategy.EmprestimoStrategy;

public abstract class Usuario  {
	protected String codigo;
	protected String tipoUsuario;
	protected String nome;
	protected EmprestimoStrategy emprestimoStrategy;
	protected int maxEmprestimosAbertos;
	private int tempoEmprestimo;


	public Usuario(String codigo, String tipoUsuario, String nome) {
		this.codigo = codigo;
		this.tipoUsuario = tipoUsuario;
		this.nome = nome;
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

	public int getMaxEmprestimosAbertos() {
		return maxEmprestimosAbertos;
	}

	public int getTempoEmprestimo() {
		return tempoEmprestimo;
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

	public void setMaxEmprestimosAbertos(int maxEmprestimosAbertos) {
		this.maxEmprestimosAbertos = maxEmprestimosAbertos;
	}

	public void setTempoEmprestimo(int tempoEmprestimo) {
		this.tempoEmprestimo = tempoEmprestimo;
	}
	
}

package model.entities;

import pattern.strategy.EmprestimoStrategy;

public abstract class Usuario  {
	protected String codigo;
	protected String tipoUsuario;
	protected String nome;
	protected EmprestimoStrategy emprestimoStrategy;



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
	
}

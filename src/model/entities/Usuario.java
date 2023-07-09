package model.entities;

import strategy.*;

public class Usuario {
	private String codigo;
	private String tipoUsuario;
	private String nome;
	private EmprestimoStrategy emprestimoStrategy;
	private ReservaStrategy reservaStrategy;
	private DevolucaoStrategy devolucaoStrategy;
	
	public Usuario(String codigo, String tipoUsuario, String nome) {
		super();
		this.codigo = codigo;
		this.tipoUsuario = tipoUsuario;
		this.nome = nome;
		if (tipoUsuario == "Aluno de Graduação") {
			this.emprestimoStrategy = new EmprestimoAlunoGradStrategy();
			this.reservaStrategy = new ReservaAlunoGradStrategy();
			this.devolucaoStrategy = new DevolucaoAlunoGradStrategy();
		}else if (tipoUsuario == "Aluno de Pós-graduação") {
			this.emprestimoStrategy = new EmprestimoAlunoPosGradStrategy();
			this.reservaStrategy = new ReservaAlunoPosGradStrategy();
			this.devolucaoStrategy = new DevolucaoAlunoPosGradStrategy();
		}else if (tipoUsuario == "Professor") {
			this.emprestimoStrategy = new EmprestimoProfessorStrategy();
			this.reservaStrategy = new ReservaProfessorStrategy();
			this.devolucaoStrategy = new DevolucaoProfessorStrategy();
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

	public ReservaStrategy getReservaStrategy() {
		return reservaStrategy;
	}

	public DevolucaoStrategy getDevolucaoStrategy() {
		return devolucaoStrategy;
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

	public void setReservaStrategy(ReservaStrategy reservaStrategy) {
		this.reservaStrategy = reservaStrategy;
	}

	public void setDevolucaoStrategy(DevolucaoStrategy devolucaoStrategy) {
		this.devolucaoStrategy = devolucaoStrategy;
	}
	
	
}

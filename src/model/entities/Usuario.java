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
	
	
}

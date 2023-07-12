package model.entities;

import pattern.strategy.EmprestimoAlunoGradStrategy;

public class UsuarioAlunoGrad extends Usuario {
	
	public UsuarioAlunoGrad(String codigo, String tipoUsuario, String nome) {
		super(codigo, tipoUsuario, nome);
		this.emprestimoStrategy = new EmprestimoAlunoGradStrategy();
	}

}

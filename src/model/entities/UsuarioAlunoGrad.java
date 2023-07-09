package model.entities;

import pattern.strategy.EmprestimoAlunoStrategy;

public class UsuarioAlunoGrad extends Usuario {
	
	public UsuarioAlunoGrad(String codigo, String tipoUsuario, String nome) {
		super(codigo, tipoUsuario, nome);
		this.emprestimoStrategy = new EmprestimoAlunoStrategy();
	}

}

package model.entities;

import pattern.strategy.EmprestimoAlunoStrategy;

public class UsuarioAlunoPosGrad extends Usuario {
	
	public UsuarioAlunoPosGrad(String codigo, String tipoUsuario, String nome) {
		super(codigo, tipoUsuario, nome);
		this.emprestimoStrategy = new EmprestimoAlunoStrategy();
	}

}

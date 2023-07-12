package model.entities;

import pattern.observer.Observer;
import pattern.strategy.EmprestimoProfessorStrategy;

public class UsuarioProfessor extends Usuario implements Observer{
	
	private int qtdNotificacoes = 0;

	
	public UsuarioProfessor(String codigo, String tipoUsuario, String nome) {
		super(codigo, tipoUsuario, nome);
		this.emprestimoStrategy = new EmprestimoProfessorStrategy();
	}
	
	public int getQtdNotificacoes() {
		return qtdNotificacoes;
	}
	
	public void setQtdNotificacoes(int qtdNotificacoes) {
		this.qtdNotificacoes = qtdNotificacoes;
	}

	@Override
	public void update() {
		qtdNotificacoes++;
	}


}

package model.entities;

import java.time.LocalDate;
import java.util.List;

import facade_singleton.SistemaBiblioteca;
import model.services.Emprestimo;
import strategy.DevolucaoAlunoGradStrategy;
import strategy.DevolucaoStrategy;
import strategy.EmprestimoAlunoGradStrategy;
import strategy.EmprestimoStrategy;
import strategy.ReservaAlunoGradStrategy;
import strategy.ReservaStrategy;

import java.util.ArrayList;

public class AlunoGrad implements IUsuario {
	private String nome;
	private String codigo;
	private EmprestimoStrategy emprestimoStrategy;
	private ReservaStrategy reservaStrategy;
	private DevolucaoStrategy devolucaoStrategy;

	public AlunoGrad(String nome, String codigo) {
		this.nome = nome;
		this.codigo = codigo;
		this.emprestimoStrategy = new EmprestimoAlunoGradStrategy();
		this.reservaStrategy = new ReservaAlunoGradStrategy();
		this.devolucaoStrategy = new DevolucaoAlunoGradStrategy();
	}

	public String getNome() {
		return nome;
	}
	
	public String getCodigo() {
		return codigo;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public EmprestimoStrategy getEmprestimoStrategy() {
		return emprestimoStrategy;
	}

	public void setEmprestimoStrategy(EmprestimoStrategy emprestimoStrategy) {
		this.emprestimoStrategy = emprestimoStrategy;
	}

	public ReservaStrategy getReservaStrategy() {
		return reservaStrategy;
	}

	public void setReservaStrategy(ReservaStrategy reservaStrategy) {
		this.reservaStrategy = reservaStrategy;
	}

	public DevolucaoStrategy getDevolucaoStrategy() {
		return devolucaoStrategy;
	}

	public void setDevolucaoStrategy(DevolucaoStrategy devolucaoStrategy) {
		this.devolucaoStrategy = devolucaoStrategy;
	}














}

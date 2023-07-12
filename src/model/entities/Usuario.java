package model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.services.Emprestimo;
import model.services.Reserva;
import pattern.strategy.EmprestimoStrategy;

public abstract class Usuario {
	protected String codigo;
	protected String tipoUsuario;
	protected String nome;
	protected List<Emprestimo> listaEmprestimos;
	protected List<Reserva> listaReservas;
	protected EmprestimoStrategy emprestimoStrategy;

	public Usuario(String codigo, String tipoUsuario, String nome) {
		this.codigo = codigo;
		this.tipoUsuario = tipoUsuario;
		this.nome = nome;
		this.listaEmprestimos = new ArrayList<>();
		this.listaReservas = new ArrayList<>();
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

	public List<Emprestimo> getListaEmprestimos() {
		return listaEmprestimos;
	}

	public List<Reserva> getListaReservas() {
		return listaReservas;
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

	public void setListaEmprestimos(List<Emprestimo> listaEmprestimos) {
		this.listaEmprestimos = listaEmprestimos;
	}

	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public void setEmprestimoStrategy(EmprestimoStrategy emprestimoStrategy) {
		this.emprestimoStrategy = emprestimoStrategy;
	}
	
	public boolean verificaDevedor() {
		return getListaEmprestimos().stream().filter(emprestimo -> emprestimo.getIsAtivo() == true)
				.anyMatch(emprestimo -> emprestimo.getDataDevolucaoPrevisao().isBefore(LocalDate.now()));
	}

}

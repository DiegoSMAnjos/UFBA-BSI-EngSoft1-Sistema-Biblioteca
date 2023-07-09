package model.services;

import java.time.LocalDate;

import model.entities.Exemplar;
import model.entities.Usuario;

public class Emprestimo {

	private Usuario usuario;
	private Exemplar exemplar;
	private boolean isAtivo;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucaoPrevisao;
	private LocalDate dataDevolucaoReal;

	public Emprestimo(Usuario usuario, Exemplar exemplar, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevisao) {
		this.usuario = usuario;
		this.exemplar = exemplar;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucaoPrevisao = dataDevolucaoPrevisao;
		this.isAtivo = true;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Exemplar getExemplar() {
		return exemplar;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public LocalDate getDataDevolucaoPrevisao() {
		return dataDevolucaoPrevisao;
	}

	public LocalDate getDataDevolucaoReal() {
		return dataDevolucaoReal;
	}

	public boolean getIsAtivo() {
		return isAtivo;
	}
	

	public void setIsAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public void setDataDevolucaoReal(LocalDate data) {
		this.dataDevolucaoReal = data;
	}
}

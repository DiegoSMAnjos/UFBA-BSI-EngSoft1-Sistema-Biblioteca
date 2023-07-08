package model.services;

import java.time.LocalDate;

import model.entities.Exemplar;
import model.entities.IUsuario;

public class Reserva {
	private IUsuario usuario;
	private Exemplar exemplar;
	private LocalDate data;
	private boolean isAtiva;

	public Reserva(IUsuario usuario, Exemplar exemplar) {
		this.usuario = usuario;
		this.exemplar = exemplar;
		this.data = LocalDate.now();
		this.isAtiva = true;
	}

	public IUsuario getUsuario() {
		return usuario;
	}

	public LocalDate getData() {
		return data;
	}

	public boolean getIsAtiva() {
		return isAtiva;
	}

	public void setIsAtiva(boolean value) {
		this.isAtiva = value;
	}

	public Exemplar getExemplar() {
		return exemplar;
	}
}

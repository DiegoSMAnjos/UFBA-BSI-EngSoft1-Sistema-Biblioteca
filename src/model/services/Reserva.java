package model.services;

import java.time.LocalDate;

import model.entities.Exemplar;
import model.entities.Usuario;

public class Reserva {
	private Usuario usuario;
	private Exemplar exemplar;
	private LocalDate data;
	private boolean isAtiva;

	public Reserva(Usuario usuario, Exemplar exemplar) {
		this.usuario = usuario;
		this.exemplar = exemplar;
		this.data = LocalDate.now();
		exemplar.setStatus("Reservado");
		this.isAtiva = true;
	}

	public Usuario getUsuario() {
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

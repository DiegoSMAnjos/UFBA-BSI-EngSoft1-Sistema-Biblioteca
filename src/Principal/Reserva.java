package Principal;

import java.time.LocalDate;

public class Reserva {
	private IUsuario usuario;
	private Livro livro;
	private LocalDate data;
	private boolean isAtiva;

	public Reserva(IUsuario usuario, Livro livro) {
		this.usuario = usuario;
		this.livro = livro;
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

	public Livro getLivro() {
		return livro;
	}
}

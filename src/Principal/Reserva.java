package Principal;
import java.time.LocalDate;

public class Reserva {
	private IUsuario usuario; 
	private Livro livro;
	private LocalDate data; 
	private boolean reservaAtiva; 
	public Reserva(IUsuario usuario,Livro livro) {
			this.usuario = usuario;
			this.livro = livro;
			this.data = LocalDate.now();
			this.reservaAtiva = true;
	}
	
	public IUsuario getUsuario() {
		return usuario; 
	}
	
	public LocalDate getData() {
		return data; 
	}
	public boolean getReservaAtiva() {
		return reservaAtiva; 
	}
	

	
	public void setReservaAtiva(boolean value) {
		this.reservaAtiva = value;
	}
	
	public Livro getLivro() {
		return livro;
	}
}

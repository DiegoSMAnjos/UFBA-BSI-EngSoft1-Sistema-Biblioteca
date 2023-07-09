package strategy;

import java.util.List;

import facade_singleton.SistemaBiblioteca;
import model.entities.Livro;
import model.services.Reserva;
import observer.Observer;

public class ReservaProfessorStrategy implements ReservaStrategy, Observer {
	private int limiteReservas = 3;
	private int quantidadeNotificacoes = 0;
	@Override
	public void realizarReserva(String codigoLivro, SistemaBiblioteca bib) {
		if (bib.getReservasAtuais(this).size() >= limiteReservas) {
			throw new Exception("Você não pode realizar mais reservas pois excedeu o limite!");
		}
		if (this.verificaDevedor()) {
			throw new Exception("O usuário está com status devedor!");
		}
		if (this.reservasAtuais.stream().anyMatch(res -> res.getLivro().getCodigoLivro().equals(codigoLivro))) {
			throw new Exception("O usuário já reservou este livro!");
		}

		List<Livro> livrosDisponiveis = getExemplaresDisponiveis(codigoLivro);

		if (livrosDisponiveis.size() > 0) {
			adicionarReserva(livrosDisponiveis.get(0));
			return;
		}

		throw new Exception("O livro não possui exemplares disponíveis");

	}
	private void adicionarReserva(Livro exemplar) {
		Reserva reserva = new Reserva(this, exemplar);
		exemplar.reservarItem(this, reserva);
		this.reservasAtuais.add(reserva);
	}

	public int getQuantidadeNotificacoes() {
		return this.quantidadeNotificacoes;
	}


	@Override
	public void update(Livro livro) {
		System.out.println("Existem mais de dois exemplares do livro: / Titulo: " + livro.getTitulo() + "/ Codigo: "
				+ livro.getCodigoLivro());
		this.quantidadeNotificacoes++;
	}

}

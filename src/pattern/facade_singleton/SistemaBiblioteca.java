package pattern.facade_singleton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.entities.Exemplar;
import model.entities.Livro;
import model.entities.Usuario;
import model.services.Emprestimo;
import model.services.Reserva;
import pattern.command.*;
import pattern.observer.Observer;

public class SistemaBiblioteca {
	public static SistemaBiblioteca instanciaBiblioteca;
	private List<Usuario> listaUsuarios;
	private List<Livro> listaLivros;

	private Map<String, Command> commands;

	private SistemaBiblioteca() {
		this.listaUsuarios = new ArrayList<Usuario>();
		this.listaLivros = new ArrayList<Livro>();
		this.commands = new HashMap<>();
		this.getCommands().put("dev", new CommandRealizarDevolucao());
		this.getCommands().put("res", new CommandRealizarReserva());
		this.getCommands().put("liv", new CommandConsultarLivro());
		this.getCommands().put("emp", new CommandRealizarEmprestimo());
		this.getCommands().put("usu", new CommandConsultarUsuario());
		this.getCommands().put("obs", new CommandAdicionarObservador());
		this.getCommands().put("ntf", new CommandConsultarNotificacoes());
		this.getCommands().put("sai", new CommandSair());
	}

	public static SistemaBiblioteca getInstance() {
		if (instanciaBiblioteca == null) {
			instanciaBiblioteca = new SistemaBiblioteca();
		}
		return instanciaBiblioteca;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public List<Livro> getListaLivros() {
		return listaLivros;
	}

	public Map<String, Command> getCommands() {
		return commands;
	}

	public void run(String[] args) throws Exception {
		this.getCommands().get(args[0]).execute(args);
	}

	public void commandAdicionarObservador(String codigoUsuario, String codigoLivro) {
		if (getUsuarioByCodigo(codigoUsuario).getTipoUsuario().equals("Professor")) {
			getLivroByCodigo(codigoLivro).getObservadores().add((Observer) getUsuarioByCodigo(codigoUsuario));
			System.out.println("Observador registrado com sucesso!");
		} else {
			System.out.println("Insira um usuário válido!");
		}

	}

	public void commandConsultarLivro(String codigoLivro) {
		List<Exemplar> exemplares = getLivroByCodigo(codigoLivro).getListaExemplares();
		if (exemplares == null) {
			System.out.println("Não foi possível encontrar o livro!");
			return;
		} else {
			System.out.println("Título: " + getLivroByCodigo(codigoLivro).getTitulo());
			System.out.println("Número de Reservas Atuais: " + getLivroByCodigo(codigoLivro).getReservasSimultaneas());
			for (Exemplar exemplar : exemplares) {
				System.out.println("-------------------");
				System.out.println("Exemplar: " + exemplar.getCodigo());
				System.out.println("Status: " + exemplar.getStatus());
				if (exemplar.getStatus().equals("Reservado")) {
					Reserva reserva = getReservaAtivaByExemplar(exemplar.getCodigo());
					System.out.println("Usuário: " + reserva.getUsuario().getNome());
				} else if (exemplar.getStatus().equals("Emprestado")) {
					Emprestimo emprestimo = getEmprestimoAtivoByExemplar(exemplar.getCodigo());
					System.out.println("Usuário: " + emprestimo.getUsuario().getNome());
					System.out.println("Data do Empréstimo: " + emprestimo.getDataEmprestimo());
					System.out.println("Data de Devolução Prevista: " + emprestimo.getDataDevolucaoPrevisao());
				}
			}

		}
	}

	public void commandConsultarNotificacoes(String codigoUsuario) {

		if (getUsuarioByCodigo(codigoUsuario).getTipoUsuario().equals("Professor")) {
			Observer observer = (Observer) getUsuarioByCodigo(codigoUsuario);
			System.out.println("Quantidade de Notificações: " + observer.getQtdNotificacoes());
		} else {
			System.out.println("Insira um observador válido!");
		}
	}

	public void commandConsultarUsuario(String codigoUsuario) {
		Usuario usuario = getUsuarioByCodigo(codigoUsuario);
		if (usuario.equals(null)) {
			System.out.println("Não foi possível encontrar o usuário!");
			return;
		} else {
			System.out.println("Usuário: " + usuario.getNome());
			System.out.println("Empréstimos: ");
			for (Emprestimo emprestimo : usuario.getListaEmprestimos()) {
				System.out.println("-------------------");
				System.out.println("Livro: " + getLivroByCodigo(emprestimo.getExemplar().getCodigoLivro()).getTitulo());
				System.out.println("Data do Empréstimo: " + emprestimo.getDataEmprestimo());
				if (emprestimo.getIsAtivo() == true) {
					System.out.println("Status: em curso");
					System.out.println("Data Prevista de Devolução: " + emprestimo.getDataDevolucaoPrevisao());
				} else {
					System.out.println("Status: finalizado");
					System.out.println("Data de Devolução: " + emprestimo.getDataDevolucaoReal());
				}

			}
			System.out.println("-------------------");
			System.out.println("Reservas: ");
			for (Reserva reserva : usuario.getListaReservas()) {
				System.out.println("-------------------");
				System.out.println("Livro: " + getLivroByCodigo(reserva.getExemplar().getCodigoLivro()).getTitulo());
				System.out.println("Data da Reserva: " + reserva.getData());
			}
		}
	}

	public void commandRealizarDevolucao(String codigoUsuario, String codigoLivro) {
		for (Emprestimo emp : getEmprestimosAtuaisByUsuario(getUsuarioByCodigo(codigoUsuario))) {
			if (emp.getExemplar().getCodigoLivro().equals(codigoLivro)) {
				emp.setDataDevolucaoReal(LocalDate.now());
				emp.setIsAtivo(false);
				emp.getExemplar().setStatus("Disponível");
				System.out.println("Devolução realizada com sucesso!");
				System.out.println("Usuário: " + getUsuarioByCodigo(codigoUsuario).getNome());
				System.out.println("Livro: " + getLivroByCodigo(codigoLivro).getTitulo());
				return;
			}
		}
		System.out.println("O usuário não possui empréstimos em aberto para esse livro");
		return;
	}

	public void commandRealizarEmprestimo(String codigoUsuario, String codigoLivro) {
		// getUsuarioByID, getLivroById; 
		
		getUsuarioByCodigo(codigoUsuario).getEmprestimoStrategy().realizarEmprestimo(codigoUsuario, codigoLivro);
	}

	public void commandRealizarReserva(String codigoUsuario, String codigoLivro) {
		int limiteReservas = 3;
		Usuario usuario = getUsuarioByCodigo(codigoUsuario);

		if (getReservasAtuaisByUsuario(usuario).size() >= limiteReservas) {
			System.out.println("O usuário chegou no limite de reservas permitido!");
			System.out.println("Reserva não realizada.");
			return;
		}
		for (Reserva reserva : getReservasAtuaisByUsuario(usuario)) {
			if (reserva.getExemplar().getCodigoLivro().equals(codigoLivro)) {
				System.out.println("O usuário já possui reserva ativa para esse livro.");
				System.out.println("Reserva não realizada.");
				return;
			}
		}
		Exemplar exemplar = getLivroByCodigo(codigoLivro).getListaExemplares().stream()
				.filter(x -> x.getStatus().equals("Disponível")).findFirst().orElse(null);
		if (exemplar != null) {
			usuario.getListaReservas().add(new Reserva(usuario, exemplar));
			System.out.println("Reserva realizada!");
			System.out.println("Usuário: " + usuario.getNome());
			System.out.println("Livro: " + getLivroByCodigo(codigoLivro).getTitulo());
			getLivroByCodigo(codigoLivro).addReservasSimultaneas();
			getLivroByCodigo(codigoLivro).verificarReservasSimultaneas();
			return;
		} else {
			System.out.println("Não há exemplares disponíveis.");
			System.out.println("Reserva não realizada.");
		}

	}

	public void commandSair() {
		System.out.println("Saindo da aplicação...");
		System.exit(0);
	}

	public Usuario getUsuarioByCodigo(String codigoUsuario) {
		return getListaUsuarios().stream().filter(usuario -> usuario.getCodigo().equals(codigoUsuario)).findFirst()
				.orElse(null);
	}

	public Livro getLivroByCodigo(String codigoLivro) {
		return getListaLivros().stream().filter(livro -> livro.getCodigo().equals(codigoLivro)).findFirst()
				.orElse(null);
	}

	public List<Exemplar> getExemplaresByStatus(String codLivro, String status) {
		return getLivroByCodigo(codLivro).getListaExemplares().stream()
				.filter(exemplar -> exemplar.getStatus().equals(status)).toList();
	}

	public List<Emprestimo> getEmprestimosAtuaisByUsuario(Usuario usuario) {
		return usuario.getListaEmprestimos().stream().filter(emp -> emp.getIsAtivo()).toList();
	}

	public List<Reserva> getReservasAtuaisByUsuario(Usuario usuario) {
		return usuario.getListaReservas().stream().filter(res -> res.getIsAtiva()).toList();
	}

	public List<Reserva> getReservasAtuaisByLivro(String codigoLivro) {
		return getListaUsuarios().stream().flatMap(usuario -> usuario.getListaReservas().stream())
				.filter(res -> res.getExemplar().getCodigoLivro().equals(codigoLivro) && res.getIsAtiva()).toList();
	}

	public Reserva getReservaAtivaByExemplar(String codigoExemplar) {
		return getListaUsuarios().stream().flatMap(usuario -> usuario.getListaReservas().stream())
				.filter(reserva -> reserva.getIsAtiva() && reserva.getExemplar().getCodigo().equals(codigoExemplar))
				.findFirst().orElse(null);
	}

	public Emprestimo getEmprestimoAtivoByExemplar(String codigoExemplar) {
		return getListaUsuarios().stream().flatMap(usuario -> usuario.getListaEmprestimos().stream()).filter(
				emprestimo -> emprestimo.getIsAtivo() && emprestimo.getExemplar().getCodigo().equals(codigoExemplar))
				.findFirst().orElse(null);
	}

	public List<Exemplar> getExemplaresDisponiveis(String codigoLivro) {
		return getLivroByCodigo(codigoLivro).getListaExemplares().stream()
				.filter(exemplar -> exemplar.getStatus().equals("Disponível")).toList();
	}

}
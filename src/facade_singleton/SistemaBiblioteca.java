package facade_singleton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import command.Command;
import command.CommandAdicionarObservador;
import command.CommandConsultarLivro;
import command.CommandConsultarNotificacoes;
import command.CommandConsultarUsuario;
import command.CommandRealizarDevolucao;
import command.CommandRealizarEmprestimo;
import command.CommandRealizarReserva;
import command.CommandSair;
import model.entities.Exemplar;
import model.entities.IUsuario;
import model.entities.Livro;
import model.entities.Professor;
import model.entities.Usuario;
import model.services.Emprestimo;
import model.services.Reserva;
import observer.Observer;
import observer.Subject;

public class SistemaBiblioteca {
	private static SistemaBiblioteca instanciaBiblioteca;
	private List<Usuario> listaUsuarios;
	private List<Livro> listaLivros;
	private List<Exemplar> listaExemplares;
	private List<Reserva> listaReservas;
	private List<Emprestimo> listaEmprestimos;

	private Map<String, Command> commands;

	private SistemaBiblioteca() {
		this.listaUsuarios = new ArrayList<Usuario>();
		this.listaLivros = new ArrayList<Livro>();
		this.listaExemplares = new ArrayList<Exemplar>();
		this.listaReservas = new ArrayList<Reserva>();
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
		return this.listaUsuarios;
	}

	public List<Livro> getListaLivros() {
		return this.listaLivros;
	}

	public List<Exemplar> getListaExemplares() {
		return listaExemplares;
	}

	public List<Reserva> getListaReservas() {
		return listaReservas;
	}

	public List<Emprestimo> getListaEmprestimos() {
		return listaEmprestimos;
	}

	public Map<String, Command> getCommands() {
		return commands;
	}

	public void run(String[] args) throws Exception {
		this.getCommands().get(args[0]).execute(args);
	}

	public void commandAdicionarObservador(String codigoUsuario, String codigoLivro) {

		Observer observador = (Observer) getUsuarioByCodigo(codigoUsuario);

		this.listaLivros.forEach(liv -> {
			Subject livro = (Subject) liv;
			livro.addObserver(observador);
		});
		System.out.println("Observador Registrado com Sucesso!");
	}

	public void commandConsultarLivro(String codigoLivro) {
		try {
			List<Livro> livros = this.getListaLivros().stream()
					.filter(livro -> livro.getCodigoLivro().equals(codigoLivro)).toList();
			if (livros.size() <= 0) {
				throw new Exception("O Livro não existe!");
			} else {
				livros.forEach(livro -> System.out.println(livro.exibir()));
				System.out.println("\n Quantidade de Reservas: "
						+ livros.stream().filter(liv -> liv.getStatus().equals("Reservado")).toList().size());
			}

		} catch (Exception e) {
			System.out.println("Não foi possível encontrar o livro! " + e.getMessage());
		}
	}

	public void commandConsultarNotificacoes(String codigoUsuario) {
		Observer observador = (Observer) getUsuarioByCodigo(codigoUsuario);
		System.out.printf("Quantidade de Notificações: %d", observador.getQuantidadeNotificacoes());
	}

	public void commandConsultarUsuario(String codigoUsuario) {
		try {
			System.out.println(SistemaBiblioteca.getInstance().exibirInfoUsuario(getUsuarioByCodigo(codigoUsuario)));
		} catch (Exception e) {
			System.out.println("Não foi possível encontrar o usuário! " + e.getMessage());
		}

	}

	public void commandRealizarDevolucao(String codigoUsuario, String codigoLivro) {
		try {
			getUsuarioByCodigo(codigoUsuario).devolverLivro(codigoLivro);
			System.out.println("Livro devolvido com sucesso!");
		} catch (Exception e) {
			System.out.println("Não foi possível devolver o livro! " + e.getMessage());
		}
	}

	public void commandRealizarEmprestimo(String codigoUsuario, String codigoLivro) {
		try {
			getUsuarioByCodigo(codigoUsuario).emprestimoLivro(codigoLivro, this);
			System.out.println("Emprestimo Realizado!");
		} catch (Exception e) {
			System.out.println("Não foi possível realizar o seu emprestimo! " + e.getMessage());
		}
	}

	public void commandRealizarReserva(String codigoUsuario, String codigoLivro) {
		try {
			getUsuarioByCodigo(codigoUsuario).reservarLivro(codigoLivro, this);
			System.out.println("Reserva Realizada!");
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a sua reserva! " + e.getMessage());
		}
	}

	public void commandSair() {
		System.out.println("Saindo da aplicação...");
		System.exit(0);
	}

	public Usuario getUsuarioByCodigo(String codigoUsuario) {
		return this.listaUsuarios.stream().filter(user -> user.getCodigo().equals(codigoUsuario)).toList().get(0);
	}
	
	public Livro getLivroByCodigo(String codigoLivro) {

		for (Livro livro : listaLivros)
			if (livro.getCodigoLivro().equals(codigoLivro)) {
				return livro;
			}
		return null;
	}
	
	public List<Exemplar> getExemplaresByCodLivro(String codLivro) {
		List<Exemplar> listaExemplaresLivro = new ArrayList<>();
		for (Exemplar exemplar : listaExemplares) {
			if (exemplar.getLivro().getCodigoLivro().equals(codLivro)) {
				listaExemplaresLivro.add(exemplar);
			}
		}
		return listaExemplaresLivro;

	}

	public String exibirInfoUsuario(Usuario usuario) {
		String todosEmprestimos = "";
		String todasReservas = "";
		int i = 0;
		for (i = 0; i < this.getEmprestimosAtuais().size(); i++) {
			todosEmprestimos += (" \n / Título do Livro: "
					+ usuario.getEmprestimosAtuais().get(i).getExemplar().getLivro().getTitulo()
					+ " / Data do Empréstimo: " + usuario.getEmprestimosAtuais().get(i).getDataEmprestimo()
					+ " / Situação do Empréstimo: Em curso" + " / Data de Devolução: "
					+ usuario.getEmprestimosAtuais().get(i).getDataDevolucaoPrevisao().toString());
		}

		for (i = 0; i < usuario.getHistoricoEmprestimos().size(); i++) {
			todosEmprestimos += ("\n / Título do Livro: "
					+ usuario.getHistoricoEmprestimos().get(i).getExemplar().getLivro().getTitulo()
					+ " / Data do Empréstimo: " + usuario.getHistoricoEmprestimos().get(i).getDataEmprestimo()
					+ " / Situação do Empréstimo: Finalizado" + " / Data de Devolução: "
					+ usuario.getHistoricoEmprestimos().get(i).getDataDevolucaoReal().toString());
		}
		for (i = 0; i < usuario.getReservasAtuais().size(); i++) {
			todasReservas += ("\n / Título do Livro: "
					+ usuario.getReservasAtuais().get(i).getExemplar().getLivro().getTitulo()
					+ " / Data de solicitação da Reserva: " + usuario.getReservasAtuais().get(i).getData());
		}
		for (i = 0; i < usuario.getHistoricoReservas().size(); i++) {
			todasReservas += ("\n / Título do Livro: "
					+ usuario.getHistoricoReservas().get(i).getExemplar().getLivro().getTitulo()
					+ " / Data de solicitação da Reserva: " + usuario.getHistoricoReservas().get(i).getData());
		}
		return todosEmprestimos + todasReservas;

	}



	public List<Emprestimo> getEmprestimosAtuais(Usuario usuario) {
		List<Emprestimo> emprestimosAtuais = new ArrayList<>();
		for (Emprestimo emp : listaEmprestimos) {
			if (emp.getUsuario().getCodigo().equals(usuario.getCodigo()) && emp.getEmprestimoAtivo() == true) {
				emprestimosAtuais.add(emp);
			}
		}
		return emprestimosAtuais;

	}

	public List<Reserva> getReservasAtuais(IUsuario usuario) {
		List<Reserva> reservasAtuais = new ArrayList<>();
		for (Reserva res : listaReservas) {
			if (res.getUsuario().getCodigo().equals(usuario.getCodigo()) && res.getIsAtiva() == true) {
				reservasAtuais.add(res);
			}
		}
		return reservasAtuais;
	}



	public List<Exemplar> getExemplaresDisponiveis(String codigoLivro) {

		List<Exemplar> exemplares = this.getExemplaresByCodLivro(codigoLivro);
		List<Exemplar> exemplaresDisponiveis = new ArrayList<>();
		for (Exemplar exemplar : exemplares) {
			if (exemplar.getStatus().equals("Disponível")) {
				exemplaresDisponiveis.add(exemplar);
			}
		}

		return exemplaresDisponiveis;

	}

	public boolean verificaDevedor(IUsuario usuario) {

		return this.getEmprestimosAtuais(usuario).stream()
				.anyMatch(emprestimo -> emprestimo.getDataDevolucaoPrevisao().isBefore(LocalDate.now()));
	}

}

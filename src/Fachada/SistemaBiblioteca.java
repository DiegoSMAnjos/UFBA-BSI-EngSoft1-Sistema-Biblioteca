package Fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Command.Command;
import Command.CommandAdicionarNovoObservador;
import Command.CommandConsultarLivro;
import Command.CommandConsultarNotificacoes;
import Command.CommandConsultarUsuario;
import Command.CommandRealizarDevolucao;
import Command.CommandRealizarEmprestimo;
import Command.CommandRealizarReserva;
import Command.CommandSair;
import Observer.Observer;
import Observer.Subject;
import Principal.Emprestimo;
import Principal.Exemplar;
import Principal.IUsuario;
import Principal.Livro;
import Principal.Reserva;

public class SistemaBiblioteca {
	private static SistemaBiblioteca instanciaBiblioteca;
	private List<IUsuario> listaUsuarios;
	private List<Livro> listaLivros;
	private List<Exemplar> listaExemplares;
	private List<Reserva> listaReservas;
	private List<Emprestimo> listaEmprestimos;

	private Map<String, Command> commands;


	private SistemaBiblioteca() {
		this.listaUsuarios = new ArrayList<IUsuario>();
		this.listaLivros = new ArrayList<Livro>();
		this.listaExemplares = new ArrayList<Exemplar>();
		this.listaReservas = new ArrayList<Reserva>();
		this.commands = new HashMap<>();
		this.getCommands().put("dev", new CommandRealizarDevolucao());
		this.getCommands().put("res", new CommandRealizarReserva());
		this.getCommands().put("liv", new CommandConsultarLivro());
		this.getCommands().put("emp", new CommandRealizarEmprestimo());
		this.getCommands().put("usu", new CommandConsultarUsuario());
		this.getCommands().put("obs", new CommandAdicionarNovoObservador());
		this.getCommands().put("ntf", new CommandConsultarNotificacoes());
		this.getCommands().put("sai", new CommandSair());
	}

	public static SistemaBiblioteca getInstanciaSistemaBiblioteca() {
		if (instanciaBiblioteca == null) {
			instanciaBiblioteca = new SistemaBiblioteca();
		}
		return instanciaBiblioteca;
	}

	public List<IUsuario> getListaUsuarios() {
		return this.listaUsuarios;
	}

	public List<Livro> getListaLivros() {
		return this.listaLivros;
	}

	public List<Exemplar> getExemplares() {
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

	public void realizarEmprestimoLivro(String codigoUsuario, String codigoLivro) {
		try {
			obterUsuario(codigoUsuario).emprestimoLivro(codigoLivro);
			System.out.println("Emprestimo Realizado!");
		} catch (Exception e) {
			System.out.println("Não foi possível realizar o seu emprestimo! " + e.getMessage());
		}
	}

	public void realizarReservaLivro(String codigoUsuario, String codigoLivro) {
		try {
			obterUsuario(codigoUsuario).reservarLivro(codigoLivro);
			System.out.println("Reserva Realizada!");
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a sua reserva! " + e.getMessage());
		}
	}

	public void realizarDevolucaoLivro(String codigoUsuario, String codigoLivro) {
		try {
			obterUsuario(codigoUsuario).devolverLivro(codigoLivro);
			System.out.println("Livro devolvido com sucesso!");
		} catch (Exception e) {
			System.out.println("Não foi possível devolver o livro! " + e.getMessage());
		}
	}

	private IUsuario obterUsuario(String codigoUsuario) {
		return this.listaUsuarios.stream().filter(user -> user.getCodigo().equals(codigoUsuario)).toList().get(0);
	}

	public void consultarNotificacoes(String codigoUsuario) {
		Observer observador = (Observer) obterUsuario(codigoUsuario);
		System.out.printf("Quantidade de Notificações: %d", observador.getQuantidadeNotificacoes());
	}

	public void adicionarObservador(String codigoUsuario, String codigoLivro) {

		Observer observador = (Observer) obterUsuario(codigoUsuario);

		this.listaLivros.forEach(liv -> {
			Subject livro = (Subject) liv;
			livro.addObserver(observador);
		});
		System.out.println("Observador Registrado com Sucesso!");
	}

	public void consultarUsuarioPeloCodigo(String codigoUsuario) {
		try {
			System.out.println(SistemaBiblioteca.getInstanciaSistemaBiblioteca().exibir(obterUsuario(codigoUsuario)));
		} catch (Exception e) {
			System.out.println("Não foi possível encontrar o usuário! " + e.getMessage());
		}

	}
	
	public String exibir(IUsuario usuario) {
		String todosEmprestimos = "";
		String todasReservas = "";
		int i = 0;
		for (i = 0; i < usuario.getEmprestimosAtuais().size(); i++) {
			todosEmprestimos += (" \n / Título do Livro: " + usuario.getEmprestimosAtuais().get(i).getLivro().getTitulo()
							+ " / Data do Empréstimo: " + usuario.getEmprestimosAtuais().get(i).getDataEmprestimo()
							+ " / Situação do Empréstimo: Em curso" + " / Data de Devolução: "
							+ usuario.getEmprestimosAtuais().get(i).getDataDevolucaoPrevisao().toString());
		}
		
		for (i = 0; i < usuario.getHistoricoEmprestimos().size(); i++) {
			todosEmprestimos += ("\n / Título do Livro: " + usuario.getHistoricoEmprestimos().get(i).getLivro().getTitulo()
							+ " / Data do Empréstimo: " + usuario.getHistoricoEmprestimos().get(i).getDataEmprestimo()
							+ " / Situação do Empréstimo: Finalizado" + " / Data de Devolução: "
							+ usuario.getHistoricoEmprestimos().get(i).getDataDevolucaoReal().toString());
		}
		for (i = 0; i < usuario.getReservasAtuais().size(); i++) {
			todasReservas += ("\n / Título do Livro: " + usuario.getReservasAtuais().get(i).getLivro().getTitulo()
							+ " / Data de solicitação da Reserva: " + usuario.getReservasAtuais().get(i).getData());
		}
		for (i = 0; i < usuario.getHistoricoReservas().size(); i++) {
			todasReservas += ("\n / Título do Livro: " + usuario.getHistoricoReservas().get(i).getLivro().getTitulo()
							+ " / Data de solicitação da Reserva: " + usuario.getHistoricoReservas().get(i).getData());
		}
		return todosEmprestimos + todasReservas; 
		
	}

	public void consultarLivroPeloCodigo(String codigoLivro) {
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
	
	public void sairAplicacao() {
			System.out.println("Saindo da aplicação...");
			System.exit(0);
		}

	public Livro getLivroByCodigo(String codigoLivro) {

		for (Livro livro : listaLivros)
		if (livro.getCodigoLivro().equals(codigoLivro)) {
			return livro;
		}
		return null;
	}


	}

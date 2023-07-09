package pattern.factory;

import model.entities.Exemplar;
import model.entities.Livro;
import model.entities.Usuario;
import model.entities.UsuarioAlunoGrad;
import model.entities.UsuarioAlunoPosGrad;
import model.entities.UsuarioProfessor;
import pattern.facade_singleton.SistemaBiblioteca;

public class Factory {

	public static void createUsuario(String codigo, String tipoUsuario, String nome) {
		Usuario usuario;
		if (tipoUsuario.equals("Aluno de Graduação")) {
			usuario = new UsuarioAlunoGrad(codigo, tipoUsuario, nome);
		} else if (tipoUsuario.equals("Aluno de Pós-graduação")) {
			usuario = new UsuarioAlunoPosGrad(codigo, tipoUsuario, nome);
		} else if (tipoUsuario.equals("Professor")) {
			usuario = new UsuarioProfessor(codigo, tipoUsuario, nome);
		} else {
			System.out.println("O tipo de usuário inserido é inválido!");
			return;
		}
		SistemaBiblioteca.getInstance().getListaUsuarios().add(usuario);
	}

	public static void createLivro(String codigo, String titulo, String editora, String autor, String edicao,
			String anopublicacao) {
		SistemaBiblioteca.getInstance().getListaLivros()
				.add(new Livro(codigo, titulo, editora, autor, edicao, anopublicacao));
	}

	public static void createExemplar(String codigoLivro, String codigoExemplar, String status) {
		if (SistemaBiblioteca.getInstance().getLivroByCodigo(codigoLivro).equals(null)) {
			System.out.println("o livro de código " + codigoLivro + " não existe no sistema!");
		} else {
			SistemaBiblioteca.getInstance().getListaExemplares().add(new Exemplar(codigoLivro, codigoExemplar, status));
		}
	}

	public static void createUsuarios() {
		createUsuario("123", "Aluno de Graduação", "João da Silva");
		createUsuario("456", "Aluno de Pós-graduação", "Luiz Fernando Rodrigues");
		createUsuario("789", "Aluno de Graduação", "Pedro Paulo");
		createUsuario("100", "Professor", "Carlos Lucena");
	}

	public static void createLivros() {
		createLivro("100", "Engenharia de Software", "AddisonWesley", "Ian Sommervile", "6ª", "2000");
		createLivro("101", "UML - Guia do Usuário", "Campus", "Grady Booch, James Rumbaugh, Ivar Jacobson", "7ª",
				"2000");
		createLivro("200", "Code Complete", "Microsoft Press", "Steve McConnell", "2ª", "2014");
		createLivro("201", "Agile Software, Development,Principles, Patterns,and Practices", "Prentice Hall",
				"Robert Martin", "1ª", "2002");
		createLivro("300", "Refactoring: Improving the Design of Existing Code", "Addison-Wesley Professional",
				"Martin Fowler", "1ª", "1999");
		createLivro("301", "Software Metrics: A Rigorous and Practical Approach", "CRC Press",
				"Norman Fenton,James Bieman", "3ª", "2014");
		createLivro("400", "Design Patterns: Elements of Reusable Object-Oriented Software",
				"Addison-Wesley Professional", "Erich Gamma,Richard Helm, Ralph Johnson, John Vlissides", "1ª", "1994");
		createLivro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language",
				"Addison-Wesley Professional", "Martin Fowler", "3ª", "2003");

	}

	public static void createExemplares() {
		createExemplar("100", "01", "Disponível");
		createExemplar("100", "02", "Disponível");
		createExemplar("101", "03", "Disponível");
		createExemplar("200", "04", "Disponível");
		createExemplar("201", "05", "Disponível");
		createExemplar("300", "06", "Disponível");
		createExemplar("300", "07", "Disponível");
		createExemplar("400", "08", "Disponível");
		createExemplar("400", "09", "Disponível");
	}

	public static void createEmprestimos() {
		SistemaBiblioteca.getInstance().commandRealizarEmprestimo("123", "400");

	}

}

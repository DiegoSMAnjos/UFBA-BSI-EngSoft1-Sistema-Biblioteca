package pattern.factory;

import model.entities.Exemplar;
import model.entities.Livro;
import model.entities.Usuario;
import pattern.facade_singleton.SistemaBiblioteca;
import pattern.strategy.EmprestimoAlunoGradStrategy;
import pattern.strategy.EmprestimoAlunoPosGradStrategy;
import pattern.strategy.EmprestimoProfessorStrategy;

public class Factory {
	static SistemaBiblioteca bib = SistemaBiblioteca.getInstance();
	
	public static void createUsuario(String codigo, String tipoUsuario, String nome) {
		Usuario usuario = new Usuario(codigo, tipoUsuario, nome);
		if (tipoUsuario.equals("Aluno de Graduação")) {
			usuario.setEmprestimoStrategy(new EmprestimoAlunoGradStrategy());
		} else if (tipoUsuario.equals("Aluno de Pós-graduação")) {
			usuario.setEmprestimoStrategy(new EmprestimoAlunoPosGradStrategy());
		} else if (tipoUsuario.equals("Professor")) {
			usuario.setEmprestimoStrategy(new EmprestimoProfessorStrategy());
		}else {
			System.out.println("O tipo de usuário inserido é inválido!");
			return;
		}
		bib.getListaUsuarios().add(usuario);
	}
	
	public static void createLivro() {
		
	}
	
	public static void createExemplar(String codigoLivro, String codigoExemplar, String status) {
		if (SistemaBiblioteca.getInstance().getLivroByCodigo(codigoLivro).equals(null)) {
			System.out.println("o livro de código " + codigoLivro + " não existe no sistema!");
		} else {
			bib.getListaExemplares().add(new Exemplar(codigoLivro, codigoExemplar, status));
		}
	}
	
	

	public static void createUsuarios() {
		createUsuario("123", "Aluno de Graduação", "João da Silva");
		createUsuario("456", "Aluno de Pós-graduação", "Luiz Fernando Rodrigues");
		createUsuario("789", "Aluno de Graduação", "Pedro Paulo");
		createUsuario("100", "Professor", "Carlos Lucena");
	}

	public static void createLivros() {
		bib.getListaLivros().add(new Livro("100", "Engenharia de Software", "AddisonWesley",  "Ian Sommervile", "6ª", "2000"));
		bib.getListaLivros().add(new Livro("101", "UML - Guia do Usuário", "Campus", "Grady Booch, James Rumbaugh, Ivar Jacobson", "7ª", "2000"));
		bib.getListaLivros().add(new Livro("200", "Code Complete", "Microsoft Press", "Steve McConnell", "2ª", "2014"));
		bib.getListaLivros().add(new Livro("201", "Agile Software, Development,Principles, Patterns,and Practices", "Prentice Hall", "Robert Martin", "1ª", "2002"));
		bib.getListaLivros().add(new Livro("300", "Refactoring: Improving the Design of Existing Code","Addison-Wesley Professional", "Martin Fowler", "1ª", "1999"));
		bib.getListaLivros().add(new Livro("301","Software Metrics: A Rigorous and Practical Approach", "CRC Press", "Norman Fenton,James Bieman", "3ª", "2014"));
		bib.getListaLivros().add(new Livro("400", "Design Patterns: Elements of Reusable Object-Oriented Software","Addison-Wesley Professional","Erich Gamma,Richard Helm, Ralph Johnson, John Vlissides", "1ª", "1994"));
		bib.getListaLivros().add(new Livro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language","Addison-Wesley Professional","Martin Fowler", "3ª", "2003"));
		
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
		bib.commandRealizarEmprestimo("123", "400");
		
	}
	
}

package Principal;

import java.util.Scanner;

import Fachada.SistemaBiblioteca;


public class Main {

	public static void main(String[] args) {

		SistemaBiblioteca bib = SistemaBiblioteca.getInstance();
		
		bib.getListaUsuarios().add(new AlunoGraduacao("João da Silva", "123"));
		bib.getListaUsuarios().add(new AlunoPosGraduacao("Luiz Fernando Rodrigues", "456"));
		bib.getListaUsuarios().add(new AlunoGraduacao("Pedro Paulo", "789"));
		bib.getListaUsuarios().add(new Professor("Carlos Lucena", "100"));
		bib.getListaLivros().add(new Livro("100", "Engenharia de Software", "AddisonWesley",  "Ian Sommervile", "6ª", "2000"));
		bib.getListaLivros().add(new Livro("100", "Engenharia de Software", "AddisonWesley",  "Ian Sommervile", "6ª", "2000"));		
		bib.getListaLivros().add(new Livro("101", "UML - Guia do Usuário", "Campus", "Grady Booch, James Rumbaugh, Ivar Jacobson", "7ª", "2000"));
		bib.getListaLivros().add(new Livro("200", "Code Complete", "Microsoft Press", "Steve McConnell", "2ª", "2014"));
		bib.getListaLivros().add(new Livro("201", "Agile Software, Development,Principles, Patterns,and Practices", "Prentice Hall", "Robert Martin", "1ª", "2002"));
		bib.getListaLivros().add(new Livro("300", "Refactoring: Improving the Design of Existing Code","Addison-Wesley Professional", "Martin Fowler", "1ª", "1999"));
		bib.getListaLivros().add(new Livro("300", "Refactoring: Improving the Design of Existing Code","Addison-Wesley Professional", "Martin Fowler", "1ª", "1999"));
		bib.getListaLivros().add(new Livro("400", "Design Patterns: Elements of Reusable Object-Oriented Software","Addison-Wesley Professional","Erich Gamma,Richard Helm, Ralph Johnson, John Vlissides", "1ª", "1994"));
		bib.getListaLivros().add(new Livro("400", "Design Patterns: Elements of Reusable Object-Oriented Software","Addison-Wesley Professional","Erich Gamma,Richard Helm, Ralph Johnson, John Vlissides", "1ª", "1994"));
		bib.getListaLivros().add(new Livro("301","Software Metrics: A Rigorous and Practical Approach", "CRC Press", "Norman Fenton,James Bieman", "3ª", "2014"));
		bib.getListaLivros().add(new Livro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language","Addison-Wesley Professional","Martin Fowler", "3ª", "2003"));
		bib.getListaExemplares().add(new Exemplar("100", "01"));
		
		bib.commandRealizarEmprestimo("123", "400");
		
		System.out.println("Seja Bem-vindo ao Sistema de Biblioteca!\n\nInsira um comando: ");
		try (Scanner sc = new Scanner(System.in)) {
			while(true) {
			    try {
			        String input = sc.nextLine();
			        String[] arguments = input.split(" ");

			        if (bib.getCommands().containsKey(arguments[0])) {
			        	bib.run(arguments);
			        } else {
			            System.out.println("Comando inválido. Por favor insira um comando válido.");
			            continue;
			        }
			    } catch (Exception e) {
			        e.getClass();
			        System.out.println(e.getMessage());
			    }
			}
		}
		
	}
}

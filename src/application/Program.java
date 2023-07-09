package application;

import java.util.Scanner;
import pattern.facade_singleton.SistemaBiblioteca;
import pattern.factory.Factory;

public class Program {

	public static void main(String[] args){
		
		SistemaBiblioteca bib = SistemaBiblioteca.getInstance();
		Factory.createUsuarios();
		Factory.createLivros();
		Factory.createExemplares();
		Factory.createEmprestimos();

		System.out.println("Seja Bem-vindo ao Sistema de Biblioteca!\n\nInsira um comando: ");
		try (Scanner sc = new Scanner(System.in)) {
			while (true) {
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

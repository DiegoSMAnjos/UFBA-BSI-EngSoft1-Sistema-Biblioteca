package Command;

import Fachada.SistemaBiblioteca;

public class CommandRealizarDevolucao implements Command{

	@Override
	public void execute(String[] args) throws Exception {
		SistemaBiblioteca.getInstance().realizarDevolucaoLivro(args[1], args[2]);		
		
	}

}

package pattern.command;

import pattern.facade_singleton.SistemaBiblioteca;

public class CommandRealizarDevolucao implements Command{

	@Override
	public void execute(String[] args) throws Exception {
		SistemaBiblioteca.getInstance().commandRealizarDevolucao(args[1], args[2]);		
		
	}

}

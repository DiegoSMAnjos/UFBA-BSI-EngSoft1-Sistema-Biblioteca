package pattern.command;

import pattern.facade_singleton.SistemaBiblioteca;

public class CommandRealizarEmprestimo implements Command{

	@Override
	public void execute(String[] args){
		SistemaBiblioteca.getInstance().commandRealizarEmprestimo(args[1], args[2]);		
		
	}

}

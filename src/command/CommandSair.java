package command;

import facade_singleton.SistemaBiblioteca;

public class CommandSair implements Command {

	@Override
	public void execute(String[] args) throws Exception {
		SistemaBiblioteca.getInstance().commandSair();	
	}

}

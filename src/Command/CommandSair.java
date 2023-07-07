package Command;

import Fachada.SistemaBiblioteca;

public class CommandSair implements Command {

	@Override
	public void execute(String[] args) throws Exception {
		SistemaBiblioteca.getInstance().sairAplicacao();	
	}

}

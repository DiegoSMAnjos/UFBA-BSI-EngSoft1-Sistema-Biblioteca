package command;

import facade_singleton.SistemaBiblioteca;

public class CommandConsultarUsuario implements Command{

	@Override
	public void execute(String[] args) {
		SistemaBiblioteca.getInstance().commandConsultarUsuario(args[1]);		
	
	}

}

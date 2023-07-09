package pattern.command;

import pattern.facade_singleton.SistemaBiblioteca;

public class CommandConsultarNotificacoes implements Command{

	@Override
	public void execute(String[] args) throws Exception {
		SistemaBiblioteca.getInstance().commandConsultarNotificacoes(args[1]);		
	}

}

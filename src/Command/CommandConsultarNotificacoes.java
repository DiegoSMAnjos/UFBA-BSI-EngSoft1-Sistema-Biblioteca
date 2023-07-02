package Command;

import Fachada.SistemaBiblioteca;

public class CommandConsultarNotificacoes implements Command{

	@Override
	public void execute(String[] args) throws Exception {
		SistemaBiblioteca.getInstanciaSistemaBiblioteca().consultarNotificacoes(args[1]);		
	}

}

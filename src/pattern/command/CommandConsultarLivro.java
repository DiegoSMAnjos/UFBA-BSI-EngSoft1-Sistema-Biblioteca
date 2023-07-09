package pattern.command;

import pattern.facade_singleton.SistemaBiblioteca;

public class CommandConsultarLivro implements Command{

	@Override
	public void execute(String[] args) throws Exception {
		SistemaBiblioteca.getInstance().commandConsultarLivro(args[1]);		
		
	}

}

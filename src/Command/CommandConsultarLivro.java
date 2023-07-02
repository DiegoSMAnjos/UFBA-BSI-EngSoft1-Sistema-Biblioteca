package Command;

import Fachada.SistemaBiblioteca;

public class CommandConsultarLivro implements Command{

	@Override
	public void execute(String[] args) throws Exception {
		SistemaBiblioteca.getInstanciaSistemaBiblioteca().consultarLivroPeloCodigo(args[1]);		
		
	}

}

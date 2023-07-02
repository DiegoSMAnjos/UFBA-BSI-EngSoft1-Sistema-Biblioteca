package Command;

import Fachada.SistemaBiblioteca;

public class CommandConsultarUsuario implements Command{

	@Override
	public void execute(String[] args) {
		SistemaBiblioteca.getInstanciaSistemaBiblioteca().consultarUsuarioPeloCodigo(args[1]);		
	
	}

}

package Command;

import Fachada.SistemaBiblioteca;

public class CommandAdicionarObservador implements Command{

	@Override
	public void execute(String[] args)  {
		SistemaBiblioteca.getInstance().commandAdicionarObservador(args[1], args[2]);		
	}

}

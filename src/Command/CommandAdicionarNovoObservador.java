package Command;

import Fachada.SistemaBiblioteca;

public class CommandAdicionarNovoObservador implements Command{

	@Override
	public void execute(String[] args)  {
		SistemaBiblioteca.getInstanciaSistemaBiblioteca().adicionarObservador(args[1], args[2]);		
	}

}

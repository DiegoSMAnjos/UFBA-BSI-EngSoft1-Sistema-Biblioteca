package Command;

import Fachada.SistemaBiblioteca;

public class CommandRealizarEmprestimo implements Command{

	@Override
	public void execute(String[] args){
		SistemaBiblioteca.getInstanciaSistemaBiblioteca().realizarEmprestimoLivro(args[1], args[2]);		
		
	}

}

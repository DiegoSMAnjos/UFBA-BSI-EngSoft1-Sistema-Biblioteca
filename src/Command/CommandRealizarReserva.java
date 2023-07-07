package Command;

import Fachada.SistemaBiblioteca;

public class CommandRealizarReserva implements Command{

	@Override
	public void execute(String[] args){
		SistemaBiblioteca.getInstance().realizarReservaLivro(args[1], args[2]);		
		
	}

}

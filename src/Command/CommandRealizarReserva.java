package Command;

import Fachada.SistemaBiblioteca;

public class CommandRealizarReserva implements Command{

	@Override
	public void execute(String[] args){
		SistemaBiblioteca.getInstance().commandRealizarReserva(args[1], args[2]);		
		
	}

}

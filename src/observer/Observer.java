package Observer;

import Principal.Livro;

public interface Observer {
		public void update(Livro livro); 
		public int getQuantidadeNotificacoes(); 
		
}

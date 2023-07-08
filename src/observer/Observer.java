package observer;

import model.entities.Livro;

public interface Observer {
		public void update(Livro livro); 
		public int getQuantidadeNotificacoes(); 
		
}

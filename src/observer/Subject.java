package observer;

import model.entities.Livro;

public interface Subject {
	public void addObserver(Observer observador);

	public void removeObserver(Observer observador);

	public void notifyObserver(Livro livro);
}

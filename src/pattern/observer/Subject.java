package pattern.observer;


public interface Subject {
	public void addObserver(Observer observador);

	public void removeObserver(Observer observador);

	public void notifyObserver();
}

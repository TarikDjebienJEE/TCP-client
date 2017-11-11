package tp2.client.mvc.designPattern;

public interface IObservable {
	public void addObserver(IObserver obs);
	public void removeObserver();
	public void notifyObserver();
}

package tp2.client.mvc.model;

import java.util.ArrayList;
import java.util.Map;

import tp2.client.mvc.designPattern.IObservable;
import tp2.client.mvc.designPattern.IObserver;
import tp2.client.mvc.model.service.IRepertoireService;

public abstract class AbstractModel implements IObservable {
	
	/**
	 * Le modele
	 */
	protected Map<String, Object> modelMap;
	
	/**
	 * Service du model
	 */
	protected IRepertoireService repertoireService;
	
	public IRepertoireService getRepertoireService() {
		return repertoireService;
	}
	
	public Map<String, Object> getModelMap() {
		return modelMap;
	}
	
	//IMPLÉMENTATION PATTERN OBSERVER
	
	private ArrayList<IObserver> listObserver = new ArrayList<IObserver>();
	
	public void addObserver(IObserver obs) {
		this.listObserver.add(obs);
	}

	public void notifyObserver() {
		//Transmission de la donnée
		for(IObserver obs : listObserver)
			obs.update(modelMap);
	}

	public void removeObserver() {
		listObserver = new ArrayList<IObserver>();
	}

}

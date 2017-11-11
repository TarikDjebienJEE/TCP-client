package tp2.client.mvc.model.service.impl;

import tp2.client.mvc.model.dao.Repertoire;
import tp2.client.mvc.model.dao.impl.CarnetProxy;
import tp2.client.mvc.model.entity.Personne;
import tp2.client.mvc.model.exception.CarnetNotFoundException;
import tp2.client.mvc.model.exception.UserNotFoundException;
import tp2.client.mvc.model.factory.IPersonneFactory;
import tp2.client.mvc.model.factory.impl.PersonneFactory;
import tp2.client.mvc.model.service.IRepertoireService;

public class RepertoireServiceImpl implements IRepertoireService {
	
	private CarnetProxy carnet;
	protected IPersonneFactory personneFactory;
	
	public RepertoireServiceImpl(){
		this.carnet = new CarnetProxy();
		this.personneFactory = new PersonneFactory();
	}

	@Override
	public boolean fixerRepertoire(CarnetProxy repertoire) {
		if(repertoire != null){
			this.carnet = repertoire;
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public Repertoire getRepertoire() {
		return getCarnet();
	}

	@Override
	public Personne createPersonne(String nom, String email, String url, String info) {
		return personneFactory.createPersonne(nom, email, url, info);
	}

	@Override
	public boolean ajouterPersonne(int idCarnet, Personne creerPersonne) throws CarnetNotFoundException {
		return this.carnet.ajouterPersonne(idCarnet, creerPersonne);
	}
	
	@Override
	public boolean retirerPersonne(int idCarnet, String nom) throws CarnetNotFoundException {
		return this.carnet.retirerPersonne(idCarnet, nom);
	}

	@Override
	public boolean modifierPersonne(int idCarnet, Personne creerPersonne) throws CarnetNotFoundException {
		return this.carnet.modifierPersonne(idCarnet, creerPersonne);
	}

	@Override
	public Personne chercherPersonne(int idCarnet, String nomPersonne) throws CarnetNotFoundException {
		return this.carnet.chercherPersonne(idCarnet, nomPersonne);
	}

	@Override
	public String[] listerPersonnes(int idCarnet) throws CarnetNotFoundException {
		return this.carnet.listerPersonnes(idCarnet);
	}
	
	@Override
	public boolean ajouterCarnet() {
		return this.carnet.ajouterCarnet();
	}

	@Override
	public String[] listerCarnets() {
		return this.carnet.listerCarnets();
	}

	@Override
	public tp2.client.mvc.model.entity.Carnet chercherCarnet(String idCarnet) throws CarnetNotFoundException {
		return this.carnet.chercherCarnet(idCarnet);
	}

	@Override
	public boolean retirerCarnet(String idCarnet) throws CarnetNotFoundException {
		return this.carnet.retirerCarnet(idCarnet);
	}

	
	/**
	 * @return the carnet
	 */
	public Repertoire getCarnet() {
		return carnet;
	}

	@Override
	public void closeConnection() {
		this.carnet.closeSocketConnection();
	}

	@Override
	public boolean seConnecter(String login, String password) throws UserNotFoundException{
		return this.carnet.authenticate(login,password);
	}

}

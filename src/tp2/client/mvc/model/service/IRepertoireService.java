package tp2.client.mvc.model.service;

import tp2.client.mvc.model.dao.Repertoire;
import tp2.client.mvc.model.dao.impl.CarnetProxy;
import tp2.client.mvc.model.entity.Personne;
import tp2.client.mvc.model.exception.CarnetNotFoundException;
import tp2.client.mvc.model.exception.UserNotFoundException;

public interface IRepertoireService {

	public boolean fixerRepertoire(CarnetProxy repertoire);

	public Repertoire getRepertoire();

	public Personne createPersonne(String nom, String email, String url, String info);

	public boolean ajouterPersonne(int idCarnet, Personne creerPersonne) throws CarnetNotFoundException;

	public boolean modifierPersonne(int idCarnet, Personne creerPersonne) throws CarnetNotFoundException;
	
	public boolean retirerPersonne(int idCarnet, String nom) throws CarnetNotFoundException;

	public Personne chercherPersonne(int idCarnet, String nomPersonne) throws CarnetNotFoundException;

	public String[] listerPersonnes(int idCarnet) throws CarnetNotFoundException;

	public void closeConnection();

	public boolean ajouterCarnet();

	public String[] listerCarnets();

	public tp2.client.mvc.model.entity.Carnet chercherCarnet(String idCarnet) throws CarnetNotFoundException;

	public boolean retirerCarnet(String idCarnet) throws CarnetNotFoundException;

	public boolean seConnecter(String login, String password) throws UserNotFoundException;

}

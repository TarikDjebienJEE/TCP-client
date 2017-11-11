package tp2.client.mvc.controller;

import tp2.client.mvc.model.AbstractModel;
import tp2.client.mvc.model.dao.impl.CarnetProxy;
import tp2.client.mvc.model.entity.Personne;

public abstract class AbstractControler {

	protected AbstractModel repertoireModel;

	public AbstractControler(AbstractModel repertoireModel){
		this.repertoireModel = repertoireModel;
	}
	
	/**
	 * Recupere le repertoire courant.
	 */
	public abstract void getRepertoire();
	
	/**
	 * Permet de fixer le repertoire courant.
	 */
	public abstract void fixerRepertoire(CarnetProxy repertoire);
	
	/**
	 * Fabrique d'une personne.
	 */
	public abstract void creerPersonne(String nom, String email, String url, String info);

	/**
	 * Permet d'ajouter une Personne dans le repertoire.
	 * @param creerPersonne
	 */
	public abstract void ajouterPersonne(int idCarnet, Personne creerPersonne);
	
	/**
	 * Permet de retirer une personne dans le repertoire.
	 * @param nom
	 * @return
	 */
	public abstract void retirerPersonne(int idCarnet, String nom);

	/**
	 * Permet de modifier une personne dans le repertoire
	 * @param creerPersonne
	 * @return
	 */
	public abstract void modifierPersonne(int idCarnet, Personne creerPersonne);

	/**
	 * Permet de chercher une personne dans le repertoire.
	 * @param nomPersonne
	 * @return
	 */
	public abstract void chercherPersonne(int idCarnet, String nomPersonne);

	/**
	 * Permet de lister tous les noms de personnes disponibles dans le repertoire.
	 * @return
	 */
	public abstract void listerPersonnes(int idCarnet);

	/**
	 * Permet d'ajouter un carnet dans la BDD.
	 */
	public  abstract void ajouterCarnet();

	/**
	 * Permet de lister tous les identifiants de carnets disponibles dans la base de données.
	 * @return
	 */
	public abstract void listerCarnets();

	/**
	 * Permet de chercher un carnet dans la base de donnée.
	 * @param idCarnet l'identifiant du carnet
	 * @return
	 */
	public abstract void chercherCarnet(String idCarnet);

	/**
	 * Permet de retirer un carnet dans la base de donnée.
	 * @param nom
	 * @return
	 */
	public abstract void retirerCarnet(String idCarnet);
	
	public void closeMyConnection() {
		repertoireModel.getRepertoireService().closeConnection();
	}

	public abstract void seConnecter(String login, String password);
	
}

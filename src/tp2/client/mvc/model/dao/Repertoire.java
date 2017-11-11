package tp2.client.mvc.model.dao;

import tp2.client.mvc.model.entity.Personne;
import tp2.client.mvc.model.exception.CarnetNotFoundException;


/**
 * Contrat d'un Data Access Object pour une entite Personne
 * On retrouve alors les opérations CRUD.
 * 
 * @author tarik
 * @version 1.0
 */
public interface Repertoire {
	
	/**
	 * Ajouter une personne dans le repertoire.
	 *
	 * @param personne La personne a ajouter.
	 * @return false Si deja presente.
	 *
	 */
	public boolean ajouterPersonne (int idCarnet, Personne personne) throws CarnetNotFoundException;

	/**
	 * Modifier une personne dans le repertoire.
	 *
	 * @param personne la personne a modifier.
	 * @return false Si le nom de la personne n'existe pas.
	 *
	 */
	public boolean modifierPersonne (int idCarnet, Personne personne) throws CarnetNotFoundException;

	/**
	 * Retirer une personne du repertoire.
	 *
	 * @param nom Le nom de la personne a supprimer.
	 * @return false Si le nom de la personne n'existe pas.
	 *
	 */
	public boolean retirerPersonne (int idCarnet, String nom) throws CarnetNotFoundException;

	/**
	 * Rechercher une personne dans le repertoire.
	 *
	 * @param nom Le nom de la personne a rechercher.
	 * @return null Si la personne n'existe pas.
	 *
	 */
	public Personne chercherPersonne (int idCarnet, String nom) throws CarnetNotFoundException;

	/**
	 * Lister les noms des personnes.
	 *
	 * @return Un tableau des noms des personnes.
	 *
	 */
	public String[] listerPersonnes (int idCarnet) throws CarnetNotFoundException;

}
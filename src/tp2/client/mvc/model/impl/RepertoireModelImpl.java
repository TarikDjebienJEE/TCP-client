package tp2.client.mvc.model.impl;

import java.util.HashMap;

import tp2.client.mvc.model.AbstractModel;
import tp2.client.mvc.model.service.impl.RepertoireServiceImpl;

public class RepertoireModelImpl extends AbstractModel {
	
	// Les cles du model
	public static final String SE_CONNECTER = "seConnecter";
	public static final String RETIRER_CARNET = "retirerCarnet";
	public static final String CHERCHER_CARNET = "chercherCarnet";
	public static final String LISTER_CARNETS = "listerCarnets";
	public static final String AJOUTER_CARNET = "ajouterCarnet";
	public static final String LISTER_PERSONNES = "listerPersonnes";
	public static final String CHERCHER_PERSONNE = "chercherPersonne";
	public static final String MODIFIER_PERSONNE = "modifierPersonne";
	public static final String RETIRER_PERSONNE = "retirerPersonne";
	public static final String AJOUTER_PERSONNE = "ajouterPersonne";
	public static final String CREER_PERSONNE = "creerPersonne";
	public static final String FIXER_REPERTOIRE = "fixerRepertoire";
	public static final String GET_REPERTOIRE = "getRepertoire";
	
	public RepertoireModelImpl() {
		this.repertoireService = new RepertoireServiceImpl();
		this.modelMap = new HashMap<String, Object>();
	}
	
	

}

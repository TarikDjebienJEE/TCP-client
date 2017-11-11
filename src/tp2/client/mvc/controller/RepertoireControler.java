package tp2.client.mvc.controller;

import tp2.client.mvc.model.AbstractModel;
import tp2.client.mvc.model.dao.Repertoire;
import tp2.client.mvc.model.dao.impl.CarnetProxy;
import tp2.client.mvc.model.entity.Personne;
import tp2.client.mvc.model.exception.CarnetNotFoundException;
import tp2.client.mvc.model.exception.UserNotFoundException;
import tp2.client.mvc.model.impl.RepertoireModelImpl;
import tp2.client.mvc.model.net.tcp.ClientProtocole;

public class RepertoireControler extends AbstractControler {

	public RepertoireControler(AbstractModel repertoireModel) {
		super(repertoireModel);
	}

	@Override
	public void getRepertoire() {

		Repertoire repertoire = repertoireModel.getRepertoireService().getRepertoire();

		if(repertoire != null) {
			repertoireModel.getModelMap().put(RepertoireModelImpl.GET_REPERTOIRE, repertoire);
		}else{
			repertoireModel.getModelMap().put(RepertoireModelImpl.GET_REPERTOIRE, null);
		}

		repertoireModel.notifyObserver();
	}

	@Override
	public void fixerRepertoire(CarnetProxy repertoire) {

		if(repertoireModel.getRepertoireService().fixerRepertoire(repertoire)){
			repertoireModel.getModelMap().put(RepertoireModelImpl.FIXER_REPERTOIRE, ClientProtocole.MESSAGE_ORDER_SUCCESS);
		}else{
			repertoireModel.getModelMap().put(RepertoireModelImpl.FIXER_REPERTOIRE, ClientProtocole.MESSAGE_ORDER_FAILED);
		}

		repertoireModel.notifyObserver();
	}

	@Override
	public void creerPersonne(String nom, String email, String url, String info) {

		Personne personne = repertoireModel.getRepertoireService().createPersonne(nom, email, url, info);

		if( personne != null ){
			repertoireModel.getModelMap().put(RepertoireModelImpl.CREER_PERSONNE, personne);
		}else{
			repertoireModel.getModelMap().put(RepertoireModelImpl.CREER_PERSONNE, null);
		}

		repertoireModel.notifyObserver();
	}

	@Override
	public void ajouterPersonne(int idCarnet, Personne creerPersonne) {

		try {
			if(repertoireModel.getRepertoireService().ajouterPersonne(idCarnet, creerPersonne)) {
				repertoireModel.getModelMap().put(RepertoireModelImpl.AJOUTER_PERSONNE, ClientProtocole.MESSAGE_ORDER_SUCCESS);
			}else{
				repertoireModel.getModelMap().put(RepertoireModelImpl.AJOUTER_PERSONNE, ClientProtocole.MESSAGE_ORDER_FAILED);
			}
		} catch (CarnetNotFoundException e) {
			repertoireModel.getModelMap().put(RepertoireModelImpl.AJOUTER_PERSONNE, ClientProtocole.MESSAGE_ORDER_ERROR);
		} finally {
			repertoireModel.notifyObserver();
		}
	}

	@Override
	public void retirerPersonne(int idCarnet, String nom) {

		try {
			if(repertoireModel.getRepertoireService().retirerPersonne(idCarnet, nom)) {
				repertoireModel.getModelMap().put(RepertoireModelImpl.RETIRER_PERSONNE, ClientProtocole.MESSAGE_ORDER_SUCCESS);
			}else{
				repertoireModel.getModelMap().put(RepertoireModelImpl.RETIRER_PERSONNE, ClientProtocole.MESSAGE_ORDER_FAILED);
			}
		} catch (CarnetNotFoundException e) {
			repertoireModel.getModelMap().put(RepertoireModelImpl.RETIRER_PERSONNE, ClientProtocole.MESSAGE_ORDER_ERROR);
		} finally {
			repertoireModel.notifyObserver();
		}
	}

	@Override
	public void modifierPersonne(int idCarnet, Personne creerPersonne) {

		try {
			if(repertoireModel.getRepertoireService().modifierPersonne(idCarnet, creerPersonne)) {
				repertoireModel.getModelMap().put(RepertoireModelImpl.MODIFIER_PERSONNE,  ClientProtocole.MESSAGE_ORDER_SUCCESS);
			}else{
				repertoireModel.getModelMap().put(RepertoireModelImpl.MODIFIER_PERSONNE, ClientProtocole.MESSAGE_ORDER_FAILED);
			}
		} catch (CarnetNotFoundException e) {
			repertoireModel.getModelMap().put(RepertoireModelImpl.MODIFIER_PERSONNE, ClientProtocole.MESSAGE_ORDER_ERROR);
		} finally {
			repertoireModel.notifyObserver();
		}
	}

	@Override
	public void chercherPersonne(int idCarnet, String nomPersonne) {

		Personne personneRecherche;
		try {
			personneRecherche = repertoireModel.getRepertoireService().chercherPersonne(idCarnet, nomPersonne);
			if( personneRecherche != null) {
				repertoireModel.getModelMap().put(RepertoireModelImpl.CHERCHER_PERSONNE, personneRecherche);
			}else{
				repertoireModel.getModelMap().put(RepertoireModelImpl.CHERCHER_PERSONNE, null);
			}
		} catch (CarnetNotFoundException e) {
			repertoireModel.getModelMap().put(RepertoireModelImpl.CHERCHER_PERSONNE, ClientProtocole.MESSAGE_ORDER_ERROR);
		} finally {
			repertoireModel.notifyObserver();
		}
	}

	@Override
	public void listerPersonnes(int idCarnet) {
		String[] nomsPersonnes;
		try {
			nomsPersonnes = this.repertoireModel.getRepertoireService().listerPersonnes(idCarnet);
			if( nomsPersonnes != null && nomsPersonnes.length != 0) {
				repertoireModel.getModelMap().put(RepertoireModelImpl.LISTER_PERSONNES, nomsPersonnes);
			}else{
				repertoireModel.getModelMap().put(RepertoireModelImpl.LISTER_PERSONNES, null);
			}
		} catch (CarnetNotFoundException e) {
			repertoireModel.getModelMap().put(RepertoireModelImpl.LISTER_PERSONNES, ClientProtocole.MESSAGE_ORDER_ERROR);
		} finally {
			repertoireModel.notifyObserver();
		}
	}

	@Override
	public void ajouterCarnet() {

		if(repertoireModel.getRepertoireService().ajouterCarnet()) {
			repertoireModel.getModelMap().put(RepertoireModelImpl.AJOUTER_CARNET, ClientProtocole.MESSAGE_ORDER_SUCCESS);
		}else{
			repertoireModel.getModelMap().put(RepertoireModelImpl.AJOUTER_CARNET, ClientProtocole.MESSAGE_ORDER_FAILED);
		}
		repertoireModel.notifyObserver();
	}

	@Override
	public void listerCarnets() {
		String[] nomsCarnets;
		nomsCarnets = this.repertoireModel.getRepertoireService().listerCarnets();
		if( nomsCarnets != null && nomsCarnets.length != 0) {
			repertoireModel.getModelMap().put(RepertoireModelImpl.LISTER_CARNETS, nomsCarnets);
		}else{
			repertoireModel.getModelMap().put(RepertoireModelImpl.LISTER_CARNETS, null);
		}
		repertoireModel.notifyObserver();
	}

	@Override
	public void chercherCarnet(String idCarnet) {
		tp2.client.mvc.model.entity.Carnet  carnetRecherche;
		try {
			carnetRecherche = repertoireModel.getRepertoireService().chercherCarnet(idCarnet);
			if( carnetRecherche != null) {
				repertoireModel.getModelMap().put(RepertoireModelImpl.CHERCHER_CARNET, carnetRecherche);
			}else{
				repertoireModel.getModelMap().put(RepertoireModelImpl.CHERCHER_CARNET, null);
			}
		} catch (CarnetNotFoundException e) {
			repertoireModel.getModelMap().put(RepertoireModelImpl.CHERCHER_PERSONNE, ClientProtocole.MESSAGE_ORDER_ERROR);
		} finally {
			repertoireModel.notifyObserver();
		}
	}

	@Override
	public void retirerCarnet(String idCarnet) {

		try {
			if(repertoireModel.getRepertoireService().retirerCarnet(idCarnet)) {
				repertoireModel.getModelMap().put(RepertoireModelImpl.RETIRER_CARNET, ClientProtocole.MESSAGE_ORDER_SUCCESS);
			}else{
				repertoireModel.getModelMap().put(RepertoireModelImpl.RETIRER_CARNET, ClientProtocole.MESSAGE_ORDER_FAILED);
			}
		} catch (CarnetNotFoundException e) {
			repertoireModel.getModelMap().put(RepertoireModelImpl.RETIRER_CARNET, ClientProtocole.MESSAGE_ORDER_ERROR);
		} finally {
			repertoireModel.notifyObserver();
		}

	}

	@Override
	public void seConnecter(String login, String password) {
		try {
			if(repertoireModel.getRepertoireService().seConnecter(login, password)) {
				repertoireModel.getModelMap().put(RepertoireModelImpl.SE_CONNECTER, ClientProtocole.MESSAGE_ORDER_SUCCESS);
			}else{
				repertoireModel.getModelMap().put(RepertoireModelImpl.SE_CONNECTER, ClientProtocole.MESSAGE_ORDER_FAILED);
			}
		} catch (UserNotFoundException e) {
			repertoireModel.getModelMap().put(RepertoireModelImpl.SE_CONNECTER, ClientProtocole.MESSAGE_ORDER_ERROR);
		} finally {
			repertoireModel.notifyObserver();
		}
	}

}

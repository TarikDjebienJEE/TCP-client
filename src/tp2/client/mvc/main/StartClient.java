package tp2.client.mvc.main;

import tp2.client.mvc.controller.AbstractControler;
import tp2.client.mvc.controller.RepertoireControler;
import tp2.client.mvc.model.AbstractModel;
import tp2.client.mvc.model.impl.RepertoireModelImpl;
import tp2.client.mvc.view.IHM;

public class StartClient {

	public static void main(String[] args) {
		
		//Instanciation de notre modèle
		AbstractModel repertoireModel = new RepertoireModelImpl();
		
		//Création du contrôleur
		AbstractControler repertoireControler = new RepertoireControler(repertoireModel);
		
		//Création de notre fenêtre avec le contrôleur en paramètre
		IHM repertoireIHM = new IHM(repertoireControler);
		
		//Ajout de la fenêtre comme observer de notre modèle
		repertoireModel.addObserver(repertoireIHM);
	}
}

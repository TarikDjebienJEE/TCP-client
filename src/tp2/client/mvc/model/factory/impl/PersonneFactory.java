package tp2.client.mvc.model.factory.impl;

import tp2.client.mvc.model.entity.Personne;
import tp2.client.mvc.model.factory.IPersonneFactory;

public class PersonneFactory implements IPersonneFactory {

	@Override
	public Personne createPersonne(String nom, String email, String url, String info) {
		return new Personne(nom, email, url, info);
	}

}

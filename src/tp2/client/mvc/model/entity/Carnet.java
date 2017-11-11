package tp2.client.mvc.model.entity;

import java.util.LinkedList;
import java.util.List;

public class Carnet {
	
	private int id;
	private List<Personne> lesPersonnes;
	
	public Carnet(int id) {
		this.setLesPersonnes(new LinkedList<Personne>());
		this.setId(id);
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	private void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the lesPersonnes
	 */
	public List<Personne> getLesPersonnes() {
		return lesPersonnes;
	}

	/**
	 * @param lesPersonnes the lesPersonnes to set
	 */
	public void setLesPersonnes(List<Personne> lesPersonnes) {
		this.lesPersonnes = lesPersonnes;
	}

}

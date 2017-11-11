package tp2.client.mvc.view.listener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tp2.client.mvc.view.IHM;

public class ListePersonnesSelectionListener implements ListSelectionListener {

	/**
	 * 
	 */
	private final IHM ihm;

	/**
	 * @param ihm
	 */
	public ListePersonnesSelectionListener(IHM ihm) {
		this.ihm = ihm;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		this.ihm.chercherPersonne( (String) this.ihm.getListePersonnes().getSelectedValue());
	}

}
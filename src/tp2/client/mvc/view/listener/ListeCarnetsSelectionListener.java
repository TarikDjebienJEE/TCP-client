package tp2.client.mvc.view.listener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tp2.client.mvc.view.IHM;

public class ListeCarnetsSelectionListener implements ListSelectionListener {

	/**
	 * 
	 */
	private final IHM ihm;

	/**
	 * @param ihm
	 */
	public ListeCarnetsSelectionListener(IHM ihm) {
		this.ihm = ihm;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		this.ihm.chercherCarnet( (String) this.ihm.getListeCarnets().getSelectedValue());
	}

}

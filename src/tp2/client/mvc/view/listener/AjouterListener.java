package tp2.client.mvc.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp2.client.mvc.view.IHM;

public class AjouterListener implements ActionListener {

	/**
	 * 
	 */
	private final IHM ihm;

	/**
	 * @param ihm
	 */
	public AjouterListener(IHM ihm) {
		this.ihm = ihm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ihm.boutonAjouterPersonne) {
			this.ihm.ajouterPersonne();
		}
		
		if(e.getSource() == ihm.boutonAjouterCarnet) {
			this.ihm.ajouterCarnet();
		}
		
		
	}                
}
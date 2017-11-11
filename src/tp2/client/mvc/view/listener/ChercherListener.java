package tp2.client.mvc.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp2.client.mvc.view.IHM;

public class ChercherListener implements ActionListener {

	/**
	 * 
	 */
	private final IHM ihm;

	/**
	 * @param ihm
	 */
	public ChercherListener(IHM ihm) {
		this.ihm = ihm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ihm.boutonChercherPersonne){
			this.ihm.chercherPersonne(this.ihm.getChampNom().getText());
		}
		
		if(e.getSource() == ihm.boutonChercherCarnet){
			this.ihm.chercherCarnet(this.ihm.getChampIdCarnet().getText());
		}
	}
}
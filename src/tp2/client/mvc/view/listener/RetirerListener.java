package tp2.client.mvc.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp2.client.mvc.view.IHM;

public class RetirerListener implements ActionListener {

	/**
	 * 
	 */
	private final IHM ihm;

	/**
	 * @param ihm
	 */
	public RetirerListener(IHM ihm) {
		this.ihm = ihm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ihm.boutonRetirerPersonne){
			this.ihm.retirerPersonne(this.ihm.getChampNom().getText());
		}
		if(e.getSource() == ihm.boutonRetirerCarnet){
			this.ihm.retirerCarnet(this.ihm.getChampIdCarnet().getText());
		}
	}
}
package tp2.client.mvc.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp2.client.mvc.view.IHM;

public class ListerListener implements ActionListener {

	/**
	 * 
	 */
	private final IHM ihm;

	/**
	 * @param ihm
	 */
	public ListerListener(IHM ihm) {
		this.ihm = ihm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ihm.boutonListerPersonnes){
			this.ihm.listerPersonnes();
		}
		if(e.getSource() == ihm.boutonListerCarnets){
			this.ihm.listerCarnets();
		}
	}
}
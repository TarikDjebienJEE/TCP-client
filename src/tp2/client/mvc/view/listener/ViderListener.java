package tp2.client.mvc.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp2.client.mvc.view.IHM;

public class ViderListener implements ActionListener {

	/**
	 * 
	 */
	private final IHM ihm;

	/**
	 * @param ihm
	 */
	public ViderListener(IHM ihm) {
		this.ihm = ihm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ihm.boutonPersonneVider){
			this.ihm.viderFichePersonne();
		}
		
		if(e.getSource() == ihm.boutonCarnetVider){
			this.ihm.viderFicheCarnet();
		}
		
		if(e.getSource() == ihm.boutonloginVider){
			this.ihm.viderFicheLogin();
		}
	}                
}
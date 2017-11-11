package tp2.client.mvc.view.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import tp2.client.mvc.view.IHM;

//******* LES LISTENERS POUR NOS COMPOSANTS **********
public class FenetreListener implements WindowListener {

	/**
	 * 
	 */
	private final IHM ihm;

	/**
	 * @param ihm
	 */
	public FenetreListener(IHM ihm) {
		this.ihm = ihm;
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.ihm.closeConnection();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

}
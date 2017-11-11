package tp2.client.mvc.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp2.client.mvc.view.IHM;

public class ConnectionListener implements ActionListener {

	/**
	 * 
	 */
	private final IHM ihm;

	/**
	 * @param ihm
	 */
	public ConnectionListener(IHM ihm) {
		this.ihm = ihm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			this.ihm.seConnecter();
	}

}

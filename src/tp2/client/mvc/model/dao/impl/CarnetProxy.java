package tp2.client.mvc.model.dao.impl;

import java.io.IOException;

import tp2.client.mvc.constante.ConfigurationConstantes;
import tp2.client.mvc.model.dao.ListeRepertoires;
import tp2.client.mvc.model.dao.Repertoire;
import tp2.client.mvc.model.entity.Personne;
import tp2.client.mvc.model.exception.CarnetNotFoundException;
import tp2.client.mvc.model.exception.UserNotFoundException;
import tp2.client.mvc.model.net.tcp.ClientTCP;
import tp2.client.mvc.model.net.tcp.ClientProtocole;


public class CarnetProxy implements Repertoire, ListeRepertoires {

	private static final String HOST = ConfigurationConstantes.INNET_ADDRESS;
	private static final int PORT = ConfigurationConstantes.PORT;
	
	private ClientTCP clientTCP;
	
	public CarnetProxy() {
		this.clientTCP = new ClientTCP(HOST,PORT);
	}
	
	@Override
	public boolean ajouterPersonne(int idCarnet, Personne personne) throws CarnetNotFoundException {
		try {
			clientTCP.writeRequest(ClientProtocole.sendRequestInsertPersonne(idCarnet, personne));
			String serverState = clientTCP.getResponse();
			if(serverState.startsWith(ClientProtocole.MESSAGE_ORDER_ERROR)){
				throw new CarnetNotFoundException();
			}
			return serverState.equals(ClientProtocole.MESSAGE_ORDER_SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modifierPersonne(int idCarnet, Personne personne) throws CarnetNotFoundException {
		try {
			clientTCP.writeRequest(ClientProtocole.sendRequestUpdatePersonne(idCarnet, personne));
			String serverState = clientTCP.getResponse();
			if(serverState.startsWith(ClientProtocole.MESSAGE_ORDER_ERROR)){
				throw new CarnetNotFoundException();
			}
			return serverState.equals(ClientProtocole.MESSAGE_ORDER_SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean retirerPersonne(int idCarnet, String nomPersonne) throws CarnetNotFoundException {
		try {
			clientTCP.writeRequest(ClientProtocole.sendRequestDeletePersonne(idCarnet, nomPersonne));
			String serverState = clientTCP.getResponse();
			if(serverState.startsWith(ClientProtocole.MESSAGE_ORDER_ERROR)){
				throw new CarnetNotFoundException();
			}
			return serverState.equals(ClientProtocole.MESSAGE_ORDER_SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Personne chercherPersonne(int idCarnet, String nomPersonne) throws CarnetNotFoundException{
		try {
			clientTCP.writeRequest(ClientProtocole.sendRequestSelectByNamePersonne(idCarnet, nomPersonne));
			return ClientProtocole.getResponseSelectByNamePersonne(clientTCP.getResponse());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String[] listerPersonnes(int idCarnet) throws CarnetNotFoundException {
		try {
			clientTCP.writeRequest(ClientProtocole.sendRequestSelectAllPersonne(idCarnet));
			return ClientProtocole.getResponseSelectAllPersonne(clientTCP.getResponse());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean ajouterCarnet() {
		
		try {
			clientTCP.writeRequest(ClientProtocole.sendRequestInsertCarnet());
			String serverState = clientTCP.getResponse();
			return serverState.equals(ClientProtocole.MESSAGE_ORDER_SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean retirerCarnet(String idCarnet) throws CarnetNotFoundException {
		
		try {
			clientTCP.writeRequest(ClientProtocole.sendRequestDeleteCarnet(idCarnet));
			String serverState = clientTCP.getResponse();
			if(serverState.startsWith(ClientProtocole.MESSAGE_ORDER_ERROR)){
				throw new CarnetNotFoundException();
			}
			return serverState.equals(ClientProtocole.MESSAGE_ORDER_SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public tp2.client.mvc.model.entity.Carnet chercherCarnet(String idCarnet) throws CarnetNotFoundException {
		
		try {
			clientTCP.writeRequest(ClientProtocole.sendRequestSelectByIdCarnet(idCarnet));
			return ClientProtocole.getResponseSelectByIdCarnet(clientTCP.getResponse());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String[] listerCarnets() {
		try {
			clientTCP.writeRequest(ClientProtocole.sendRequestSelectAllCarnets());
			return ClientProtocole.getResponseSelectAllCarnets(clientTCP.getResponse());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @return the clientTCP
	 */
	public ClientTCP getClientTCP() {
		return clientTCP;
	}

	/**
	 * @param clientTCP the clientTCP to set
	 */
	public void setClientTCP(ClientTCP clientTCP) {
		this.clientTCP = clientTCP;
	}

	public void closeSocketConnection() {
		try {
			clientTCP.writeRequest(ClientProtocole.sendRequestExitApplication());
			this.clientTCP.closeConnectionClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean authenticate(String login, String password) throws UserNotFoundException {
		try {
			clientTCP.writeRequest(ClientProtocole.sendRequestAuthenticateUser(login, password));
			String serverState = clientTCP.getResponse();
			if(serverState.startsWith(ClientProtocole.MESSAGE_ORDER_ERROR)){
				throw new UserNotFoundException();
			}
			return serverState.equals(ClientProtocole.MESSAGE_ORDER_SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}

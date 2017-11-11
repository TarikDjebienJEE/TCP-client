package tp2.client.mvc.model.net.tcp;

import tp2.client.mvc.model.entity.Personne;
import tp2.client.mvc.model.exception.CarnetNotFoundException;
import tp2.client.mvc.model.factory.ICarnetFactory;
import tp2.client.mvc.model.factory.IPersonneFactory;
import tp2.client.mvc.model.factory.impl.CarnetFactory;
import tp2.client.mvc.model.factory.impl.PersonneFactory;

public final class ClientProtocole {
	
	
	private static final String MESSAGE_AJOUTER_PERSONNE  = "ajouterPersonne";
	private static final String MESSAGE_MODIFIER_PERSONNE = "modifierPersonne";
	private static final String MESSAGE_RETIRER_PERSONNE  = "retirerPersonne";
	private static final String MESSAGE_CHERCHER_PERSONNE = "chercherPersonne";
	private static final String MESSAGE_LISTER_PERSONNE   = "listerPersonne";
	
	private static final String MESSAGE_AJOUTER_CARNET    = "ajouterCarnet";
	private static final String MESSAGE_RETIRER_CARNET    = "retirerCarnet";
	private static final String MESSAGE_CHERCHER_CARNET   = "chercherCarnet";
	private static final String MESSAGE_LISTER_CARNET	  = "listerCarnet";
	
	public static final String MESSAGE_ORDER_SUCCESS 	= "OK";
	public static final String MESSAGE_ORDER_FAILED 	= "KO";
	public static final String MESSAGE_ORDER_ERROR      = "ERROR";
	private static final String PARAMS_SEPARATOR 		= ";";
	private static final String ORDER_SEPARATOR 		= ":";
	
	private static final String MESSAGE_AUTHENTICATE_USER = "connectUser";
	private static final String MESSAGE_EXIT_APP 		  = "EXIT";
	
	
	public static String sendRequestInsertPersonne(int idCarnet, Personne p){
		StringBuffer buffer = new StringBuffer()
		.append(MESSAGE_AJOUTER_PERSONNE)
		.append(ORDER_SEPARATOR)
		.append(requestFormatter(Integer.toString(idCarnet)))
		.append(PARAMS_SEPARATOR)
		.append(requestFormatter(p.getNom()))
		.append(PARAMS_SEPARATOR)
		.append(requestFormatter(p.getEmail()))
		.append(PARAMS_SEPARATOR)
		.append(requestFormatter(p.getUrl()))
		.append(PARAMS_SEPARATOR)
		.append(requestFormatter(p.getInfo()));
		return 	buffer.toString();
	}
	
	public static String sendRequestUpdatePersonne(int idCarnet, Personne p){
		StringBuffer buffer = new StringBuffer()
		.append(MESSAGE_MODIFIER_PERSONNE)
		.append(ORDER_SEPARATOR)
		.append(requestFormatter(Integer.toString(idCarnet)))
		.append(PARAMS_SEPARATOR)
		.append(requestFormatter(p.getNom()))
		.append(PARAMS_SEPARATOR)
		.append(requestFormatter(p.getEmail()))
		.append(PARAMS_SEPARATOR)
		.append(requestFormatter(p.getUrl()))
		.append(PARAMS_SEPARATOR)
		.append(requestFormatter(p.getInfo()));
		return 	buffer.toString();
	}
	
	public static String sendRequestDeletePersonne(int idCarnet, String nomPersonne){
		StringBuffer buffer = new StringBuffer()
		.append(MESSAGE_RETIRER_PERSONNE)
		.append(ORDER_SEPARATOR)
		.append(requestFormatter(Integer.toString(idCarnet)))
		.append(PARAMS_SEPARATOR)
		.append(requestFormatter(nomPersonne));
		return 	buffer.toString();
	}
	
	public static String sendRequestSelectByNamePersonne(int idCarnet, String nomPersonne){
		StringBuffer buffer = new StringBuffer()
		.append(MESSAGE_CHERCHER_PERSONNE)
		.append(ORDER_SEPARATOR)
		.append(requestFormatter(Integer.toString(idCarnet)))
		.append(PARAMS_SEPARATOR)
		.append(requestFormatter(nomPersonne));
		return 	buffer.toString();
	}
	
	public static String sendRequestSelectAllPersonne(int idCarnet){
		StringBuffer buffer = new StringBuffer()
		.append(MESSAGE_LISTER_PERSONNE)
		.append(ORDER_SEPARATOR)
		.append(requestFormatter(Integer.toString(idCarnet)));
		return 	buffer.toString();
	}
	
	public static String sendRequestInsertCarnet() {
		StringBuffer buffer = new StringBuffer()
		.append(MESSAGE_AJOUTER_CARNET);
		return 	buffer.toString();
	}

	public static String sendRequestDeleteCarnet(String idCarnet) {
		StringBuffer buffer = new StringBuffer()
		.append(MESSAGE_RETIRER_CARNET)
		.append(ORDER_SEPARATOR)
		.append(requestFormatter(idCarnet));
		return 	buffer.toString();
	}
	
	public static String sendRequestSelectByIdCarnet(String idCarnet) {
		StringBuffer buffer = new StringBuffer()
		.append(MESSAGE_CHERCHER_CARNET)
		.append(ORDER_SEPARATOR)
		.append(requestFormatter(idCarnet));
		return 	buffer.toString();
	}
	
	public static String sendRequestSelectAllCarnets() {
		StringBuffer buffer = new StringBuffer()
		.append(MESSAGE_LISTER_CARNET);
		return 	buffer.toString();
	}
	
	public static String sendRequestAuthenticateUser(String login, String password) {
		StringBuffer buffer = new StringBuffer()
		.append(MESSAGE_AUTHENTICATE_USER)
		.append(ORDER_SEPARATOR)
		.append(requestFormatter(login))
		.append(PARAMS_SEPARATOR)
		.append(requestFormatter(password));
		return 	buffer.toString();
	}
	
	public static String sendRequestExitApplication() {
		return MESSAGE_EXIT_APP;
	}
	
	public static Personne getResponseSelectByNamePersonne(String serverResponse) throws CarnetNotFoundException{
		IPersonneFactory pf = new PersonneFactory();
		if(serverResponse.startsWith(MESSAGE_ORDER_SUCCESS)){
			String[] params = (serverResponse.split(ORDER_SEPARATOR)[1]).split(PARAMS_SEPARATOR);
			return pf.createPersonne(params[0], params[1], params[2], params[3]);
		}else if(serverResponse.startsWith(MESSAGE_ORDER_ERROR)){
			throw new CarnetNotFoundException();
		}else{
			return null;
		}
	}
	
	public static String[] getResponseSelectAllPersonne(String serverResponse) throws CarnetNotFoundException{
		if(serverResponse.startsWith(MESSAGE_ORDER_SUCCESS)){
			String[] names = (serverResponse.split(ORDER_SEPARATOR)[1]).split(PARAMS_SEPARATOR);
			return names;
		}else if(serverResponse.startsWith(MESSAGE_ORDER_ERROR)){
			throw new CarnetNotFoundException();
		}else{
			return null;
		}
	}

	public static tp2.client.mvc.model.entity.Carnet getResponseSelectByIdCarnet(String serverResponse) 
			throws CarnetNotFoundException {
		ICarnetFactory cf = new CarnetFactory();
		if(serverResponse.startsWith(MESSAGE_ORDER_SUCCESS)){
			String[] params = (serverResponse.split(ORDER_SEPARATOR)[1]).split(PARAMS_SEPARATOR);
			return cf.createCarnet(params[0]);
		}else if(serverResponse.startsWith(MESSAGE_ORDER_ERROR)){
			throw new CarnetNotFoundException();
		}else{
			return null;
		}
	}

	public static String[] getResponseSelectAllCarnets(String serverResponse) {
		if(serverResponse.startsWith(MESSAGE_ORDER_SUCCESS)){
			String[] names = (serverResponse.split(ORDER_SEPARATOR)[1]).split(PARAMS_SEPARATOR);
			return names;
		}else{
			return null;
		}
	}
	
	public static String requestFormatter(String request){
		return request.replaceAll(ORDER_SEPARATOR, "&order").replaceAll(PARAMS_SEPARATOR, "&separator");
	}
	
	public static String responseFormatter(String response){
		return response.replaceAll("&order", ORDER_SEPARATOR).replaceAll("&separator", PARAMS_SEPARATOR);
	}

}

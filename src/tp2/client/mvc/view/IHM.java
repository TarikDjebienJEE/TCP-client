package tp2.client.mvc.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tp2.client.mvc.controller.AbstractControler;
import tp2.client.mvc.designPattern.IObserver;
import tp2.client.mvc.model.dao.Repertoire;
import tp2.client.mvc.model.entity.Carnet;
import tp2.client.mvc.model.entity.Personne;
import tp2.client.mvc.model.impl.RepertoireModelImpl;
import tp2.client.mvc.model.net.tcp.ClientProtocole;
import tp2.client.mvc.view.listener.AjouterListener;
import tp2.client.mvc.view.listener.ChercherListener;
import tp2.client.mvc.view.listener.ConnectionListener;
import tp2.client.mvc.view.listener.FenetreListener;
import tp2.client.mvc.view.listener.ListeCarnetsSelectionListener;
import tp2.client.mvc.view.listener.ListePersonnesSelectionListener;
import tp2.client.mvc.view.listener.ListerListener;
import tp2.client.mvc.view.listener.ModifierListener;
import tp2.client.mvc.view.listener.RetirerListener;
import tp2.client.mvc.view.listener.ViderListener;

/**
 * Interface graphique de notre application repertoire
 * 
 * @author tarik
 */
public class IHM extends JFrame implements IObserver {

	private static final long serialVersionUID = 1L;
	private static final String WINDOWS_TITLE = "Repertoires";
	private static final Dimension WINDOWS_DIMENSION = new Dimension(800, 600);

	private static final String MESSAGE_PROBLEME_DEFAULT = "Aucun probleme a signaler.";
	private static final String MESSAGE_PROBLEME_REPERTOIRE_ABSENT = "Vous n'avez pas fixe le repertoire courant";
	private static final String MESSAGE_PERSONNE_EXISTE_DEJA = "Cette personne existe déjà.";
	private static final String MESSAGE_CARNET_ERROR_CREATION = "L'operation d'ajout carnet a echoué.";
	private static final String MESSAGE_ERROR_LOGIN = "Login/Password invalide";

	//*******    NOTRE INSTANCE DE NOTRE CONTROLEUR   **********
	/**
	 * Controller frontal de notre MVC2
	 */
	protected AbstractControler repertoireControler;

	//******* DONNEES REMONTEES PAR LE CONTROLEUR **************
	/**
	 * Donnees pour mettre a jour la vue
	 */
	protected Map<String, Object> datas;

	//******* COMPOSANTES DE L'INTERFACE HOMME MACHINE.*********

	private final CardLayout cl = new CardLayout();
	
	/**
	 * Un tableau stockant les differentes vue possibles
	 */
	private String[] listContent = {"LOGIN_VUE", "MAIN_VUE"};
		
	/**
	 * Les ecrans
	 */
	protected JTabbedPane onglet;

	/**
	 * Container login de l'IHM
	 */
	protected JPanel loginScreen;
	
	/**
	 * Container personne de l'IHM
	 */
	protected JPanel personneScreen;

	/**
	 * Container carnet de l'IHM
	 */
	protected JPanel carnetScreen;

	/**
	 * Panel pour les boutons
	 */
	protected JPanel loginBoutons;
	
	/**
	 * Panel pour les boutons
	 */
	protected JPanel personneBoutons;

	/**
	 * Panel pour les boutons
	 */
	protected JPanel carnetBoutons;

	/**
	 * Panel pour la fiche Personne
	 */
	protected JPanel panelFichePersonne;

	/**
	 * Panel pour la fiche Carnet
	 */
	protected JPanel panelFicheCarnet;
	
	/**
	 * Panel pour la fiche login
	 */
	protected JPanel panelFicheLogin;

	/**
	 * Panel pour la liste des personnes
	 */
	protected JPanel panelListePersonnes;

	/**
	 * Panel pour la liste des carnets
	 */
	protected JPanel panelListeCarnets;

	/**
	 * Liste des personnes.
	 */
	private JList listePersonnes;

	/**
	 * Liste des carnets.
	 */
	private JList listeCarnets;

	/** 
	 * Login de connexion
	 */
	protected JTextField champLogin;
	
	/** 
	 * Password pour la connexion
	 */
	protected JTextField champPassword;
	
	/** 
	 * Champs composant la fiche Personne.
	 * 
	 * Identifiant du carnet
	 */
	protected JTextField champPersonneIdCarnet;

	/** 
	 * Champs composant la fiche Carnet.
	 * 
	 * Identifiant du carnet
	 */
	protected JTextField champCarnetIdCarnet;

	/** 
	 * Champs composant la fiche Personne.
	 * 
	 * Nom de la personne
	 */
	private JTextField champNom;

	/** 
	 * Champs composant la fiche Personne.
	 * 
	 * Email de la personne
	 */
	protected JTextField champEmail;

	/** 
	 * Champs composant la fiche Personne.
	 * 
	 * URL de sa page personnelle
	 */
	protected JTextField champUrl;

	/** 
	 * Champs composant la fiche Personne.
	 * 
	 * Texte d'information de la personne
	 */
	protected JTextArea champInfo;

	/** 
	 * Le bouton vider clear l'affichage sur l'IHM
	 */
	public JButton boutonloginVider;
	
	/** 
	 * Le bouton connection pour se connecter à l'application sur l'IHM
	 */
	public JButton boutonloginConnection;
	
	/** 
	 * Le bouton vider clear l'affichage sur l'IHM
	 */
	public JButton boutonPersonneVider;

	/** 
	 * Le bouton vider clear l'affichage sur l'IHM
	 */
	public JButton boutonCarnetVider;

	/**
	 * Bouton pour ajouter une personne
	 */
	public JButton boutonAjouterPersonne;

	/**
	 * Bouton pour ajouter un carnet
	 */
	public JButton boutonAjouterCarnet;

	/**
	 * Bouton pour mettre a jour une personne
	 */
	public JButton boutonModifierPersonne;

	/**
	 * Bouton pour retirer une personne
	 */
	public JButton boutonRetirerPersonne;

	/**
	 * Bouton pour retirer un carnet
	 */
	public JButton boutonRetirerCarnet;

	/**
	 * Bouton pour lister toutes les personnes
	 */
	public JButton boutonListerPersonnes;

	/**
	 * Bouton pour lister tous les carnets 
	 */
	public JButton boutonListerCarnets;

	/**
	 * Bouton pour rechercher une personne
	 */
	public JButton boutonChercherPersonne;

	/**
	 * Bouton pour rechercher un carnet
	 */
	public JButton boutonChercherCarnet;

	/**
	 *  Label pour afficher les problemes.
	 */
	protected JLabel labelProblemePersonne;

	/**
	 *  Label pour afficher les problemes.
	 */
	protected JLabel labelProblemeCarnet;
	
	/**
	 *  Label pour afficher les problemes.
	 */
	protected JLabel labelProblemeLogin;

	/**
	 * Constructeur d'initialisation de notre IHM
	 * 
	 * @param repertoireControler le controller vers lequel on delegue les requetes clientes
	 */
	public IHM(AbstractControler repertoireControler) {

		initControllerAndDatas(repertoireControler);
		initIHMConfiguration();
		
		
		
		initFicheLogin();
		initFichePersonne();
		initFicheCarnet();
		
		initBoutonsLogin();
		initBoutonsPersonne();
		initBoutonsCarnet();
		
		initListePersonne();
		initListeCarnet();
		
		initMessage();
		
		pack();
	}

	/**
	 * @param repertoireControler
	 */
	private void initControllerAndDatas(AbstractControler repertoireControler) {
		this.repertoireControler = repertoireControler;
		this.datas = new HashMap<String, Object>();
	}

	/**
	 * Initialisation de la zone de message de notification
	 */
	private void initMessage() {

		this.labelProblemePersonne = new JLabel("");
		this.personneScreen.add (BorderLayout.SOUTH, this.labelProblemePersonne);

		this.labelProblemeCarnet = new JLabel("");
		this.carnetScreen.add (BorderLayout.SOUTH, labelProblemeCarnet);
		
		this.labelProblemeLogin = new JLabel("");
		this.loginScreen.add(BorderLayout.SOUTH, labelProblemeLogin);

		// Fixer l'erreur avec le message par defaut.
		fixerErreur(null);
	}

	/**
	 * Initialisation de la zone d'affichage de la liste de Personne disponible
	 */
	private void initListePersonne() {
		// Cette partie affiche la liste des personnes contenues dans le repertoire.
		// Elle est placee a droite.
		ListePersonnesSelectionListener listePersonnesSelectionListener = new ListePersonnesSelectionListener(this);
		this.setListePersonnes(new JList());
		this.getListePersonnes().addListSelectionListener(listePersonnesSelectionListener);
		this.panelListePersonnes = new JPanel(new BorderLayout());
		this.panelListePersonnes.add(BorderLayout.NORTH, new JLabel("Liste des personnes."));
		panelListePersonnes.setSize(100, this.getHeight()-10);
		JScrollPane scrollPane = new JScrollPane(this.getListePersonnes());
		panelListePersonnes.add(BorderLayout.CENTER, scrollPane);
		this.personneScreen.add(BorderLayout.EAST, panelListePersonnes);
	}

	private void initListeCarnet() {
		ListeCarnetsSelectionListener listeCarnetsSelectionListener = new ListeCarnetsSelectionListener(this);
		this.setListeCarnets(new JList());
		this.getListeCarnets().addListSelectionListener(listeCarnetsSelectionListener);
		this.panelListeCarnets = new JPanel(new BorderLayout());
		this.panelListeCarnets.add(BorderLayout.NORTH, new JLabel("Liste des carnets"));
		panelListeCarnets.setSize(100, this.getHeight()-10);
		JScrollPane scrollPane = new JScrollPane(this.getListeCarnets());
		panelListeCarnets.add(BorderLayout.CENTER, scrollPane);
		this.carnetScreen.add(BorderLayout.EAST, panelListeCarnets);
	}

	/**
	 * Initialisation de la zone de saisie d'une fiche d'une personne
	 */
	private void initFichePersonne() {
		// Cette partie contient la fiche Personne.
		// Elle est placee au milieu.
		this.panelFichePersonne = new JPanel();
		this.panelFichePersonne.setLayout (new GridLayout(2,1));
		this.personneScreen.add(BorderLayout.CENTER,panelFichePersonne);

		// Creation des labels et des champs de saisies de la fiche Personne.
		JPanel pc1 = new JPanel();
		pc1.setLayout (new GridLayout(4,1));
		this.panelFichePersonne.add(pc1);

		pc1.add(new JLabel("ID carnet :"));
		this.champPersonneIdCarnet = new JTextField(9);
		pc1.add(this.champPersonneIdCarnet);

		pc1.add(new JLabel("Nom :"));
		this.setChampNom(new JTextField(20));
		pc1.add(this.getChampNom());

		pc1.add(new JLabel("Email :"));
		this.champEmail = new JTextField(20);
		pc1.add(this.champEmail);

		pc1.add(new JLabel("URL :"));
		this.champUrl = new JTextField(20);
		pc1.add(this.champUrl);

		JPanel pc2 = new JPanel();
		pc2.setLayout (new GridLayout(1,2));
		this.panelFichePersonne.add(pc2);

		pc2.add(new JLabel("Informations :"));
		this.champInfo = new JTextArea(5,20);
		pc2.add(this.champInfo);
	}

	/**
	 * Initialisation de la zone de saisie d'une fiche d'un carnet
	 */
	private void initFicheCarnet() {

		this.panelFicheCarnet = new JPanel();
		this.panelFicheCarnet.setLayout (new GridLayout(2,1));
		this.carnetScreen.add(BorderLayout.CENTER, panelFicheCarnet);

		JPanel pc1 = new JPanel();
		pc1.setLayout (new GridLayout(1,1));
		this.panelFicheCarnet.add(pc1);

		pc1.add(new JLabel("ID carnet :"));
		this.champCarnetIdCarnet = new JTextField(9);
		pc1.add(this.champCarnetIdCarnet);

	}
	
	private void initFicheLogin() {
		this.panelFicheLogin = new JPanel();
		panelFicheLogin.setSize(100, 200);
		this.panelFicheLogin.setLayout(new FlowLayout());
		this.loginScreen.add(panelFicheLogin);
		
		JPanel pc1 = new JPanel();
		pc1.setLayout(new GridLayout(2,2));
		this.panelFicheLogin.add(pc1);
		
		pc1.add(new JLabel("Login : "));
		this.champLogin = new JTextField(14);
		champLogin.setSize(100, 20);
		pc1.add(champLogin);
		
		pc1.add(new JLabel("Password : "));
		this.champPassword = new JPasswordField(14);
		champPassword.setSize(100, 20);
		pc1.add(champPassword);
	}
	
	private void initBoutonsLogin() {
		this.loginBoutons = new JPanel();
		this.loginBoutons.setLayout(new GridLayout(2,1));
		this.panelFicheLogin.add(loginBoutons);
		
		ConnectionListener connectListener = new ConnectionListener(this);
		this.boutonloginConnection = new JButton("Se connecter");
		this.loginBoutons.add(boutonloginConnection);
		this.boutonloginConnection.addActionListener(connectListener);
		
		ViderListener viderListener = new ViderListener(this);
		this.boutonloginVider = new JButton("Reinitialiser");
		this.loginBoutons.add(boutonloginVider);
		this.boutonloginVider.addActionListener(viderListener);
	}

	/**
	 * Initialisation du tableau de bord interactif contenant les boutons
	 */
	private void initBoutonsPersonne() {

		this.personneBoutons = new JPanel();
		this.personneBoutons.setLayout (new GridLayout(6,1));
		personneScreen.add(BorderLayout.WEST,personneBoutons);

		ViderListener viderListener = new ViderListener(this);
		this.boutonPersonneVider = new JButton("Effacer la fiche");
		this.personneBoutons.add(this.boutonPersonneVider);
		this.boutonPersonneVider.addActionListener (viderListener);

		AjouterListener ajouterListener = new AjouterListener(this);
		this.boutonAjouterPersonne = new JButton("Ajouter une personne");
		this.personneBoutons.add(this.boutonAjouterPersonne);
		this.boutonAjouterPersonne.addActionListener (ajouterListener);

		ModifierListener modifierListener = new ModifierListener(this);
		this.boutonModifierPersonne = new JButton("Modifier une personne");
		this.personneBoutons.add(this.boutonModifierPersonne);
		this.boutonModifierPersonne.addActionListener (modifierListener);

		RetirerListener retirerListener = new RetirerListener(this);
		this.boutonRetirerPersonne = new JButton("Retirer une personne");
		this.personneBoutons.add(this.boutonRetirerPersonne);
		this.boutonRetirerPersonne.addActionListener (retirerListener);

		ChercherListener chercherListener = new ChercherListener(this);
		this.boutonChercherPersonne = new JButton("Chercher une personne");
		this.personneBoutons.add(this.boutonChercherPersonne);
		this.boutonChercherPersonne.addActionListener (chercherListener);

		ListerListener listerListener = new ListerListener(this);
		this.boutonListerPersonnes = new JButton("Lister les personnes");
		this.personneBoutons.add(this.boutonListerPersonnes);
		this.boutonListerPersonnes.addActionListener (listerListener);
	}

	/**
	 * Initialisation du tableau de bord interactif contenant les boutons
	 */
	private void initBoutonsCarnet() {

		this.carnetBoutons = new JPanel();
		this.carnetBoutons.setLayout (new GridLayout(6,1));
		carnetScreen.add(BorderLayout.WEST,carnetBoutons);

		ViderListener viderListener = new ViderListener(this);
		this.boutonCarnetVider = new JButton("Effacer la fiche");
		this.carnetBoutons.add(this.boutonCarnetVider);
		this.boutonCarnetVider.addActionListener (viderListener);

		AjouterListener ajouterListener = new AjouterListener(this);
		this.boutonAjouterCarnet = new JButton("Ajouter un carnet");
		this.carnetBoutons.add(this.boutonAjouterCarnet);
		this.boutonAjouterCarnet.addActionListener (ajouterListener);

		RetirerListener retirerListener = new RetirerListener(this);
		this.boutonRetirerCarnet = new JButton("Retirer un carnet");
		this.carnetBoutons.add(this.boutonRetirerCarnet);
		this.boutonRetirerCarnet.addActionListener (retirerListener);

		ChercherListener chercherListener = new ChercherListener(this);
		this.boutonChercherCarnet = new JButton("Chercher un carnet");
		this.carnetBoutons.add(this.boutonChercherCarnet);
		this.boutonChercherCarnet.addActionListener (chercherListener);

		ListerListener listerListener = new ListerListener(this);
		this.boutonListerCarnets = new JButton("Lister les carnets");
		this.carnetBoutons.add(this.boutonListerCarnets);
		this.boutonListerCarnets.addActionListener (listerListener);
	}

	/**
	 * Initialisation des parametres de la Fenetre d'affichage
	 */
	private void initIHMConfiguration() {
		this.setSize(WINDOWS_DIMENSION);
		this.setTitle(WINDOWS_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new FenetreListener(this));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(cl);

		this.onglet = new JTabbedPane(JTabbedPane.NORTH);

		this.personneScreen = new JPanel();
		// Ce panel est organise en 5 zones.
		this.personneScreen.setLayout (new BorderLayout());

		this.carnetScreen = new JPanel();
		this.carnetScreen.setLayout(new BorderLayout());
		
		this.loginScreen = new JPanel();
		this.loginScreen.setLayout(new BorderLayout());

		this.onglet.addTab("Gestion Personne", personneScreen);
		this.onglet.addTab("Gestion Carnets", carnetScreen);
		
		this.getContentPane().add(loginScreen, listContent[0]);
		this.getContentPane().add(onglet, listContent[1]);

		this.setVisible(true);
	}



	//******* METHODES ********

	/**
	 *  Fixer le message d'erreur.
	 *  Si message vide alors afficher le message par defaut.
	 */
	public void fixerErreur (String message) {
		if (message != null && message.length() != 0) {
			this.labelProblemePersonne.setText (message + " !");
			this.labelProblemeCarnet.setText(message + " !");
			this.labelProblemeLogin.setText(message + " !");
		} else {
			this.labelProblemePersonne.setText(MESSAGE_PROBLEME_DEFAULT);
			this.labelProblemeCarnet.setText(MESSAGE_PROBLEME_DEFAULT);
			this.labelProblemeLogin.setText(MESSAGE_PROBLEME_DEFAULT);
		}
	}

	public void closeConnection() {
		this.repertoireControler.closeMyConnection();
	}

	/** 
	 * Obtenir la reference sur le Repertoire.
	 * Si le repertoire n'est pas encore fixe alors fixer l'erreur.
	 */
	public Repertoire getRepertoire() {
		this.repertoireControler.getRepertoire();
		if ( datas.get(RepertoireModelImpl.GET_REPERTOIRE) != null ){
			return (Repertoire) datas.get(RepertoireModelImpl.GET_REPERTOIRE);
		}else{
			fixerErreur(MESSAGE_PROBLEME_REPERTOIRE_ABSENT);
			return null;
		}
	}

	/**
	 * Changer de repertoire
	 */
	public void fixerRepertoire(tp2.client.mvc.model.dao.impl.CarnetProxy repertoire) {
		this.repertoireControler.fixerRepertoire(repertoire);
		if(datas.get(RepertoireModelImpl.FIXER_REPERTOIRE).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS)) {
			this.listerPersonnes();
		}
	}

	/**
	 * Creer un objet Personne avec une Fabrique de personne a partir de la fiche saisie.
	 */
	public Personne creerPersonne () {
		this.repertoireControler.creerPersonne(getChampNom().getText(), champEmail.getText(), champUrl.getText(), champInfo.getText() );
		if ( datas.get(RepertoireModelImpl.CREER_PERSONNE) != null ){
			return (Personne) datas.get(RepertoireModelImpl.CREER_PERSONNE);
		}else{
			fixerErreur("Erreur creation d'une personne.");
			return null;
		}
	}

	/**
	 * Remplir la fiche Personne a partir des champs d'un objet Personne.
	 */
	public void remplirFichePersonne (Personne p) {
		this.getChampNom().setText(p.getNom());
		this.champEmail.setText(p.getEmail());
		this.champUrl.setText(p.getUrl());
		this.champInfo.setText(p.getInfo());
	}

	/**
	 * Remplir la fiche Carnet.
	 */
	public void remplirFicheCarnet (Carnet carnet) {
		this.champCarnetIdCarnet.setText(Integer.toString(carnet.getId()));
	}

	/**
	 * Lorsque le bouton Vider est selectionne alors cette methode
	 * remet a blanc les champs de la fiche Personne.
	 */
	public void viderFichePersonne () {
		System.out.println("IHM.viderFichePersonne()");
		this.champPersonneIdCarnet.setText("");
		this.getChampNom().setText("");
		this.champEmail.setText("");
		this.champUrl.setText("");
		this.champInfo.setText("");
	}

	/**
	 * Lorsque le bouton Vider est selectionne alors cette methode
	 * remet a blanc les champs de la fiche Carnet.
	 */
	public void viderFicheCarnet () {
		System.out.println("IHM.viderFicheCarnet()");
		this.champCarnetIdCarnet.setText("");
	}
	
	/**
	 * Lorsque le bouton Vider est selectionne alors cette methode
	 * remet a blanc les champs de la fiche login
	 */
	public void viderFicheLogin() {
		System.out.println("IHM.viderFicheLogin()");
		this.champLogin.setText("");
		this.champPassword.setText("");
	}

	/* OPERATION CRUD PERSONNE */

	/**
	 * Lorsque le bouton Ajouter est selectionne alors cette methode
	 * cree un objet Personne et l'ajoute au Repertoire.
	 */
	public void ajouterPersonne () {

		if (champPersonneIdCarnet.getText().isEmpty() || getChampNom().getText().isEmpty() || champEmail.getText().isEmpty() || champUrl.getText().isEmpty() || champInfo.getText().isEmpty()) {
			fixerErreur("Vous devez remplir les champs idCarnet, nom, email, url et description pour ajouter une personne.");
			return;
		}

		System.out.println("IHM.ajouterPersonne()");
		int idCarnet = Integer.valueOf(champPersonneIdCarnet.getText().trim());
		this.repertoireControler.ajouterPersonne( idCarnet, creerPersonne() );
		if ( datas.get(RepertoireModelImpl.AJOUTER_PERSONNE) != null && datas.get(RepertoireModelImpl.AJOUTER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS) ){
			listerPersonnes();
			viderFichePersonne();
			fixerErreur(null);
		} else if ( datas.get(RepertoireModelImpl.AJOUTER_PERSONNE) != null && datas.get(RepertoireModelImpl.AJOUTER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_ERROR) ){
			fixerErreur("Le carnet '"+idCarnet+"' n'existe pas");
		}  else {
			fixerErreur(MESSAGE_PERSONNE_EXISTE_DEJA);
		}
	}

	/**
	 * Lorsque le bouton Modifier est selectionne alors cette methode
	 * cree un objet Personne et remplace celle existante dans le Repertoire.
	 */
	public void modifierPersonne () {

		String nom = getChampNom().getText();
		String email = champEmail.getText();
		String url = champUrl.getText();
		String info = champInfo.getText();

		if (champPersonneIdCarnet.getText().isEmpty() || nom.isEmpty() || email.isEmpty() || url.isEmpty() || info.isEmpty()) {
			fixerErreur("Vous devez remplir les champs idCarnet, nom, email, url et description pour modifier une personne.");
			return;
		}

		int idCarnet = Integer.valueOf(champPersonneIdCarnet.getText().trim());

		System.out.println("IHM.modifierPersonne(idCarnet:"+idCarnet+", nom :" + nom + ")");
		this.repertoireControler.modifierPersonne(idCarnet, creerPersonne() );
		if ( datas.get(RepertoireModelImpl.MODIFIER_PERSONNE) != null && datas.get(RepertoireModelImpl.MODIFIER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS) ){
			fixerErreur(null);
		} else if ( datas.get(RepertoireModelImpl.MODIFIER_PERSONNE) != null && datas.get(RepertoireModelImpl.MODIFIER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_ERROR) ){
			fixerErreur("Le carnet '"+idCarnet+"' n'existe pas");
		} else {
			fixerErreur("La personne `" + nom + "' n'existe pas");
		}
	}

	/**
	 * Lorsque le bouton Retirer est selectionne alors cette methode
	 * retire une Personne du Repertoire.
	 */
	public void retirerPersonne (String nomPersonne) {

		if (champPersonneIdCarnet.getText().isEmpty() || nomPersonne == null || nomPersonne.isEmpty()) {
			fixerErreur("Vous devez remplir le champ idCarnet et nom pour retirer une personne.");
			return;
		}

		int idCarnet = Integer.valueOf(champPersonneIdCarnet.getText().trim());

		System.out.println("IHM.retirerPersonne(idCarnet:"+idCarnet+", nom :" + nomPersonne + ")");
		this.repertoireControler.retirerPersonne(idCarnet, nomPersonne);
		if ( datas.get(RepertoireModelImpl.RETIRER_PERSONNE) != null && datas.get(RepertoireModelImpl.RETIRER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS) ){
			listerPersonnes ();
			fixerErreur(null);
		} else if ( datas.get(RepertoireModelImpl.RETIRER_PERSONNE) != null && datas.get(RepertoireModelImpl.RETIRER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_ERROR) ){
			fixerErreur("Le carnet '"+idCarnet+"' n'existe pas");
		}else{
			fixerErreur("La personne `" + nomPersonne + "' n'existe pas");
		}
	}

	/**
	 * Lorsque le bouton Chercher est selectionne alors cette methode
	 * cherche une Personne dans le Repertoire.
	 */
	public void chercherPersonne (String nomPersonne) {

		if (champPersonneIdCarnet.getText().isEmpty() || nomPersonne == null || nomPersonne.isEmpty()) {
			fixerErreur("Vous devez remplir le champ idCarnet et nom pour chercher une personne.");
			return;
		}

		int idCarnet = Integer.valueOf(champPersonneIdCarnet.getText().trim());

		System.out.println("IHM.chercherPersonne(idCarnet:"+idCarnet+", nom :" + nomPersonne + ")");
		this.repertoireControler.chercherPersonne(idCarnet, nomPersonne );

		if ( datas.get(RepertoireModelImpl.CHERCHER_PERSONNE) != null ) {
			if(datas.get(RepertoireModelImpl.CHERCHER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_ERROR)){
				fixerErreur("Le carnet '"+idCarnet+"' n'existe pas");
			}else{
				remplirFichePersonne ((Personne) datas.get(RepertoireModelImpl.CHERCHER_PERSONNE));
				fixerErreur(null);
			}
		}else{
			fixerErreur("La personne `" + nomPersonne + "' n'existe pas");
		}
	}

	/**
	 * Lorsque le bouton Lister est selectionne alors cette methode
	 * met a jour la liste des noms des personnes du repertoire courant.
	 */
	public void listerPersonnes () {

		if (champPersonneIdCarnet.getText().isEmpty() ) {
			fixerErreur("Vous devez remplir le champ id Carnet pour lister les personne d'un carnet.");
			return;
		}

		int idCarnet = Integer.valueOf(champPersonneIdCarnet.getText().trim());

		System.out.println("IHM.listerPersonnes(idCarnet : "+idCarnet+")");
		this.repertoireControler.listerPersonnes(idCarnet);

		if(datas.get(RepertoireModelImpl.LISTER_PERSONNES) != null) {
			if(datas.get(RepertoireModelImpl.LISTER_PERSONNES).equals(ClientProtocole.MESSAGE_ORDER_ERROR)){
				fixerErreur("Le carnet '"+idCarnet+"' n'existe pas");
			}else{
				getListePersonnes().removeAll();
				String[] noms = (String[]) datas.get(RepertoireModelImpl.LISTER_PERSONNES);
				getListePersonnes().setListData(noms);
				fixerErreur(null);
			}
		}else{
			DefaultListModel modelList = new DefaultListModel();
			modelList.addElement("");
			getListePersonnes().setModel(modelList);
			fixerErreur("Aucune personnes dans le repertoire disponible");
		}
	}

	/* OPERATION CRUD CARNET */

	/**
	 * Lorsque le bouton Ajouter est selectionne alors cette methode
	 * cree un objet Carnet sur le serveur et l'ajoute en Base de donnee.
	 */
	public void ajouterCarnet() {

		System.out.println("IHM.ajouterCarnet()");
		this.repertoireControler.ajouterCarnet();
		if ( datas.get(RepertoireModelImpl.AJOUTER_CARNET) != null && datas.get(RepertoireModelImpl.AJOUTER_CARNET).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS) ){
			listerCarnets();
			viderFicheCarnet();
			fixerErreur(null);
		} else {
			fixerErreur(MESSAGE_CARNET_ERROR_CREATION);
		}

	}

	/**
	 * Lorsque le bouton Chercher est selectionne alors cette methode
	 * cherche un carnet dans la base de donnée.
	 */
	public void chercherCarnet(String idCarnet) {

		if (idCarnet == null || idCarnet.isEmpty()) {
			fixerErreur("Vous devez remplir le champ idCarnet pour chercher un carnet.");
			return;
		}

		System.out.println("IHM.chercherPersonne(idCarnet:"+idCarnet+")");
		this.repertoireControler.chercherCarnet(idCarnet);

		if ( datas.get(RepertoireModelImpl.CHERCHER_CARNET) != null ) {
			remplirFicheCarnet ((Carnet) datas.get(RepertoireModelImpl.CHERCHER_CARNET));
			fixerErreur(null);
		}else{
			fixerErreur("Le carnet `" + idCarnet + "' n'existe pas");
		}
	}

	/**
	 * Lorsque le bouton Retirer est selectionne alors cette methode
	 * retire un carnet de la base de donnée.
	 */
	public void retirerCarnet(String idCarnet) {

		if (champCarnetIdCarnet.getText().isEmpty()) {
			fixerErreur("Vous devez remplir le champ idCarnet pour retirer un carnet.");
			return;
		}

		System.out.println("IHM.retirerPersonne(idCarnet:"+idCarnet+")");
		this.repertoireControler.retirerCarnet(idCarnet);
		if ( datas.get(RepertoireModelImpl.RETIRER_CARNET) != null && datas.get(RepertoireModelImpl.RETIRER_CARNET).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS) ){
			listerCarnets();
			fixerErreur(null);
		}else{
			fixerErreur("Le carnet `" + idCarnet + "' n'existe pas");
		}
	}

	/**
	 * Lorsque le bouton Lister est selectionne alors cette methode
	 * met a jour la liste des noms des carnets en base de donnée disponibles.
	 */
	public void listerCarnets() {

		System.out.println("IHM.listerCarnets()");
		this.repertoireControler.listerCarnets();

		if(datas.get(RepertoireModelImpl.LISTER_CARNETS) != null) {
			getListeCarnets().removeAll();
			String[] identifiants = (String[]) datas.get(RepertoireModelImpl.LISTER_CARNETS);
			getListeCarnets().setListData(identifiants);
			fixerErreur(null);
		}else{
			DefaultListModel modelList = new DefaultListModel();
			modelList.addElement("");
			getListeCarnets().setModel(modelList);
			fixerErreur("Aucuns carnets dans la base de donnée disponible");
		}
	}
	
	public void seConnecter() {
		
		if (champLogin.getText().isEmpty() || champPassword.getText().isEmpty()) {
			fixerErreur("Vous devez remplir les champs login, password pour vous connecter.");
			return;
		}

		System.out.println("IHM.seConnecter()");
		
		String login = champLogin.getText().trim();
		String password = champPassword.getText().trim();
		
		this.repertoireControler.seConnecter( login, password );
		if ( datas.get(RepertoireModelImpl.SE_CONNECTER) != null && datas.get(RepertoireModelImpl.SE_CONNECTER).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS) ){
			cl.show(this.getContentPane(), listContent[1]);
			fixerErreur(null);
		}  else {
			fixerErreur(MESSAGE_ERROR_LOGIN);
		}
	}

	//******* IMPLEMENTATION DU PATTERN OBSERVER ******
	@Override
	public void update(Map<String, Object> modelMap) {

		// GET REPERTOIRE
		if (modelMap.get(RepertoireModelImpl.GET_REPERTOIRE) != null && !modelMap.get(RepertoireModelImpl.GET_REPERTOIRE).equals(datas.get(RepertoireModelImpl.GET_REPERTOIRE) )) {
			datas.put(RepertoireModelImpl.GET_REPERTOIRE, (Repertoire) modelMap.get(RepertoireModelImpl.GET_REPERTOIRE));
		}

		// CHANGER DE REPERTOIRE
		if(modelMap.get(RepertoireModelImpl.FIXER_REPERTOIRE) != null && !modelMap.get(RepertoireModelImpl.FIXER_REPERTOIRE).equals(datas.get(RepertoireModelImpl.FIXER_REPERTOIRE) )) {
			if (modelMap.get(RepertoireModelImpl.FIXER_REPERTOIRE).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS)) {
				datas.put(RepertoireModelImpl.FIXER_REPERTOIRE, modelMap.get(RepertoireModelImpl.FIXER_REPERTOIRE));
			}else{
				datas.put(RepertoireModelImpl.FIXER_REPERTOIRE, ClientProtocole.MESSAGE_ORDER_FAILED);
			}
		}

		// CREER UNE PERSONNE
		if(modelMap.get(RepertoireModelImpl.CREER_PERSONNE) != null && !modelMap.get(RepertoireModelImpl.CREER_PERSONNE).equals(datas.get(RepertoireModelImpl.CREER_PERSONNE) )) {
			datas.put(RepertoireModelImpl.CREER_PERSONNE, (Personne) modelMap.get(RepertoireModelImpl.CREER_PERSONNE));
		}

		// AJOUTER UNE PERSONNE
		if(modelMap.get(RepertoireModelImpl.AJOUTER_PERSONNE) != null ) {
			if (modelMap.get(RepertoireModelImpl.AJOUTER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS)) {
				datas.put(RepertoireModelImpl.AJOUTER_PERSONNE, modelMap.get(RepertoireModelImpl.AJOUTER_PERSONNE));
			}else if(modelMap.get(RepertoireModelImpl.AJOUTER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_ERROR)){
				datas.put(RepertoireModelImpl.AJOUTER_PERSONNE, ClientProtocole.MESSAGE_ORDER_ERROR);
			}else{
				datas.put(RepertoireModelImpl.AJOUTER_PERSONNE, ClientProtocole.MESSAGE_ORDER_FAILED);
			}
		}

		// MODIFER UNE PERSONNE
		if (modelMap.get(RepertoireModelImpl.MODIFIER_PERSONNE) != null) {
			if (modelMap.get(RepertoireModelImpl.MODIFIER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS)) {
				datas.put(RepertoireModelImpl.MODIFIER_PERSONNE, modelMap.get(RepertoireModelImpl.MODIFIER_PERSONNE));
			}else if(modelMap.get(RepertoireModelImpl.MODIFIER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_ERROR)){
				datas.put(RepertoireModelImpl.MODIFIER_PERSONNE, ClientProtocole.MESSAGE_ORDER_ERROR);
			}else{
				datas.put(RepertoireModelImpl.MODIFIER_PERSONNE, ClientProtocole.MESSAGE_ORDER_FAILED);
			}
		}

		// SUPPRIMER UNE PERSONNE
		if (modelMap.get(RepertoireModelImpl.RETIRER_PERSONNE) != null) {
			if (modelMap.get(RepertoireModelImpl.RETIRER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS)) {
				datas.put(RepertoireModelImpl.RETIRER_PERSONNE, modelMap.get(RepertoireModelImpl.RETIRER_PERSONNE));
			}else if(modelMap.get(RepertoireModelImpl.RETIRER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_ERROR)){
				datas.put(RepertoireModelImpl.RETIRER_PERSONNE, ClientProtocole.MESSAGE_ORDER_ERROR);
			}else{
				datas.put(RepertoireModelImpl.RETIRER_PERSONNE, ClientProtocole.MESSAGE_ORDER_FAILED);
			}
		}

		// RECHERCHER UNE PERSONNE
		if (modelMap.get(RepertoireModelImpl.CHERCHER_PERSONNE) != null) {
			if(modelMap.get(RepertoireModelImpl.CHERCHER_PERSONNE).equals(ClientProtocole.MESSAGE_ORDER_ERROR)){
				datas.put(RepertoireModelImpl.CHERCHER_PERSONNE, (String) modelMap.get(RepertoireModelImpl.CHERCHER_PERSONNE));
			}else{
				datas.put(RepertoireModelImpl.CHERCHER_PERSONNE, (Personne) modelMap.get(RepertoireModelImpl.CHERCHER_PERSONNE));
			}
		}else{
			datas.put(RepertoireModelImpl.CHERCHER_PERSONNE, null);
		}

		// LISTER LES PERSONNES
		if (modelMap.get(RepertoireModelImpl.LISTER_PERSONNES) != null) {
			if(modelMap.get(RepertoireModelImpl.LISTER_PERSONNES).equals(ClientProtocole.MESSAGE_ORDER_ERROR)){
				datas.put(RepertoireModelImpl.LISTER_PERSONNES, ClientProtocole.MESSAGE_ORDER_ERROR);
			}else{
				datas.put(RepertoireModelImpl.LISTER_PERSONNES, (String[]) modelMap.get(RepertoireModelImpl.LISTER_PERSONNES));
			}
		}else{
			datas.put(RepertoireModelImpl.LISTER_PERSONNES, null);
		}

		// AJOUTER UN CARNET
		if(modelMap.get(RepertoireModelImpl.AJOUTER_CARNET) != null ) {
			if (modelMap.get(RepertoireModelImpl.AJOUTER_CARNET).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS)) {
				datas.put(RepertoireModelImpl.AJOUTER_CARNET, modelMap.get(RepertoireModelImpl.AJOUTER_CARNET));
			}else{
				datas.put(RepertoireModelImpl.AJOUTER_CARNET, ClientProtocole.MESSAGE_ORDER_FAILED);
			}
		}

		// SUPPRIMER UN CARNET
		if (modelMap.get(RepertoireModelImpl.RETIRER_CARNET) != null) {
			if (modelMap.get(RepertoireModelImpl.RETIRER_CARNET).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS)) {
				datas.put(RepertoireModelImpl.RETIRER_CARNET, modelMap.get(RepertoireModelImpl.RETIRER_CARNET));
			}else if(modelMap.get(RepertoireModelImpl.RETIRER_CARNET).equals(ClientProtocole.MESSAGE_ORDER_ERROR)){
				datas.put(RepertoireModelImpl.RETIRER_CARNET, ClientProtocole.MESSAGE_ORDER_ERROR);
			}else{
				datas.put(RepertoireModelImpl.RETIRER_CARNET, ClientProtocole.MESSAGE_ORDER_FAILED);
			}
		}

		// RECHERCHER UN CARNET
		if (modelMap.get(RepertoireModelImpl.CHERCHER_CARNET) != null) {
			if(modelMap.get(RepertoireModelImpl.CHERCHER_CARNET).equals(ClientProtocole.MESSAGE_ORDER_ERROR)){
				datas.put(RepertoireModelImpl.CHERCHER_CARNET, (String) modelMap.get(RepertoireModelImpl.CHERCHER_CARNET));
			}else{
				datas.put(RepertoireModelImpl.CHERCHER_CARNET, (Carnet) modelMap.get(RepertoireModelImpl.CHERCHER_CARNET));
			}
		}else{
			datas.put(RepertoireModelImpl.CHERCHER_CARNET, null);
		}

		// LISTER LES CARNETS
		if (modelMap.get(RepertoireModelImpl.LISTER_CARNETS) != null) {
			datas.put(RepertoireModelImpl.LISTER_CARNETS, (String[]) modelMap.get(RepertoireModelImpl.LISTER_CARNETS));
		}else{
			datas.put(RepertoireModelImpl.LISTER_CARNETS, null);
		}
		
		// SE CONNECTER
		if (modelMap.get(RepertoireModelImpl.SE_CONNECTER) != null) {
			if (modelMap.get(RepertoireModelImpl.SE_CONNECTER).equals(ClientProtocole.MESSAGE_ORDER_SUCCESS)) {
				datas.put(RepertoireModelImpl.SE_CONNECTER, modelMap.get(RepertoireModelImpl.SE_CONNECTER));
			}else if(modelMap.get(RepertoireModelImpl.SE_CONNECTER).equals(ClientProtocole.MESSAGE_ORDER_ERROR)){
				datas.put(RepertoireModelImpl.SE_CONNECTER, ClientProtocole.MESSAGE_ORDER_ERROR);
			}else{
				datas.put(RepertoireModelImpl.SE_CONNECTER, ClientProtocole.MESSAGE_ORDER_FAILED);
			}
		}

	}

	/* GETTERS / SETTERS */

	/**
	 * @return the champNom
	 */
	public JTextField getChampNom() {
		return champNom;
	}

	/**
	 * @param champNom the champNom to set
	 */
	public void setChampNom(JTextField champNom) {
		this.champNom = champNom;
	}

	/**
	 * @return the listePersonnes
	 */
	public JList getListePersonnes() {
		return listePersonnes;
	}

	public JList getListeCarnets() {
		return listeCarnets;
	}

	public void setListeCarnets(JList listeCarnets) {
		this.listeCarnets = listeCarnets;
	}

	/**
	 * @param listePersonnes the listePersonnes to set
	 */
	public void setListePersonnes(JList listePersonnes) {
		this.listePersonnes = listePersonnes;
	}

	public JTextField getChampIdCarnet() {
		return champCarnetIdCarnet;
	}

}

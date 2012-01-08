import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.util.Observable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
/**
 * Fenêtre principale de l'application Bibliothèque avec le menu
 * @author IUT,  A.Culet
 * @version 1.0
 */
public class VueMenuBiblio  extends Vue{
    
	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JTextField textFieldBdd;

    public VueMenuBiblio(Controleur controleur) {
        super (controleur);
        this.initialize();
    }
    
    private void initialize() {
    	content = new JPanel();
    	getFrame().setTitle("Gestion de bibliothèque");
        getFrame().setBounds(100, 100, 550, 250);
        getFrame().setContentPane(content);
        getFrame().getContentPane().setLayout(null);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 550, 22);
       
        // Menu Application
        JMenu mnApplication = new JMenu("Application");
        mnApplication.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(mnApplication);
        
        JMenuItem mntmSauvegarder = new JMenuItem("Enregistrer");
        mntmSauvegarder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        mntmSauvegarder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getControleur().sauve();
            }
        });
        mnApplication.add(mntmSauvegarder);
        
        JMenuItem mntmSlectionnerLaBase = new JMenuItem("Ouvrir");
        mntmSlectionnerLaBase.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        mntmSlectionnerLaBase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String text = textFieldBdd.getText();
            	if (text.isEmpty())
            		Message.message("Le nom de fichier est vide", Controleur.attention);
            	else
            		getControleur().ouvrir(text);
            }
        });
        mnApplication.add(mntmSlectionnerLaBase);
        
        JMenuItem mntmSupprimerLaBase = new JMenuItem("Supprimer la base");
        mntmSupprimerLaBase.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_MASK));
        mntmSupprimerLaBase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getControleur().supprimerBase();
            }
        });
        mnApplication.add(mntmSupprimerLaBase);
        
        JMenuItem menuItemQuitter = new JMenuItem("Quitter");
        menuItemQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        menuItemQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getControleur().quitter(VueMenuBiblio.this);
            }
        });
        mnApplication.add(menuItemQuitter);
        
        // Menu ouvrage
        JMenu mnOuvrage = new JMenu("Ouvrage");
        menuBar.add(mnOuvrage);
       
        JMenuItem MenuItemOuv = new JMenuItem("Nouvel ouvrage");
        MenuItemOuv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //  affichage de la fenetre de saisie d'un ouvrage
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur().saisirOuvrage() ;
            }
        });
        mnOuvrage.add(MenuItemOuv);

        JMenuItem MenuItemExemp = new JMenuItem("Nouvel exemplaire");
        MenuItemExemp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //  affichage de la fenetre de saisie d'un exemplaire
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur().saisirExemplaire() ;}
        });
        mnOuvrage.add(MenuItemExemp);
       
        JMenuItem menuItemConsult = new JMenuItem("Consulter ouvrage");
        menuItemConsult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de consultation d'un ouvrage
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). consulterOuvrage() ;}
        });
        mnOuvrage.add(menuItemConsult);
       
       
        // Menu periodiques
        JMenu mnPeriodiques = new JMenu("Periodiques");
        menuBar.add(mnPeriodiques);
        
        JMenuItem menuItemConsultPerio = new JMenuItem("Consulter Periodique");
        menuItemConsultPerio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de consultation d'un periodique
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). consulterPeriodique() ;}
        });
        mnPeriodiques.add(menuItemConsultPerio);

        JMenuItem menuItemNewPerio = new JMenuItem("Nouveau Periodique");
        menuItemNewPerio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de saisie d'un periodique
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). saisiePeriodique() ;}
        });
        mnPeriodiques.add(menuItemNewPerio);
        
        // Menu parutions
        JMenu mnParutions = new JMenu("Parutions");
        menuBar.add(mnParutions);
        
        JMenuItem menuItemNewArticle = new JMenuItem("Nouvel Article");
        menuItemNewArticle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de saisie d'un periodique
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). saisieArticle() ;}
        });
        mnParutions.add(menuItemNewArticle);
        
        
        JMenuItem menuItemNewParu = new JMenuItem("Nouvelle Parution");
        menuItemNewParu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de saisie d'un periodique
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). saisirParution() ;}
        });
        mnParutions.add(menuItemNewParu);
        
        // Menu recherches
        JMenu mnRecherches = new JMenu("Recherches");
        menuBar.add(mnRecherches);        
        
        JMenuItem menuItemRechAut = new JMenuItem("Recherche par Auteur");
        menuItemRechAut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de saisie d'un periodique
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). rechAuteur() ;}
        });
        mnRecherches.add(menuItemRechAut);
        
        
        JMenuItem menuItemRechMot = new JMenuItem("Recherche par Mots Clés");
        menuItemRechMot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de saisie d'un periodique
                getControleur().rechTitre() ;
            }
        });
        mnRecherches.add(menuItemRechMot);
        
        JMenuItem mntmRechercheParTitre = new JMenuItem("Recherche par Titre");
        mntmRechercheParTitre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de saisie d'un periodique
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). rechTitre() ;
            }
        });
        mnRecherches.add(mntmRechercheParTitre);
        
        content.add(menuBar);
        
        // Fichier chargé
        
        textFieldBdd = new JTextField();
        textFieldBdd.setBounds(153, 92, 134, 28);
        content.add(textFieldBdd);
        textFieldBdd.setColumns(10);
        
        JLabel lblBaseDeDonne = new JLabel("Base de donnée :");
        lblBaseDeDonne.setBounds(15, 98, 126, 16);
        content.add(lblBaseDeDonne);
        
        JButton btnOuvrir = new JButton("Ouvrir");
        btnOuvrir.setBounds(314, 92, 117, 29);
        btnOuvrir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String text = textFieldBdd.getText();
        		if (text.isEmpty()) 
        			Message.message("Le nom de fichier est vide", Controleur.attention);
        		else
        			getControleur().ouvrir(text);
        	}
        });
        content.add(btnOuvrir);
        
        getFrame().setVisible(true);
    }
    
    public void alimente(String fichier) {
    	textFieldBdd.setText(fichier);
    }
    
    public void update(Observable observable, Object objet) {
		// maj de la vue lorque l'ouvrage a été modifié
		this.alimente(this.getControleur().getFileName());
		super.update(observable,  objet);
	}
}
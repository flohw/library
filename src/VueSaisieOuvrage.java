import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;
import java.util.HashSet;
import javax.swing.JList;

/**
 * Fenêtre de saisie d'un ouvrage 
 * Code du JFrame généré par Window Builder/Swing Designer.
 * @author IUT,  A.Culet 
 * @version 1.0
 */
public class VueSaisieOuvrage extends Vue {

	private static final long serialVersionUID = 1L;

	private JPanel content;
	
	private JLabel lblISBN;
	private JLabel lblDateEdition;
	private JLabel lblEditeur;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblMotsclefs;
	private JLabel lblTitre;
	
	private JTextField textFieldTitre;
	private JTextField textFieldIsbn;
	private JTextField textFieldDateEd;
	private JTextField textFieldEditeur;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	
	private JButton btnEnregistrerOuvrage;
	private JButton btnAnnulerOuvrage;
	private JButton btnAnnulerAuteur;
	private JButton btnNouvelAuteur;
	private JButton btnTerminer;
	private JButton btnCS;
	private JButton btnSC;
	
	private JList listSource;
	private JList listCible;
	private JScrollPane scrollSource;
	private JScrollPane scrollCible;
	private DefaultListModel modeleSource = new DefaultListModel();
	private DefaultListModel modeleCible = new DefaultListModel();
	
	private JSeparator separator;
	private JSeparator separator_1;
	
	private Ouvrage _ouvrage;
	private HashSet<Auteur> _auteurs = new HashSet<Auteur>();
	private HashSet<String> _motsCles = new HashSet<String>();
		
	
	/**
	 * Create the frame.
	 */
	public VueSaisieOuvrage(Controleur controleur) {
		super(controleur);
		content = new JPanel();
		getFrame().setBounds(100, 100, 717, 578);
		getFrame().setContentPane(content);
		
		lblTitre = new JLabel("Titre");
		lblTitre.setBounds(116, 9, 117, 15);
		textFieldTitre = new JTextField();
		textFieldTitre.setText("Harry Potter");
		textFieldTitre.setBounds(364, 7, 323, 19);
		textFieldTitre.setColumns(10);
		
		lblISBN = new JLabel("Isbn");
		lblISBN.setBounds(116, 35, 117, 15);
		textFieldIsbn = new JTextField();
		textFieldIsbn.setText("1");
		textFieldIsbn.setBounds(364, 33, 323, 19);
		textFieldIsbn.setColumns(10);
		
		lblDateEdition = new JLabel("Date d'édition");
		lblDateEdition.setBounds(116, 61, 117, 15);
		textFieldDateEd = new JTextField();
		textFieldDateEd.setBounds(364, 59, 323, 19);
		textFieldDateEd.setText("12/1999");
		textFieldDateEd.setColumns(10);
		
		lblEditeur = new JLabel("Editeur");
		lblEditeur.setBounds(116, 87, 117, 15);
		textFieldEditeur = new JTextField();
		textFieldEditeur.setBounds(364, 85, 323, 19);
		textFieldEditeur.setText("Gallimard");
		textFieldEditeur.setColumns(10);
		
		btnEnregistrerOuvrage = new JButton("Enregistrer");
		btnEnregistrerOuvrage.setBounds(206, 116, 123, 25);
		btnEnregistrerOuvrage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String _isbn = textFieldIsbn.getText();
				String _titre = textFieldTitre.getText();
				String _editeur = textFieldEditeur.getText();
				GregorianCalendar _dateEd = ESDate.lireDate (textFieldDateEd.getText());
				if ((_isbn.length() == 0) || (_titre.length() == 0) || (_editeur.length() == 0))
				{
					Message dialog = new Message("Un des champs est vide");
					dialog.setVisible(true);
				}
				else if (_dateEd == null)
				{
					Message dialog = new Message("La date est incorrecte");
					dialog.setVisible(true);
				}
				else
					setOuvrage(getControleur().rechOuvrage(_isbn, _titre, _editeur, _dateEd));
			}
		});
		btnAnnulerOuvrage = new JButton("Annuler");
		btnAnnulerOuvrage.setBounds(419, 116, 83, 25);
		btnAnnulerOuvrage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueSaisieOuvrage.this);
				getControleur().menuBiblio();
			}
		});
		
		separator = new JSeparator();
		separator.setBounds(0, 148, 706, 2);
		
		lblNom = new JLabel("Nom de l'auteur");
		lblNom.setBounds(116, 159, 117, 15);
		lblNom.setEnabled(false);
		
		textFieldNom = new JTextField();
		textFieldNom.setText("Rowlings");
		textFieldNom.setBounds(339, 168, 323, 19);
		textFieldNom.setEnabled(false);
		textFieldNom.setColumns(10);
		
		lblPrenom = new JLabel("Prénom de l'auteur");
		lblPrenom.setBounds(116, 194, 117, 15);
		lblPrenom.setEnabled(false);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setText("J.K.");
		textFieldPrenom.setBounds(339, 194, 323, 19);
		textFieldPrenom.setEnabled(false);
		textFieldPrenom.setColumns(10);
		
		btnNouvelAuteur = new JButton("Nouvel Auteur");
		btnNouvelAuteur.setBounds(184, 221, 123, 25);
		btnNouvelAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Action enregistrer auteur
				String nom = textFieldNom.getText();
				String prenom = textFieldPrenom.getText();
				if (nom.length() != 0 && prenom.length() != 0) {
					Auteur auteur = new Auteur(nom, prenom);
					getControleur().auteurExiste(auteur, _auteurs);
				} else {
					Message dialog = new Message("Un des champs est vide");
					dialog.setVisible(true);
				}
			}
		});
		btnNouvelAuteur.setEnabled(false);
		
		btnAnnulerAuteur = new JButton("Annuler");
		btnAnnulerAuteur.setBounds(463, 221, 83, 25);
		btnAnnulerAuteur.setEnabled(false);
		btnAnnulerAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions passer de auteur à ouvrage
				setEtat(Vue.initiale);
			}
		});
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 258, 714, 2);
		
		
		lblMotsclefs = new JLabel("Mots-Clefs");
		lblMotsclefs.setBounds(310, 273, 117, 15);
		lblMotsclefs.setEnabled(false);
		
		listSource = new JList(modeleSource);
		listSource.setValueIsAdjusting(true);
		listSource.setEnabled(false);
		listSource.setBounds(108, 304, 199, 161);
		for(String item : getControleur().lectureLignesFichier())
			modeleSource.addElement(item);
		scrollSource = new JScrollPane(listSource);
		scrollSource.setBounds(listSource.getBounds());
		scrollSource.setSize(listSource.getSize());
		
		listCible = new JList(modeleCible);
		listCible.setEnabled(false);
		scrollCible = new JScrollPane(listCible);
		scrollCible.setBounds(413, 304, 199, 161);
		
		btnCS = new JButton("<<");
		btnCS.setEnabled(false);
		btnCS.setBounds(334, 328, 54, 25);
		btnCS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions retirer mot cle
				int [] indexes = listCible.getSelectedIndices();
				HashSet<String> elems = new HashSet<String>();
				for (int index : indexes) {
					String elem = modeleCible.getElementAt(index).toString();
					modeleSource.addElement(elem);
					elems.add(elem);
					if (!btnSC.isEnabled())
						btnSC.setEnabled(true);
				}
				for (String elem : elems)
					modeleCible.removeElement(elem);
				if (modeleCible.isEmpty())
					btnCS.setEnabled(false);
			}
		});
		
		btnSC = new JButton(">>");
		btnSC.setEnabled(false);
		btnSC.setBounds(334, 409, 54, 25);
		btnSC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions ajouter mot cle
				int [] indexes = listSource.getSelectedIndices();
				HashSet<String> elems = new HashSet<String>();
				for (int index : indexes) {
					String elem = modeleSource.getElementAt(index).toString();
					modeleCible.addElement(elem);
					elems.add(elem);
					if (!btnCS.isEnabled())
						btnCS.setEnabled(true);
				}
				for (String elem : elems)
					modeleSource.removeElement(elem);
				if (modeleSource.isEmpty())
					btnSC.setEnabled(false);
			}
		});
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setBounds(291, 489, 123, 25);
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions enregistrer mot cle puis terminer
				for (int index = 0; index < modeleCible.size(); index++)
					_motsCles.add(modeleCible.get(index).toString());
				
				if (modeleSource.isEmpty()) {
					Message dialog = new Message("Ajoutez au moins un mot clé");
					dialog.setVisible(true);
				}
				else
					getControleur().nouvOuvrage(getOuvrage(), _auteurs, _motsCles);
			}
		});
		btnTerminer.setEnabled(false);
		
		content.add(scrollSource);
		content.add(scrollCible);
		content.setLayout(null);
		content.add(lblTitre);
		content.add(textFieldTitre);
		content.add(lblISBN);
		content.add(textFieldIsbn);
		content.add(lblDateEdition);
		content.add(textFieldDateEd);
		content.add(lblEditeur);
		content.add(textFieldEditeur);
		content.add(btnEnregistrerOuvrage);
		content.add(btnAnnulerOuvrage);
		content.add(separator);
		content.add(lblNom);
		content.add(separator_1);
		content.add(btnTerminer);
		content.add(lblMotsclefs);
		content.add(lblPrenom);
		content.add(btnNouvelAuteur);
		content.add(btnAnnulerAuteur);
		content.add(textFieldNom);
		content.add(textFieldPrenom);
		content.add(btnSC);
		content.add(btnCS);
		content.add(scrollSource);
		content.add(scrollCible);
		getFrame().setVisible(true);
	}
	
	private void setOuvrage(Ouvrage ouv) { _ouvrage = ouv; }
	public Ouvrage getOuvrage() { return _ouvrage; }
	
	public void setEtat (int etat){
		super.setEtat(etat);
		switch (etat) {
		case initiale: {
			btnEnregistrerOuvrage.setEnabled(true);
			textFieldTitre.setEnabled(true);
			textFieldIsbn.setEnabled(true);
			textFieldDateEd.setEnabled(true);
			textFieldEditeur.setEnabled(true);
			btnAnnulerOuvrage.setEnabled(true);
			lblTitre.setEnabled(true);
			lblISBN.setEnabled(true);
			lblDateEdition.setEnabled(true);
			lblEditeur.setEnabled(true);
			
			btnAnnulerAuteur.setEnabled(false);
			btnNouvelAuteur.setEnabled(false);
			textFieldNom.setEnabled(false);
			textFieldPrenom.setEnabled(false);
			lblPrenom.setEnabled(false);
			lblNom.setEnabled(false);
			
			lblMotsclefs.setEnabled(false);
			btnTerminer.setEnabled(false);
			if (!modeleCible.isEmpty())
				btnCS.setEnabled(false);
			btnSC.setEnabled(false);
			listSource.setEnabled(false);
			listCible.setEnabled(false);
			break;
			}
		case inter1: {
			btnEnregistrerOuvrage.setEnabled(false);
			textFieldTitre.setEnabled(false);
			textFieldIsbn.setEnabled(false);
			textFieldDateEd.setEnabled(false);
			textFieldEditeur.setEnabled(false);
			btnAnnulerOuvrage.setEnabled(false);
			lblTitre.setEnabled(false);
			lblISBN.setEnabled(false);
			lblDateEdition.setEnabled(false);
			lblEditeur.setEnabled(false);
			
			btnAnnulerAuteur.setEnabled(true);
			btnNouvelAuteur.setEnabled(true);
			textFieldNom.setEnabled(true);
			textFieldPrenom.setEnabled(true);
			lblPrenom.setEnabled(true);
			lblNom.setEnabled(true);
			
			if (!_auteurs.isEmpty())
			{
				lblMotsclefs.setEnabled(true);
				btnTerminer.setEnabled(true);
				if (!modeleCible.isEmpty())
					btnCS.setEnabled(true);
				btnSC.setEnabled(true);
				listSource.setEnabled(true);
				listCible.setEnabled(true);
			}
			break;
			}
		case inter2: {
			textFieldNom.setText("");
			textFieldPrenom.setText("");
			lblMotsclefs.setEnabled(true);
			btnTerminer.setEnabled(true);
			if (!modeleCible.isEmpty())
				btnCS.setEnabled(true);
			btnSC.setEnabled(true);
			listSource.setEnabled(true);
			listCible.setEnabled(true);
			break;
		}
		case finale: {
			lblMotsclefs.setEnabled(false);
			btnTerminer.setEnabled(false);
			btnCS.setEnabled(false);
			btnSC.setEnabled(false);
			listSource.setEnabled(false);
			listCible.setEnabled(false);
			break;
		}
		}
	}
}

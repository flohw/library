import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Observable;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class VueNouvelArticle extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JTextField textFieldPage, textFieldNom, textFieldPrenom, textFieldTitre;
	
	private JButton btnAnnulerAut, btnNouvelAut, btnFermer, buttonSC, btnAnnulerPa,
					buttonCS, btnAnnulerArt, btnEnregistrerArt, btnRechPe, btnRechPa, btnTerminer ;
	
	private JLabel lblIssn, lblId, lblTitre, lblPageDebut, lblNom, labelInfo,
					labelMC, lblPrenom, lblNouvelArticle, lblNouvelAuteurs;
	
	private JScrollPane scrollSource, scrollCible;
	private JList listCible, listSource;
	
	private DefaultListModel modeleSource = new DefaultListModel();
	private DefaultListModel modeleCible = new DefaultListModel();
	
	// Attributs de l'article
	private String _titre;
	private String _page;
	private HashSet<Auteur> _auteurs = new HashSet<Auteur>();
	private HashSet<String> _motsCles = new HashSet<String>();
	
	private Parution _parution;
	private Periodique _periodique;
	private JScrollPane scrollIssn;
	private JList listIssn;
	private JScrollPane scrollIdent;
	private JList listIdent;
	private DefaultListModel modeleIdent = new DefaultListModel();
	private DefaultListModel modeleIssn = new DefaultListModel();
	
	public VueNouvelArticle(Controleur controleur) {
		super (controleur);
		content = new JPanel();
		getFrame().setTitle("Ajouter un Nouvel Article");
		getFrame().setBounds(100, 100, 715, 750);
		getFrame().setContentPane(content);
		
		lblIssn = new JLabel("ISSN Periodique");
		lblIssn.setBounds(22, 12, 98, 16);
		
		listIssn = new JList(modeleIssn);
		listIssn.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listIdent = new JList(modeleIdent);
		for (Integer issn : getControleur().getPeriodiques().keySet())
			modeleIssn.addElement(getControleur().getPeriodique(issn).afficheInfos());
		
		scrollIdent = new JScrollPane(listIdent);
		scrollIdent.setBounds(249, 136, 312, 109);
		
		scrollIssn = new JScrollPane(listIssn);
		scrollIssn.setBounds(249, 11, 312, 84);
		
		textFieldPage = new JTextField();
		textFieldPage.setEnabled(false);
		textFieldPage.setBounds(254, 349, 192, 28);
		textFieldPage.setColumns(10);
		
		btnEnregistrerArt = new JButton("Enregistrer");
		btnEnregistrerArt.setEnabled(false);
		btnEnregistrerArt.setBounds(458, 308, 112, 29);
		btnEnregistrerArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_titre = textFieldTitre.getText();
				_page = textFieldPage.getText();
				try
				{
					Integer.decode(_page);
					if (_titre.length() == 0 || _page.length() == 0)
						new Message("Vous devez renseinger tous les champs", Controleur.attention);
					else
						getControleur().rechArticle(getParution(), _titre);
				} catch (NumberFormatException ex) {
					new Message("La page n'est pas un chiffre", Controleur.erreur);
				}
			}
		});
		
		btnAnnulerArt = new JButton("Annuler");
		btnAnnulerArt.setEnabled(false);
		btnAnnulerArt.setBounds(458, 348, 112, 29);
		btnAnnulerArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEtat(Vue.inter1);
			}
		});
		
		lblPageDebut = new JLabel("Page de début");
		lblPageDebut.setEnabled(false);
		lblPageDebut.setBounds(22, 366, 88, 16);
		
		labelInfo = new JLabel("Réinitialise la liste des auteurs");
		labelInfo.setFont(new Font("Dialog", Font.PLAIN, 13));
		labelInfo.setEnabled(false);
		labelInfo.setBounds(458, 493, 192, 16);
		
		lblTitre = new JLabel("Titre de l'Article");
		lblTitre.setEnabled(false);
		lblTitre.setBounds(22, 326, 100, 16);
		
		lblId = new JLabel("Identifiant de la Parution");
		lblId.setEnabled(false);
		lblId.setBounds(22, 123, 155, 16);
		
		lblNom = new JLabel("Nom de l'auteur");
		lblNom.setEnabled(false);
		lblNom.setBounds(22, 446, 100, 16);
		
		lblPrenom = new JLabel("Prénom de l'auteur");
		lblPrenom.setEnabled(false);
		lblPrenom.setBounds(22, 485, 117, 16);
		
		labelMC = new JLabel("Mots-Clefs");
		labelMC.setEnabled(false);
		labelMC.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		labelMC.setBounds(346, 508, 73, 16);
		
		textFieldNom = new JTextField();
		textFieldNom.setEnabled(false);
		textFieldNom.setBounds(254, 429, 192, 28);
		textFieldNom.setColumns(10);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setEnabled(false);
		textFieldPrenom.setBounds(254, 468, 192, 28);
		textFieldPrenom.setColumns(10);
		
		btnAnnulerPa = new JButton("Annuler");
		btnAnnulerPa.setEnabled(false);
		btnAnnulerPa.setBounds(22, 216, 113, 29);
		btnAnnulerPa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEtat(Vue.initiale);
			}
		});
		
		buttonCS = new JButton("<<");
		buttonCS.setEnabled(false);
		buttonCS.setBounds(345, 548, 75, 29);
		buttonCS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions retirer mot cle
				int [] indexes = listCible.getSelectedIndices();
				HashSet<String> elems = new HashSet<String>();
				for (int index : indexes)
				{
					String elem = modeleCible.getElementAt(index).toString();
					modeleSource.addElement(elem);
					elems.add(elem);
					if (!buttonSC.isEnabled())
						buttonSC.setEnabled(true);
				}
				for (String elem : elems)
					modeleCible.removeElement(elem);
				if (modeleCible.isEmpty())
					buttonCS.setEnabled(false);
			}
		});
		
		listSource = new JList(modeleSource);
		listSource.setEnabled(false);
		scrollSource = new JScrollPane(listSource);
		scrollSource.setBounds(87, 536, 230, 132);
		
		listCible = new JList(modeleCible);
		listCible.setEnabled(false);
		scrollCible = new JScrollPane(listCible);
		scrollCible.setBounds(452, 536, 230, 132);
		
		buttonSC = new JButton(">>");
		buttonSC.setEnabled(false);
		buttonSC.setBounds(345, 621, 75, 29);
		buttonSC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions ajouter mot cle
				int [] indexes = listSource.getSelectedIndices();
				HashSet<String> elems = new HashSet<String>();
				for (int index : indexes)
				{
					String elem = modeleSource.getElementAt(index).toString();
					modeleCible.addElement(elem);
					elems.add(elem);
					if (!buttonCS.isEnabled())
						buttonCS.setEnabled(true);
				}
				for (String elem : elems)
					modeleSource.removeElement(elem);
				if (modeleSource.isEmpty())
					buttonSC.setEnabled(false);
			}
		});
		
		btnFermer = new JButton("Fermer");
		btnFermer.setBounds(175, 681, 87, 29);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueNouvelArticle.this);
				getControleur().menuBiblio();
			}
		});
		
		btnNouvelAut = new JButton("Nouvel Auteur");
		btnNouvelAut.setEnabled(false);
		btnNouvelAut.setBounds(458, 428, 134, 29);
		btnNouvelAut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Action enregistrer auteur
				String nom = textFieldNom.getText();
				String prenom = textFieldPrenom.getText();
				if (nom.length() != 0 && prenom.length() != 0) {
					Auteur auteur = new Auteur(nom, prenom);
					if (!getControleur().auteurExiste(auteur, _auteurs)) {
						_auteurs.add(auteur);
						textFieldNom.setText("");
						textFieldPrenom.setText("");
					}
				} else
					new Message("Un des champs est vide", Controleur.attention);
			}
		});
		
		btnAnnulerAut = new JButton("Annuler");
		btnAnnulerAut.setEnabled(false);
		btnAnnulerAut.setBounds(458, 467, 93, 29);
		btnAnnulerAut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_auteurs.clear();
				setEtat(Vue.inter2);
			}
		});
		
		textFieldTitre = new JTextField();
		textFieldTitre.setEnabled(false);
		textFieldTitre.setBounds(254, 309, 192, 28);
		textFieldTitre.setColumns(10);
		
		btnRechPe = new JButton("Rechercher");
		btnRechPe.setBounds(22, 56, 113, 29);
		btnRechPe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listIssn.getSelectedIndex();
				if (index == -1)
					new Message("Vous devez sélectionnez un periodique", Controleur.information);
				else
					getControleur().rechPeriodique(modeleIssn.get(index).toString());
			}
		});
		
		btnRechPa = new JButton("Rechercher");
		btnRechPa.setEnabled(false);
		btnRechPa.setBounds(22, 155, 113, 29);
		btnRechPa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listIdent.getSelectedIndex();
				if (index == -1)
					new Message("Vous devez sélectionnez une parution", Controleur.information);
				else
					getControleur().rechParution(getPeriodique(), modeleIdent.get(index).toString());
			}
		});
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setEnabled(false);
		btnTerminer.setBounds(450, 681, 100, 29);
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int index = 0; index < modeleCible.size(); index++)
					_motsCles.add(modeleCible.get(index).toString());
				if (modeleCible.isEmpty())
					new Message("Vous devez entrer au moins un mot clé", Controleur.information);
				else
					getControleur().nouvArticle(_titre, Integer.decode(_page), _auteurs, _motsCles, getParution());
			}
		});
		
		lblNouvelArticle = new JLabel("Nouvel Article");
		lblNouvelArticle.setEnabled(false);
		lblNouvelArticle.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNouvelArticle.setBounds(335, 275, 95, 16);
		
		lblNouvelAuteurs = new JLabel("Nouvel Auteur");
		lblNouvelAuteurs.setEnabled(false);
		lblNouvelAuteurs.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNouvelAuteurs.setBounds(334, 396, 98, 16);
		content.setLayout(null);
		
		content.add(lblIssn);
		content.add(lblPageDebut);
		content.add(lblTitre);
		content.add(lblId);
		content.add(lblNom);
		content.add(lblPrenom);
		content.add(labelMC);
		content.add(lblNouvelArticle);
		content.add(lblNouvelAuteurs);
		content.add(labelInfo);
		content.add(textFieldTitre);
		content.add(textFieldPage);
		content.add(textFieldNom);
		content.add(textFieldPrenom);
		
		content.add(scrollSource);
		content.add(scrollCible);
		
		content.add(btnEnregistrerArt);
		content.add(btnAnnulerArt);
		content.add(buttonCS);
		content.add(buttonSC);
		content.add(btnFermer);
		content.add(btnNouvelAut);
		content.add(btnAnnulerAut);
		content.add(btnRechPe);
		content.add(btnRechPa);
		content.add(btnAnnulerPa);
		content.add(btnTerminer);
		content.add(scrollIssn);
		content.add(scrollIdent);
		
		getFrame().setVisible(true);
	}
	
	public void setPeriodique(Periodique pe) { _periodique = pe; }
	public void setParution(Parution pa) { _parution = pa; }
	public Periodique getPeriodique() { return _periodique; }
	public Parution getParution() { return _parution; }
	
	public void setEtat(int etat){
		super.setEtat(etat);
		switch (etat) {
		case initiale:
			initSources();
			lblIssn.setEnabled(true);
			listIssn.setEnabled(true);
			btnRechPe.setEnabled(true);
			btnAnnulerPa.setEnabled(false);
			lblId.setEnabled(false);
			listIdent.setEnabled(false);
			btnRechPa.setEnabled(false);
			btnAnnulerPa.setEnabled(false);
			labelMC.setEnabled(false);
			listSource.setEnabled(false);
			listCible.setEnabled(false);
			buttonSC.setEnabled(false);
			buttonCS.setEnabled(false);
			btnTerminer.setEnabled(false);
			lblNouvelAuteurs.setEnabled(false);
			labelInfo.setEnabled(false);
			lblNom.setEnabled(false);
			lblPrenom.setEnabled(false);
			textFieldNom.setEnabled(false);
			textFieldPrenom.setEnabled(false);
			btnNouvelAut.setEnabled(false);
			btnAnnulerAut.setEnabled(false);
			lblNouvelArticle.setEnabled(false);
			break;
		case inter1:
			// selection periodique -> selection parution
			lblIssn.setEnabled(false);
			listIssn.setEnabled(false);
			btnRechPe.setEnabled(false);
			lblId.setEnabled(true);
			listIdent.setEnabled(true);
			btnRechPa.setEnabled(true);
			btnAnnulerPa.setEnabled(true);
			
			// Ajout article -> selection parution
			lblNouvelArticle.setEnabled(false);
			lblPageDebut.setEnabled(false);
			lblTitre.setEnabled(false);
			textFieldTitre.setEnabled(false);
			textFieldPage.setEnabled(false);
			btnEnregistrerArt.setEnabled(false);
			btnAnnulerArt.setEnabled(false);
			break;
		case inter2:
			// selection parution -> ajout article
			lblId.setEnabled(false);
			listIdent.setEnabled(false);
			btnRechPa.setEnabled(false);
			btnAnnulerPa.setEnabled(false);
			lblNouvelArticle.setEnabled(true);
			lblPageDebut.setEnabled(true);
			lblTitre.setEnabled(true);
			textFieldTitre.setEnabled(true);
			textFieldPage.setEnabled(true);
			btnEnregistrerArt.setEnabled(true);
			btnAnnulerArt.setEnabled(true);
			
			// Ajout auteur -> ajout article
			initSources();
			lblNouvelAuteurs.setEnabled(false);
			labelInfo.setEnabled(false);
			lblNom.setEnabled(false);
			lblPrenom.setEnabled(false);
			textFieldNom.setEnabled(false);
			textFieldPrenom.setEnabled(false);
			btnNouvelAut.setEnabled(false);
			btnAnnulerAut.setEnabled(false);
			listSource.setEnabled(false);
			listCible.setEnabled(false);
			buttonSC.setEnabled(false);
			buttonCS.setEnabled(false);
			btnTerminer.setEnabled(false);
			break;
		case inter3:
			// Ajout article -> ajout auteurs
			lblPageDebut.setEnabled(false);
			lblTitre.setEnabled(false);
			textFieldTitre.setEnabled(false);
			textFieldPage.setEnabled(false);
			btnEnregistrerArt.setEnabled(false);
			btnAnnulerArt.setEnabled(false);
			lblNouvelAuteurs.setEnabled(true);
			labelInfo.setEnabled(true);
			lblNom.setEnabled(true);
			lblPrenom.setEnabled(true);
			textFieldNom.setEnabled(true);
			textFieldPrenom.setEnabled(true);
			btnNouvelAut.setEnabled(true);
			btnAnnulerAut.setEnabled(true);
			
			// Ajout auteur -> ajout article
			listSource.setEnabled(false);
			listCible.setEnabled(false);
			break;
		case finale:
			// Ajout auteurs -> Ajout mots cles
			labelMC.setEnabled(true);
			listSource.setEnabled(true);
			listCible.setEnabled(true);
			buttonSC.setEnabled(true);
			btnTerminer.setEnabled(true);
			btnFermer.setEnabled(true);
			break;
		}
	}

	private void initSources() {
		modeleCible.clear();
		modeleSource.clear();
		modeleIdent.clear();
		textFieldTitre.setText("");
		textFieldPage.setText("");
		_auteurs = new HashSet<Auteur>();
		for(String item : getControleur().lectureLignesFichier())
			modeleSource.addElement(item);
	}
	
	public void update(Observable observable, Object objet) {
		// maj de la vue lorque l'ouvrage a été modifié
		modeleIdent.clear();
		for (Integer id : getPeriodique().getParutions().keySet())
			modeleIdent.addElement(getPeriodique().getParution(id).afficheParution());
		super.update(observable,  objet);
	}
}

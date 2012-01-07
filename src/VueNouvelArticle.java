import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.awt.Font;
import javax.swing.JScrollPane;


public class VueNouvelArticle extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JTextField textFieldId, textFieldPage, textFieldNom, textFieldPrenom, textFieldIssn, textFieldTitre;
	
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
	
	public VueNouvelArticle(Controleur controleur) {
		super (controleur);
		content = new JPanel();
		getFrame().setTitle("Ajouter un Nouvel Article");
		getFrame().setBounds(100, 100, 640, 555);
		getFrame().setContentPane(content);
		
		lblIssn = new JLabel("ISSN Periodique");
		lblIssn.setBounds(22, 12, 98, 16);
		
		textFieldId = new JTextField();
		textFieldId.setEnabled(false);
		textFieldId.setBounds(189, 46, 192, 28);
		textFieldId.setColumns(10);
		
		textFieldPage = new JTextField();
		textFieldPage.setEnabled(false);
		textFieldPage.setBounds(189, 160, 192, 28);
		textFieldPage.setColumns(10);
		
		btnEnregistrerArt = new JButton("Enregistrer");
		btnEnregistrerArt.setEnabled(false);
		btnEnregistrerArt.setBounds(393, 119, 112, 29);
		btnEnregistrerArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_titre = textFieldTitre.getText();
				_page = textFieldPage.getText();
				try
				{
					Integer.decode(_page);
					if (_titre.length() == 0 || _page.length() == 0)
					{
						Message msg = new Message("Vous devez renseinger tous les champs");
						msg.setVisible(true);
					}
					else
						getControleur().rechArticle(getParution(), _titre);
				} catch (NumberFormatException ex) {
					Message msg = new Message("La page n'est pas un numero");
					msg.setVisible(true);
				}
			}
		});
		
		btnAnnulerArt = new JButton("Annuler");
		btnAnnulerArt.setEnabled(false);
		btnAnnulerArt.setBounds(393, 159, 112, 29);
		btnAnnulerArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEtat(Vue.inter1);
			}
		});
		
		lblPageDebut = new JLabel("Page de début");
		lblPageDebut.setEnabled(false);
		lblPageDebut.setBounds(22, 166, 88, 16);
		
		labelInfo = new JLabel("Réinitialise la liste des auteurs");
		labelInfo.setFont(new Font("Dialog", Font.PLAIN, 13));
		labelInfo.setEnabled(false);
		labelInfo.setBounds(393, 304, 192, 16);
		
		lblTitre = new JLabel("Titre de l'Article");
		lblTitre.setEnabled(false);
		lblTitre.setBounds(22, 126, 100, 16);
		
		lblId = new JLabel("Identifiant de la Parution");
		lblId.setEnabled(false);
		lblId.setBounds(22, 52, 155, 16);
		
		lblNom = new JLabel("Nom de l'auteur");
		lblNom.setEnabled(false);
		lblNom.setBounds(22, 246, 100, 16);
		
		lblPrenom = new JLabel("Prénom de l'auteur");
		lblPrenom.setEnabled(false);
		lblPrenom.setBounds(22, 285, 117, 16);
		
		labelMC = new JLabel("Mots-Clefs");
		labelMC.setEnabled(false);
		labelMC.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		labelMC.setBounds(281, 319, 73, 16);
		
		textFieldNom = new JTextField();
		textFieldNom.setEnabled(false);
		textFieldNom.setBounds(189, 240, 192, 28);
		textFieldNom.setColumns(10);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setEnabled(false);
		textFieldPrenom.setBounds(189, 279, 192, 28);
		textFieldPrenom.setColumns(10);
		
		btnAnnulerPa = new JButton("Annuler");
		btnAnnulerPa.setEnabled(false);
		btnAnnulerPa.setBounds(524, 45, 93, 29);
		btnAnnulerPa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEtat(Vue.initiale);
			}
		});
		
		buttonCS = new JButton("<<");
		buttonCS.setEnabled(false);
		buttonCS.setBounds(280, 359, 75, 29);
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
		scrollSource.setBounds(22, 347, 230, 132);
		
		listCible = new JList(modeleCible);
		listCible.setEnabled(false);
		scrollCible = new JScrollPane(listCible);
		scrollCible.setBounds(387, 347, 230, 132);
		
		buttonSC = new JButton(">>");
		buttonSC.setEnabled(false);
		buttonSC.setBounds(280, 432, 75, 29);
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
		btnFermer.setBounds(110, 492, 87, 29);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueNouvelArticle.this);
				getControleur().menuBiblio();
			}
		});
		
		btnNouvelAut = new JButton("Nouvel Auteur");
		btnNouvelAut.setEnabled(false);
		btnNouvelAut.setBounds(393, 239, 134, 29);
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
				} else {
					Message dialog = new Message("Un des champs est vide");
					dialog.setVisible(true);
				}
			}
		});
		
		btnAnnulerAut = new JButton("Annuler");
		btnAnnulerAut.setEnabled(false);
		btnAnnulerAut.setBounds(393, 278, 93, 29);
		btnAnnulerAut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_auteurs.clear();
				setEtat(Vue.inter2);
			}
		});
		
		textFieldIssn = new JTextField();
		textFieldIssn.setBounds(189, 6, 192, 28);
		textFieldIssn.setColumns(10);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setEnabled(false);
		textFieldTitre.setBounds(189, 120, 192, 28);
		textFieldTitre.setColumns(10);
		
		btnRechPe = new JButton("Rechercher");
		btnRechPe.setBounds(393, 5, 113, 29);
		btnRechPe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String issn = textFieldIssn.getText();
				if (issn.length() == 0)
				{
					Message msg = new Message("Vous devez remplir le champ ISSN");
					msg.setVisible(true);
				}
				else
					getControleur().rechPeriodique(issn);
			}
		});
		
		btnRechPa = new JButton("Rechercher");
		btnRechPa.setEnabled(false);
		btnRechPa.setBounds(393, 45, 113, 29);
		btnRechPa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textFieldId.getText();
				if (id.length() == 0)
				{
					Message msg = new Message("Vous devez remplir le champ de la parution");
					msg.setVisible(true);
				}
				else
					getControleur().rechParution(getPeriodique(), Integer.decode(id));
			}
		});
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setEnabled(false);
		btnTerminer.setBounds(385, 492, 100, 29);
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int index = 0; index < modeleCible.size(); index++)
					_motsCles.add(modeleCible.get(index).toString());
				if (modeleCible.isEmpty())
				{
					Message dg = new Message("Vous devez entrer au moins un mot clé");
					dg.setVisible(true);
				}
				else
					getControleur().nouvArticle(_titre, Integer.decode(_page), _auteurs, _motsCles, getParution());
			}
		});
		
		lblNouvelArticle = new JLabel("Nouvel Article");
		lblNouvelArticle.setEnabled(false);
		lblNouvelArticle.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNouvelArticle.setBounds(270, 86, 95, 16);
		
		lblNouvelAuteurs = new JLabel("Nouvel Auteur");
		lblNouvelAuteurs.setEnabled(false);
		lblNouvelAuteurs.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNouvelAuteurs.setBounds(269, 207, 98, 16);
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
		
		content.add(textFieldIssn);
		content.add(textFieldId);
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
			textFieldIssn.setEnabled(true);
			btnRechPe.setEnabled(true);
			btnAnnulerPa.setEnabled(false);
			lblId.setEnabled(false);
			textFieldId.setEnabled(false);
			btnRechPa.setEnabled(false);
			btnAnnulerPa.setEnabled(false);
			labelMC.setEnabled(false);
			listSource.setEnabled(false);
			listCible.setEnabled(false);
			buttonSC.setEnabled(false);
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
			textFieldIssn.setEnabled(false);
			btnRechPe.setEnabled(false);
			lblId.setEnabled(true);
			textFieldId.setEnabled(true);
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
			textFieldId.setEnabled(false);
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
		_auteurs = new HashSet<Auteur>();
		for(String item : getControleur().lectureLignesFichier())
			modeleSource.addElement(item);
	}
}

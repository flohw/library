import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
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
		setTitle("Ajouter un Nouvel Article");
		setBounds(100, 100, 736, 655);
		setContentPane(content);
		content.setLayout(null);
		
		lblIssn = new JLabel("ISSN Periodique");
		lblIssn.setBounds(61, 20, 102, 15);
		
		textFieldId = new JTextField();
		textFieldId.setEnabled(false);
		textFieldId.setBounds(254, 60, 195, 21);
		textFieldId.setColumns(10);
		
		textFieldPage = new JTextField();
		textFieldPage.setEnabled(false);
		textFieldPage.setBounds(254, 185, 195, 21);
		textFieldPage.setColumns(10);
		
		btnEnregistrerArt = new JButton("Enregistrer");
		btnEnregistrerArt.setEnabled(false);
		btnEnregistrerArt.setBounds(461, 140, 108, 25);
		btnEnregistrerArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_titre = textFieldId.getText();
				_page = textFieldPage.getText();
				try
				{
					Integer.decode(_page);
					if (_titre.length() == 0 || _page.length() == 0)
					{
						Message msg = new Message("Vous devez rensienger tous les champs");
						msg.setVisible(true);
					}
					else
						getControleur().rechArticle(_parution, _titre);
				} catch (NumberFormatException ex) {
					Message msg = new Message("La page n'est pas un numero");
					msg.setVisible(true);
				}
			}
		});
		
		btnAnnulerArt = new JButton("Annuler");
		btnAnnulerArt.setEnabled(false);
		btnAnnulerArt.setBounds(605, 140, 108, 25);
		btnAnnulerArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEtat(Vue.inter1);
			}
		});
		
		lblPageDebut = new JLabel("Page de début");
		lblPageDebut.setEnabled(false);
		lblPageDebut.setBounds(61, 188, 102, 15);
		
		labelInfo = new JLabel("Réinitialise la liste des auteurs");
		labelInfo.setFont(new Font("Dialog", Font.PLAIN, 13));
		labelInfo.setEnabled(false);
		labelInfo.setBounds(471, 332, 195, 15);
		
		lblTitre = new JLabel("Titre de l'Article");
		lblTitre.setEnabled(false);
		lblTitre.setBounds(61, 145, 102, 15);
		
		lblId = new JLabel("Identifiant de la Parution");
		lblId.setEnabled(false);
		lblId.setBounds(61, 63, 155, 15);
		
		lblNom = new JLabel("Nom de l'auteur");
		lblNom.setEnabled(false);
		lblNom.setBounds(61, 268, 102, 15);
		
		lblPrenom = new JLabel("Prénom de l'auteur");
		lblPrenom.setEnabled(false);
		lblPrenom.setBounds(61, 310, 125, 15);
		
		labelMC = new JLabel("Mots-Clefs");
		labelMC.setEnabled(false);
		labelMC.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		labelMC.setBounds(323, 386, 117, 15);
		
		textFieldNom = new JTextField();
		textFieldNom.setEnabled(false);
		textFieldNom.setBounds(254, 265, 195, 21);
		textFieldNom.setColumns(10);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setEnabled(false);
		textFieldPrenom.setBounds(254, 307, 195, 21);
		textFieldPrenom.setColumns(10);
		
		btnAnnulerPa = new JButton("Annuler");
		btnAnnulerPa.setEnabled(false);
		btnAnnulerPa.setBounds(605, 58, 108, 25);
		btnAnnulerPa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEtat(Vue.initiale);
			}
		});
		
		buttonCS = new JButton("<<");
		buttonCS.setEnabled(false);
		buttonCS.setBounds(353, 432, 54, 25);
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
		scrollSource.setBounds(61, 413, 280, 161);
		
		listCible = new JList(modeleCible);
		listCible.setEnabled(false);
		scrollCible = new JScrollPane(listCible);
		scrollCible.setBounds(419, 413, 294, 161);
		
		buttonSC = new JButton(">>");
		buttonSC.setEnabled(false);
		buttonSC.setBounds(353, 517, 54, 25);
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
		btnFermer.setBounds(174, 590, 123, 25);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueNouvelArticle.this);
			}
		});
		
		btnNouvelAut = new JButton("Nouvel Auteur");
		btnNouvelAut.setEnabled(false);
		btnNouvelAut.setBounds(461, 264, 125, 25);
		btnNouvelAut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Action enregistrer auteur
				String nom = textFieldNom.getText();
				String prenom = textFieldPrenom.getText();
				if (nom.length() != 0 && prenom.length() != 0)
				{
					Auteur auteur = new Auteur(nom, prenom);
					_auteurs.add(auteur);
					Message dialog = new Message("Auteur enregistré");
					dialog.setVisible(true);
					textFieldNom.setText("");
					textFieldPrenom.setText("");
					setEtat(Vue.inter4);
				}
				else
				{
					Message dialog = new Message("Un des champs est vide");
					dialog.setVisible(true);
				}
			}
		});
		
		btnAnnulerAut = new JButton("Annuler");
		btnAnnulerAut.setEnabled(false);
		btnAnnulerAut.setBounds(461, 305, 125, 25);
		btnAnnulerAut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_auteurs.clear();
				setEtat(Vue.inter2);
			}
		});
		
		textFieldIssn = new JTextField();
		textFieldIssn.setBounds(254, 17, 195, 21);
		textFieldIssn.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 97, 728, 15);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setEnabled(false);
		textFieldTitre.setBounds(254, 142, 195, 21);
		textFieldTitre.setColumns(10);
		
		btnRechPe = new JButton("Rechercher");
		btnRechPe.setBounds(461, 15, 108, 25);
		btnRechPe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String issn = textFieldIssn.getText();
				if (issn.length() == 0)
				{
					Message msg = new Message("Vous devez remplir le champ ISSN");
					msg.setVisible(true);
				}
				else
				{
					_periodique = getControleur().rechPeriodique(issn);
					if (_periodique != null && _periodique.getNbParutions() != 0)
						setEtat(inter1);
					else if (_periodique != null && _periodique.getNbParutions() == 0)
					{			
						int option = JOptionPane.showConfirmDialog(null, "Il n'y a pas de parutions, voulez-vous les créer ?",
								"Erreur Parutions", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

						if(option != JOptionPane.NO_OPTION )
							setEtat(Vue.alternate);
						else
							setEtat(Vue.initiale);
					}
				}
			}
		});
		
		btnRechPa = new JButton("Rechercher");
		btnRechPa.setEnabled(false);
		btnRechPa.setBounds(461, 58, 108, 25);
		btnRechPa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textFieldId.getText();
				if (id.length() == 0)
				{
					Message msg = new Message("Vous devez remplir le champ de la parution");
					msg.setVisible(true);
				}
				else
					_parution = getControleur().rechParution(_periodique, Integer.decode(id));
			}
		});
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setEnabled(false);
		btnTerminer.setBounds(461, 590, 107, 25);
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
					getControleur().nouvArticle(_titre, Integer.decode(_page), _auteurs, _motsCles, _parution);
			}
		});
		
		lblNouvelArticle = new JLabel("Nouvel Article");
		lblNouvelArticle.setEnabled(false);
		lblNouvelArticle.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNouvelArticle.setBounds(315, 114, 104, 16);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 220, 728, 15);
		
		lblNouvelAuteurs = new JLabel("Nouvel Auteur");
		lblNouvelAuteurs.setEnabled(false);
		lblNouvelAuteurs.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNouvelAuteurs.setBounds(315, 235, 134, 16);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 359, 728, 15);
		
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
		
		content.add(separator);
		content.add(separator_1);
		content.add(separator_2);
		
		content.setVisible(true);
	}
	
	public void setEtat(int etat){
		super.setEtat(etat);
		switch (etat) {
		case initiale:
			initModeles();
			lblIssn.setEnabled(true);
			textFieldIssn.setEnabled(true);
			btnRechPe.setEnabled(true);
			btnAnnulerPa.setEnabled(false);
			lblId.setEnabled(false);
			textFieldId.setEnabled(false);
			btnRechPa.setEnabled(false);
			btnAnnulerPa.setEnabled(false);
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
			initModeles();
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
		case inter4:
			// Ajout auteurs -> Ajout mots cles
			labelMC.setEnabled(true);
			listSource.setEnabled(true);
			listCible.setEnabled(true);
			buttonSC.setEnabled(true);
			btnTerminer.setEnabled(true);
			btnFermer.setEnabled(true);
			break;
		case alternate:
			content.setVisible(false);
			this.getControleur().saisirParution();
			break;
		case alternate2:
			content.setVisible(false);
			this.getControleur().saisiePeriodique();
			break;
		case finale:
			content.setVisible(false);
			this.getControleur().fermerVue(this);
			break;
		}
	}

	private void initModeles() {
		modeleCible.clear();
		modeleSource.clear();
		for(String item : getControleur().lectureLignesFichier())
			modeleSource.addElement(item);
	}
}

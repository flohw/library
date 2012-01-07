import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Rectangle;
import java.util.Observable;
import javax.swing.ListSelectionModel;


public class VueConsulterOuvrage extends Vue {

	private static final long serialVersionUID = 1L;

	private JPanel content;
	
	private JLabel lblAuteur;
	private JLabel lblDateDdition;
	private JLabel lblDateEd;
	private JLabel lblEditeur;
	private JLabel lblMotsclefs;
	private JLabel lblExemplaires;
	private JLabel lblIsbn;
	private JTextField textFieldTitre;
	private JTextField textFieldDateEd;
	private JTextField textFieldEditeur;
	
	private JButton btnRechercher;
	private JButton btnTerminer;
	private JList listExemplaires;
	private JList listMC;
	private JList listAuteurs;
	
	private JScrollPane scrollAuteurs;
	private JScrollPane scrollMC;
	private JScrollPane scrollExemplaires;
	private JScrollPane scrollIsbn;
	
	private DefaultListModel modeleAuteurs = new DefaultListModel();
	private DefaultListModel modeleExemplaires = new DefaultListModel();
	private DefaultListModel modeleMC = new DefaultListModel();
	private DefaultListModel modeleIsbn = new DefaultListModel();
	private JList listIsbn;
	
	private Ouvrage _ouvrage;

	public VueConsulterOuvrage(Controleur controleur) {
		super(controleur);
		content = new JPanel();
		getFrame().setTitle("Consulter un Ouvrage");
		getFrame().setBounds(100, 100, 600, 640);
		getFrame().setContentPane(content);
		
		lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(89, 9, 154, 15);
		
		listIsbn = new JList(modeleIsbn);
		listIsbn.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollIsbn = new JScrollPane(listIsbn);
		scrollIsbn.setBounds(219, 9, 339, 105);
		
		for (String isbn : getControleur().getOuvrages().keySet())
			modeleIsbn.addElement(getControleur().getOuvrage(isbn).afficheOuvrage());
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(210, 126, 154, 25);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions recherche ouvrage
				int index = listIsbn.getSelectedIndex();
				if (index == -1) {
					Message dialog = new Message("Sélectionnez un ouvrage");
					dialog.setVisible(true);
				} else
					getControleur().rechOuvrage(modeleIsbn.get(index).toString());
			}
		});
		
		lblAuteur = new JLabel("Auteur(s)");
		lblAuteur.setBounds(89, 207, 154, 15);
		
		lblDateDdition = new JLabel("Titre");
		lblDateDdition.setBounds(89, 180, 154, 15);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setEditable(false);
		textFieldTitre.setBounds(219, 178, 339, 19);
		textFieldTitre.setColumns(10);
		
		lblDateEd = new JLabel("Date d'édition");
		lblDateEd.setBounds(89, 287, 154, 15);
		
		textFieldDateEd = new JTextField();
		textFieldDateEd.setEditable(false);
		textFieldDateEd.setBounds(219, 285, 339, 19);
		textFieldDateEd.setColumns(10);
		
		lblEditeur = new JLabel("Editeur");
		lblEditeur.setBounds(89, 320, 154, 15);
		
		textFieldEditeur = new JTextField();
		textFieldEditeur.setEditable(false);
		textFieldEditeur.setBounds(219, 318, 339, 19);
		textFieldEditeur.setColumns(10);
		
		lblMotsclefs = new JLabel("Mot(s)-Clef(s)");
		lblMotsclefs.setBounds(89, 381, 154, 15);
		
		lblExemplaires = new JLabel("Exemplaire(s)");
		lblExemplaires.setBounds(89, 481, 154, 15);
		
		listAuteurs = new JList(modeleAuteurs);
		scrollAuteurs = new JScrollPane(listAuteurs);
		scrollAuteurs.setBounds(new Rectangle(219, 204, 339, 69));
		
		listMC = new JList(modeleMC);
		scrollMC = new JScrollPane(listMC);
		scrollMC.setBounds(new Rectangle(219, 380, 339, 74));
		
		listExemplaires = new JList(modeleExemplaires);
		scrollExemplaires = new JScrollPane(listExemplaires);
		scrollExemplaires.setBounds(220, 479, 338, 70);
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setBounds(210, 561, 154, 25);
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions recherche ouvrage
				getControleur().fermerVue(VueConsulterOuvrage.this);
				getControleur().menuBiblio();
			}
		});
		
		content.setLayout(null);
		content.add(lblIsbn);
		content.add(lblDateEd);
		content.add(lblEditeur);
		content.add(lblExemplaires);
		content.add(lblMotsclefs);
		content.add(lblAuteur);
		content.add(lblDateDdition);
		content.add(textFieldTitre);
		content.add(textFieldDateEd);
		content.add(textFieldEditeur);
		content.add(scrollAuteurs);
		content.add(scrollMC);
		content.add(scrollExemplaires);
		content.add(btnRechercher);
		content.add(btnTerminer);
		content.add(scrollIsbn);
		
		getFrame().setVisible(true);
	}
	
	public Ouvrage getOuvrage() { return _ouvrage; }
	public void setOuvrage(Ouvrage ouv) { _ouvrage = ouv; }
	
	public void alimente(Ouvrage ouv) {
		modeleAuteurs.clear();
		modeleMC.clear();
		modeleExemplaires.clear();
		
		textFieldTitre.setText(ouv.getTitre());
		textFieldDateEd.setText(ESDate.ecrireDate (ouv.getDateEdition()));
		textFieldEditeur.setText(ouv.getEditeur());
		for (Auteur aut : ouv.getAuteurs())
			modeleAuteurs.addElement(aut.getAuteur());
		for (MotCle mc : ouv.getMotCles())
			modeleMC.addElement(mc.getMotcle());
		for (int ex : ouv.getExemplaires().keySet())
			modeleExemplaires.addElement(ouv.getExemplaire(ex).afficheInfos());
	}
	
	public void update(Observable observable, Object objet) {
		// maj de la vue lorque l'ouvrage a été modifié
		this.alimente(this.getOuvrage());
		super.update(observable,  objet);
	}
}
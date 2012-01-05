import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JList;
import java.awt.Rectangle;


public class VueConsulterOuvrage extends Vue {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	
	private JLabel lblAuteur;
	private JLabel lblDateDdition;
	private JLabel lblDateEd;
	private JLabel lblEditeur;
	private JLabel lblMotsclefs;
	private JLabel lblExemplaires;
	private JLabel lblIsbn;
	
	private JTextField textFieldIsbn;
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
	
	private DefaultListModel modeleAuteurs = new DefaultListModel();
	private DefaultListModel modeleExemplaires = new DefaultListModel();
	private DefaultListModel modeleMC = new DefaultListModel();
	
	private JSeparator separator;

	public VueConsulterOuvrage(Controleur controleur) {
		super(controleur);
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Consulter un Ouvrage");
		frame.setBounds(100, 100, 612, 541);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(89, 9, 154, 15);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(219, 7, 339, 19);
		textFieldIsbn.setColumns(10);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(246, 41, 154, 25);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions recherche ouvrage
				if (textFieldIsbn.getText().length() == 0)
				{
					Message dialog = new Message("Champ ISBN vide");
					dialog.setVisible(true);
				}
				else
					getControleur().rechOuvrage(textFieldIsbn.getText());
			}
		});
		
		separator = new JSeparator();
		separator.setBounds(0, 81, 604, 2);
		
		lblAuteur = new JLabel("Auteur(s)");
		lblAuteur.setBounds(89, 122, 154, 15);
		
		lblDateDdition = new JLabel("Titre");
		lblDateDdition.setBounds(89, 95, 154, 15);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setEditable(false);
		textFieldTitre.setBounds(219, 93, 339, 19);
		textFieldTitre.setColumns(10);
		
		lblDateEd = new JLabel("Date d'édition");
		lblDateEd.setBounds(89, 202, 154, 15);
		
		textFieldDateEd = new JTextField();
		textFieldDateEd.setEditable(false);
		textFieldDateEd.setBounds(219, 200, 339, 19);
		textFieldDateEd.setColumns(10);
		
		lblEditeur = new JLabel("Editeur");
		lblEditeur.setBounds(89, 235, 154, 15);
		
		textFieldEditeur = new JTextField();
		textFieldEditeur.setEditable(false);
		textFieldEditeur.setBounds(219, 233, 339, 19);
		textFieldEditeur.setColumns(10);
		
		lblMotsclefs = new JLabel("Mot(s)-Clef(s)");
		lblMotsclefs.setBounds(89, 296, 154, 15);
		
		lblExemplaires = new JLabel("Exemplaire(s)");
		lblExemplaires.setBounds(89, 396, 154, 15);
		
		listAuteurs = new JList(modeleAuteurs);
		scrollAuteurs = new JScrollPane(listAuteurs);
		scrollAuteurs.setBounds(new Rectangle(219, 119, 339, 69));
		
		listMC = new JList(modeleMC);
		scrollMC = new JScrollPane(listMC);
		scrollMC.setBounds(new Rectangle(219, 295, 339, 74));
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setBounds(264, 476, 111, 25);
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions recherche ouvrage
				setEtat(Vue.finale);
			}
		});
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblIsbn);
		frame.getContentPane().add(lblDateEd);
		frame.getContentPane().add(lblEditeur);
		frame.getContentPane().add(lblExemplaires);
		frame.getContentPane().add(lblMotsclefs);
		frame.getContentPane().add(lblAuteur);
		frame.getContentPane().add(lblDateDdition);
		frame.getContentPane().add(textFieldTitre);
		frame.getContentPane().add(textFieldIsbn);
		frame.getContentPane().add(textFieldDateEd);
		frame.getContentPane().add(textFieldEditeur);
		frame.getContentPane().add(scrollAuteurs);
		frame.getContentPane().add(scrollMC);
		
		listExemplaires = new JList(modeleExemplaires);
		scrollExemplaires = new JScrollPane(listExemplaires);
		scrollExemplaires.setBounds(220, 394, 338, 70);
		frame.getContentPane().add(scrollExemplaires);
		frame.getContentPane().add(btnRechercher);
		frame.getContentPane().add(btnTerminer);
		frame.getContentPane().add(separator);
	}
	
	public void alimente(Ouvrage ouv) {
		textFieldTitre.setText(ouv.getTitre());
		for (Auteur aut : ouv.getAuteurs())
			modeleAuteurs.addElement(aut.getAuteur());
		textFieldDateEd.setText(ESDate.ecrireDate (ouv.getDateEdition()));
		textFieldEditeur.setText(ouv.getEditeur());
		for (MotCle mc : ouv.getMotCles())
			modeleMC.addElement(mc.getMotcle());
		
		int nbConsult = ouv.getNbExemplairesEnConsultation();
		int nbEmpr = ouv.getNbExemplairesEmpruntable();
		for (int i : ouv.getExemplaires().keySet())
			modeleExemplaires.addElement(ouv.getExemplaire(i).afficheInfos());

		this.repaint();
		if ((nbConsult + nbEmpr)== 0 ){
			Message dialog = new Message("Aucun exemplaire n'est encore disponible");
			dialog.setVisible(true);
		}
	}
	
	public JFrame getFrame() { return frame; }
	
	public void setEtat(int etat) {
		super.setEtat(etat);
		switch (etat) {
		case initiale:
			frame.setVisible(true);
			break;
		case alternate:
			frame.setVisible(false);
			getControleur().saisirOuvrage();
			break;
		case finale:
			frame.setVisible(false);
			this.getControleur().menuBiblio();
			break;
		}
	}
}
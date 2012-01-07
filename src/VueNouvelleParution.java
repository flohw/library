import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.Observable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;


public class VueNouvelleParution extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JButton btnEnregistrer;
	private JButton btnAnnuler;
	
	private JSeparator separator, separator_1;
	private JTextField textFieldTitre;
	private JTextField textFieldId;
	private JTextField textFieldPerio;
	private JTextField textFieldDate;
	private JButton btnRechercher ;
	
	private Periodique _periodique;
	private JScrollPane scrollIssn;
	private JList listIssn;
	private DefaultListModel modeleIssn = new DefaultListModel();

	public VueNouvelleParution(Controleur controleur) {
		super (controleur);
		content = new JPanel();
		getFrame().setTitle("Nouvelle Parution");
		getFrame().setBounds(100, 100, 490, 430);
		getFrame().setContentPane(content);
		
		JLabel lblISSN = new JLabel("ISSN");
		lblISSN.setBounds(20, 19, 28, 16);
		
		listIssn = new JList(modeleIssn);
		scrollIssn = new JScrollPane(listIssn);
		scrollIssn.setBounds(178, 22, 281, 97);
		
		separator = new JSeparator();
		separator.setBounds(609, 47, 0, 12);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(20, 80, 113, 29);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String issn = textFieldIssn.getText();
				if (issn.length() == 0)
				{
					Message dialog = new Message("Vous devez remplir tous les champs");
					dialog.setVisible(true);
				}
				else
					getControleur().rechPeriodique(textFieldIssn.getText());
			}
		});
		
		
		btnAnnuler = new JButton("Fermer");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getControleur().fermerVue(VueNouvelleParution.this);
				getControleur().menuBiblio();
			}
		});
		btnAnnuler.setBounds(196, 367, 87, 29);
		
		btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setEnabled(false);
		btnEnregistrer.setBounds(347, 288, 112, 29);
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textFieldId.getText();
				String titre = textFieldTitre.getText();
				try {
					Integer.decode(id);
					if (id.length() == 0 || titre.length() == 0) {
						Message dialog = new Message("Vous devez remplir tous les champs");
						dialog.setVisible(true);
					} else
						getControleur().nouvelleParution(getPeriodique(), Integer.decode(id), titre);
				} catch (NumberFormatException ex) {
					Message msg = new Message("L'identifiant n'est pas un chiffre");
					msg.setVisible(true);
				}
			}
		});
		
		JLabel lblTitre = new JLabel("Titre");
		lblTitre.setBounds(20, 333, 29, 16);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setEditable(false);
		textFieldTitre.setColumns(10);
		textFieldTitre.setBounds(180, 327, 279, 28);
		
		JLabel lblIdentifiant = new JLabel("Identifiant");
		lblIdentifiant.setBounds(20, 293, 65, 16);
		
		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setColumns(10);
		textFieldId.setBounds(180, 287, 140, 28);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(684, 13, 0, 12);
		
		JLabel lblPeriodique = new JLabel("Periodique");
		lblPeriodique.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriodique.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblPeriodique.setBounds(20, 151, 439, 16);
		
		JLabel labelTitrePerio = new JLabel("Titre");
		labelTitrePerio.setBounds(20, 185, 29, 16);
		
		textFieldPerio = new JTextField();
		textFieldPerio.setEditable(false);
		textFieldPerio.setColumns(10);
		textFieldPerio.setBounds(180, 179, 279, 28);
		
		textFieldDate = new JTextField();
		textFieldDate.setEditable(false);
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(180, 219, 279, 28);
		content.setLayout(null);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(20, 225, 29, 16);
		
		content.add(lblDate);
		content.add(textFieldPerio);
		content.add(textFieldId);
		content.add(btnEnregistrer);
		content.add(textFieldDate);
		content.add(labelTitrePerio);
		content.add(lblPeriodique);
		content.add(separator_1);
		content.add(lblIdentifiant);
		content.add(textFieldTitre);
		content.add(lblTitre);
		content.add(btnAnnuler);
		content.add(btnRechercher);
		content.add(separator);
		
		JLabel lblParution = new JLabel("Parution");
		lblParution.setHorizontalAlignment(SwingConstants.CENTER);
		lblParution.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblParution.setBounds(20, 259, 439, 16);
		content.add(lblParution);
		content.add(lblISSN);
		content.add(scrollIssn);
		

		
		getFrame().setVisible(true);
	}
	
	public void setPeriodique(Periodique pe) { _periodique = pe; }
	public Periodique getPeriodique() { return _periodique; }
	
	public void alimente (Periodique pe)
	{
		textFieldIssn.setText(getPeriodique().getIssn());
		textFieldPerio.setText(pe.getNom());
		textFieldDate.setText(ESDate.ecrireDate(pe.getDate()));
	}
	
	public void update(Observable observable, Object objet) {
		// maj de la vue lorque l'ouvrage a été modifié
		this.alimente(this.getPeriodique());
		super.update(observable,  objet);
	}
	
	public void setEtat(int etat) {
		super.setEtat(etat);
		switch (etat) {
		case initiale:
			textFieldId.setText("");
			textFieldTitre.setText("");
			break;
		case finale:
			textFieldTitre.setEditable(true);
			textFieldId.setEditable(true);
			btnEnregistrer.setEnabled(true);
			break;
		}
	}
}

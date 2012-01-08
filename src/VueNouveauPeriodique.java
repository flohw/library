import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class VueNouveauPeriodique extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JTextField textFieldIssn;
	private JTextField textFieldNom;
	private JTextField textFieldDate;
	
	private JButton btnEnregistrer;
	private JButton btnAnnuler;

	public VueNouveauPeriodique(Controleur controleur) {
		super(controleur);
		content = new JPanel();
		getFrame().setTitle("Enregistrer un Nouveau Périodique");
		getFrame().setBounds(100, 100, 460, 200);
		getFrame().setContentPane(content);
		
		JLabel lblIssn = new JLabel("ISSN");
		lblIssn.setBounds(15, 22, 28, 16);
		
		textFieldIssn = new JTextField();
		textFieldIssn.setBounds(227, 16, 210, 28);
		textFieldIssn.setColumns(10);
		
		JLabel lblNomDuPriodique = new JLabel("Nom du périodique");
		lblNomDuPriodique.setBounds(15, 62, 121, 16);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(227, 56, 210, 28);
		textFieldNom.setColumns(10);
		
		JLabel lblDateDabonnement = new JLabel("Date d'abonnement");
		lblDateDabonnement.setBounds(15, 102, 122, 16);
		
		textFieldDate = new JTextField();
		textFieldDate.setText("mm/aaaa");
		textFieldDate.setBounds(227, 96, 210, 28);
		textFieldDate.setColumns(10);
		
		btnEnregistrer = new JButton("Nouveau Périodique");
		btnEnregistrer.setBounds(44, 136, 169, 29);
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions recherche ouvrage
				String issn = textFieldIssn.getText();
				String nom = textFieldNom.getText();
				String date = textFieldDate.getText();
				GregorianCalendar dateCal = ESDate.lireDate(date);
				try {
					if (issn.length() == 0 || nom.length() == 0 || date.length() == 0)
						new Message("Vous devez remplir tous les champs", Controleur.attention);
					else if (dateCal == null)
						new Message("La date est incorrecte", Controleur.erreur);
					else
						getControleur().nouveauPeriodique(Integer.decode(issn), nom, ESDate.lireDate(date));
				} catch (NumberFormatException ex) {
					new Message("L'issn n'est pas un nombre", Controleur.attention);
				}
			}
		});
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(292, 136, 93, 29);
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueNouveauPeriodique.this);
				getControleur().menuBiblio();
			}
		});
		content.setLayout(null);
		
		content.add(lblIssn);
		content.add(lblNomDuPriodique);
		content.add(lblDateDabonnement);
		content.add(textFieldNom);
		content.add(textFieldIssn);
		content.add(textFieldDate);
		content.add(btnEnregistrer);
		content.add(btnAnnuler);
		getFrame().setVisible(true);
	}
	
	public void setEtat(int etat) {
		super.setEtat(etat);
		switch (etat){
		case initiale:
			textFieldIssn.setText("");
			textFieldDate.setText("mm/aaaa");
			textFieldNom.setText("");
			break;
		}
	}

}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		setTitle("Enregistrer un Nouveau Périodique");
		setBounds(100, 100, 539, 154);
		setContentPane(content);
		content.setLayout(null);
		
		JLabel lblIssn = new JLabel("ISSN");
		lblIssn.setBounds(89, 9, 122, 15);
		
		textFieldIssn = new JTextField();
		textFieldIssn.setBounds(300, 7, 150, 19);
		textFieldIssn.setColumns(10);
		
		JLabel lblNomDuPriodique = new JLabel("Nom du périodique");
		lblNomDuPriodique.setBounds(89, 35, 122, 15);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(300, 33, 150, 19);
		textFieldNom.setColumns(10);
		
		JLabel lblDateDabonnement = new JLabel("Date d'abonnement");
		lblDateDabonnement.setBounds(89, 61, 122, 15);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(300, 59, 150, 19);
		textFieldDate.setColumns(10);
		
		btnEnregistrer = new JButton("Nouveau Périodique");
		btnEnregistrer.setBounds(89, 85, 161, 25);
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions recherche ouvrage
				String issn = textFieldIssn.getText();
				String nom = textFieldNom.getText();
				String date = textFieldDate.getText();
				if (issn.length() == 0 || nom.length() == 0 || date.length() == 0)
				{
					Message dialog = new Message("Vous devez remplir tous les champs");
					dialog.setVisible(true);
				}
				else
				{
					boolean add = getControleur().nouveauPeriodique(issn, nom, ESDate.lireDate(date));
					if (add)
					{		
						int option = JOptionPane.showConfirmDialog(null, "Periodique enregistré, voulez-vous en créer un nouveau ?",
								"Nouveau Periodique", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

						if(option == JOptionPane.YES_OPTION )
							setEtat(Vue.initiale);
						else
							getControleur().fermerVue(VueNouveauPeriodique.this);
					}
				}
			}
		});
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(300, 85, 150, 25);
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions recherche ouvrage
				getControleur().fermerVue(VueNouveauPeriodique.this);
			}
		});
		
		content.add(lblIssn);
		content.add(lblNomDuPriodique);
		content.add(lblDateDabonnement);
		content.add(textFieldNom);
		content.add(textFieldIssn);
		content.add(textFieldDate);
		content.add(btnEnregistrer);
		content.add(btnAnnuler);
	}
	
	public void setEtat(int etat) {
		switch (etat){
		case initiale:
			textFieldIssn.setText("");
			textFieldDate.setText("");
			textFieldNom.setText("");
			break;
		case finale:
			break;
		}
	}

}

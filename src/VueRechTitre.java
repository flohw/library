import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class VueRechTitre extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JTextField textFieldTitre;
	
	private JButton btnEnregistrer;
	private JButton btnAnnuler;

	public VueRechTitre(Controleur controleur) {
		super(controleur);
		content = new JPanel();
		getFrame().setTitle("Rechercher par titre");
		getFrame().setBounds(100, 100, 460, 120);
		getFrame().setContentPane(content);
		
		JLabel lblTitre = new JLabel("Titre recherché");
		lblTitre.setBounds(15, 22, 106, 16);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setBounds(133, 16, 304, 28);
		textFieldTitre.setColumns(10);
		
		btnEnregistrer = new JButton("Rechercher");
		btnEnregistrer.setBounds(80, 56, 128, 29);
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actions recherche ouvrage
				String titre = textFieldTitre.getText();
				if (titre.isEmpty())
					Message.message("Le titre ne peut pas être vide", Controleur.attention);
				else {
					getControleur().fermerVue(VueRechTitre.this);
					getControleur().affRechTitre(titre);
				}
			}
		});
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(282, 56, 93, 29);
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueRechTitre.this);
				getControleur().menuBiblio();
			}
		});
		content.setLayout(null);
		
		content.add(lblTitre);
		content.add(textFieldTitre);
		content.add(btnEnregistrer);
		content.add(btnAnnuler);
		getFrame().setVisible(true);
	}
	
	public void setEtat(int etat) {
		super.setEtat(etat);
		switch (etat){
		case initiale:
			textFieldTitre.setText("");
			break;
		}
	}

}

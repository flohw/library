import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class VueRechAuteur extends Vue {

	private static final long serialVersionUID = 1L;

	private JPanel content;
	
	private JList listAuteurs;
	private JScrollPane scrollAuteurs;
	
	private JButton btnTerminer, btnRechercher;

	private DefaultListModel modeleAuteurs = new DefaultListModel();
	
	public VueRechAuteur(Controleur controleur) {
		super (controleur);
		content = new JPanel();
		setTitle("Recherche par Auteur");
		setBounds(100, 100, 544, 300);
		setContentPane(content);
		content.setLayout(null);
		
		JLabel lblAuteurs = new JLabel("Auteurs");
		lblAuteurs.setBounds(65, 76, 101, 15);
		
		listAuteurs = new JList(modeleAuteurs);
		scrollAuteurs = new JScrollPane(listAuteurs);
		scrollAuteurs.setBounds(165, 32, 294, 113);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(124, 204, 120, 25);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getControleur().fermerVue(VueRechAuteur.this);
				getControleur().affRechAuteur();
			}
		});
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setBounds(317, 204, 120, 25);
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getControleur().fermerVue(VueRechAuteur.this);
			}
		});
	
		content.setLayout(null);
		content.add(lblAuteurs);
		content.add(btnRechercher);
		content.add(btnTerminer);
		content.add(scrollAuteurs);
	}
	
	public void setEtat(int etat) {
		super.setEtat(etat);
		switch (etat) {
		case initiale:
			//ICI FAIRE UNE RECHERCHE DES AUTEURS, BOITE DIALOGUE AVEC
			//POSSIBILITE DE CREER UN OUVRAGE POUR CREER UN AUTEUR SI JAMAIS IL N'Y 
			//AUCUNE AUTEUR
			break;
		case finale:
			break;
		}
	}

}

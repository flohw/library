import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class VueRechAuteur extends Vue {

	private static final long serialVersionUID = 1L;

	private JPanel content;
	
	private JList listAuteurs;
	private JScrollPane scrollAuteurs;
	private DefaultListModel modeleAuteurs = new DefaultListModel();
	
	private JButton btnTerminer, btnRechercher;
	
	public VueRechAuteur(Controleur controleur) {
		super (controleur);
		content = new JPanel();
		getFrame().setTitle("Recherche par Auteur");
		getFrame().setBounds(100, 100, 470, 220);
		getFrame().setContentPane(content);
		
		JLabel lblAuteurs = new JLabel("Auteurs");
		lblAuteurs.setBounds(6, 76, 101, 15);
		
		listAuteurs = new JList(modeleAuteurs);
		listAuteurs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollAuteurs = new JScrollPane(listAuteurs);
		scrollAuteurs.setBounds(86, 31, 351, 113);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(57, 156, 120, 25);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = listAuteurs.getSelectedIndex();
				if (index == -1)
					Message.message("Vous devez sélectionner un auteur", Controleur.attention);
				else {
					getControleur().fermerVue(VueRechAuteur.this);
					getControleur().affRechAuteur(modeleAuteurs.get(index).toString());
				}
			}
		});
		
		for (Auteur a : getControleur().getAuteurs())
			modeleAuteurs.addElement(a.getAuteur());
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setBounds(250, 156, 120, 25);
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getControleur().fermerVue(VueRechAuteur.this);
				getControleur().menuBiblio();
			}
		});
	
		content.setLayout(null);
		content.add(lblAuteurs);
		content.add(btnRechercher);
		content.add(btnTerminer);
		content.add(scrollAuteurs);
		
		getFrame().setVisible(true);
	}

}

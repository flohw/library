import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class VueRechMotCle extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JLabel lblMotclef;
	
	private JScrollPane scrollMC;
	private JList listMC;
	
	private DefaultListModel modeleMC = new DefaultListModel();
	
	private JButton btnRechercher;
	private JButton btnAnnuler;

	public VueRechMotCle(Controleur controleur) {
		super (controleur);
		content = new JPanel();
		getFrame().setBounds(100, 100, 490, 210);
		getFrame().setTitle("Recherche par Mot Clé");
		getFrame().setContentPane(content);
		
		lblMotclef = new JLabel("Mots-Clefs");
		lblMotclef.setBounds(23, 76, 101, 15);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(49, 152, 120, 25);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = listMC.getSelectedIndex();
				if (index == -1)
					Message.message("Sélectionnez un mot clé", Controleur.attention);
				else {
					getControleur().fermerVue(VueRechMotCle.this);
					getControleur().affRechMC(modeleMC.get(index).toString());
				}
			}
		});
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(280, 152, 120, 25);
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getControleur().fermerVue(VueRechMotCle.this);
				getControleur().menuBiblio();
			}
		});
		
		listMC = new JList(modeleMC);
		listMC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMC.setValueIsAdjusting(true);
		scrollMC = new JScrollPane(listMC);
		scrollMC.setBounds(165, 27, 294, 113);
		
		content.setLayout(null);
		content.add(lblMotclef);
		content.add(btnRechercher);
		content.add(btnAnnuler);
		content.add(scrollMC);
		for(MotCle item : getControleur().getMotsCles())
			modeleMC.addElement(item.getMotcle());
		getFrame().setVisible(true);
	}
}

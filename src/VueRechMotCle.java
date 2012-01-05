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
		setBounds(100, 100, 534, 263);
		setTitle("Recherche par Mot Clé");
		setContentPane(content);
		content.setLayout(null);
		
		lblMotclef = new JLabel("Mots-Clefs");
		lblMotclef.setBounds(61, 72, 101, 15);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(102, 167, 120, 25);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getControleur().fermerVue(VueRechMotCle.this);
				getControleur().affRechMC();
			}
		});
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(333, 167, 120, 25);
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getControleur().fermerVue(VueRechMotCle.this);
			}
		});
		
		listMC = new JList(modeleMC);
		listMC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMC.setValueIsAdjusting(true);
		scrollMC = new JScrollPane(listMC);
		scrollMC.setBounds(165, 32, 294, 113);
		
		content.setLayout(null);
		content.add(lblMotclef);
		content.add(btnRechercher);
		content.add(btnAnnuler);
		content.add(scrollMC);
		for(String item : getControleur().lectureLignesFichier())
			modeleMC.addElement(item);
	}
	
	public void setEtat(int etat) {
		super.setEtat(etat);
		switch (etat) {
		case initiale:
			break;
		case finale:
			break;
		}
	}

}

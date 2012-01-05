import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class VueAfficheAuteur extends Vue {

	private static final long serialVersionUID = 1L;

	private JPanel content;
	
	private JButton btnNouvRech;
	private JList listArt;
	private JScrollPane scrollArt;
	private JList listOuvrage;
	private JScrollPane scrollOuvrage;
	private JLabel lblArt;
	private JLabel lblOuv;
	private JLabel lblRsultatDeLa;
	private JButton btnFermer;
	
	public VueAfficheAuteur(Controleur controleur) {
		super (controleur);
		content = new JPanel();
		content.setLayout(null);
		setTitle("Affichage des ouvrage/articles de l'auteur");
		setContentPane(content);
		setBounds(100, 100, 544, 456);
		
		lblRsultatDeLa = new JLabel("Résultat de la recherche");
		lblRsultatDeLa.setFont(new Font("Dialog", Font.BOLD, 15));
		lblRsultatDeLa.setBounds(201, 12, 204, 15);
		
		lblOuv = new JLabel("Ouvrages Associés");
		lblOuv.setBounds(12, 103, 129, 15);
		
		lblArt = new JLabel("Articles Associés");
		lblArt.setBounds(12, 268, 106, 15);
		
		scrollOuvrage = new JScrollPane();
		scrollOuvrage.setBounds(145, 61, 346, 103);
		
		listOuvrage = new JList();
		listOuvrage.setEnabled(false);
		
		scrollArt = new JScrollPane();
		scrollArt.setBounds(145, 227, 346, 103);
		
		listArt = new JList();
		listArt.setEnabled(false);
		
		btnNouvRech = new JButton("Nouvelle Recherche");
		btnNouvRech.setBounds(74, 370, 160, 25);
		btnNouvRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueAfficheAuteur.this);
				getControleur().rechAuteur();
			}
		});
		
		btnFermer = new JButton("Fermer");
		btnFermer.setBounds(305, 370, 160, 25);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueAfficheAuteur.this);
			}
		});
		
		content.add(btnFermer);
		content.add(btnNouvRech);
		scrollArt.setColumnHeaderView(listArt);
		content.add(scrollArt);
		scrollOuvrage.setViewportView(listOuvrage);
		content.add(scrollOuvrage);
		content.add(lblArt);
		content.add(lblOuv);
		content.add(lblRsultatDeLa);
	}
	
	public void setEtat(int etat) {
		switch (etat) {
		case initiale:
			break;
		case finale:
			break;
		}
	}
}

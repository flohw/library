import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class VueAfficheMC extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JLabel lblRsultatDeLa;
	private JList listArt;
	private JLabel lblOuv;
	private JLabel lblArt;
	private JScrollPane scrollOuvrage;
	private JList listOuvrage;
	private JScrollPane scrollArt;
	private JButton btnNouvRech;
	private JButton btnFermer;

	public VueAfficheMC(Controleur controleur) {
		super (controleur);
		content = new JPanel();
		setTitle("Ouvrages/Articles associés au mot clé ");
		setBounds(100, 100, 544, 456);
		content.setLayout(null);
		setContentPane(content);
		
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
				getControleur().fermerVue(VueAfficheMC.this);
				getControleur().rechMotCle();
			}
		});
		
		btnFermer = new JButton("Fermer");
		btnFermer.setBounds(305, 370, 160, 25);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueAfficheMC.this);
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

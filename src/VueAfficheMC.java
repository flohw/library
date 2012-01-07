import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.Observable;


public class VueAfficheMC extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel content;
	
	private JLabel lblOuv;
	private JLabel lblArt;
	
	private JScrollPane scrollOuvrage;
	private JList listOuvrage;
	private JList listArt;
	private JScrollPane scrollArt;
	
	private DefaultListModel modeleArticles = new DefaultListModel();
	private DefaultListModel modeleOuvrages = new DefaultListModel();
	private JButton btnNouvRech;
	private JButton btnFermer;
	
	private MotCle _mot;
	private JLabel lvlRsultatDeLa;

	public VueAfficheMC(Controleur controleur) {
		super (controleur);
		content = new JPanel();
		content.setLayout(null);
		getFrame().setTitle("Affichage des ouvrage/articles de l'auteur");
		getFrame().setContentPane(content);
		getFrame().setBounds(100, 100, 525, 420);
		
		lvlRsultatDeLa = new JLabel("Résultat de la recherche pour ");
		lvlRsultatDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lvlRsultatDeLa.setFont(new Font("Dialog", Font.BOLD, 15));
		lvlRsultatDeLa.setBounds(12, 12, 479, 15);
		
		lblOuv = new JLabel("Ouvrages Associés");
		lblOuv.setBounds(12, 103, 129, 15);
		
		lblArt = new JLabel("Articles Associés");
		lblArt.setBounds(12, 268, 121, 15);
		
		listOuvrage = new JList(modeleOuvrages);
		scrollOuvrage = new JScrollPane(listOuvrage);
		scrollOuvrage.setBounds(145, 61, 346, 132);
		
		listArt = new JList(modeleArticles);
		scrollArt = new JScrollPane(listArt);
		scrollArt.setBounds(145, 227, 346, 132);
		
		btnNouvRech = new JButton("Nouvelle Recherche");
		btnNouvRech.setBounds(67, 371, 160, 25);
		btnNouvRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueAfficheMC.this);
				getControleur().rechAuteur();
			}
		});
		
		btnFermer = new JButton("Fermer");
		btnFermer.setBounds(298, 371, 160, 25);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueAfficheMC.this);
				getControleur().menuBiblio();
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
		content.add(lvlRsultatDeLa);
		
		getFrame().setVisible(true);
	}
	
	public void setMotCle(MotCle mc) { _mot = mc; }
	public MotCle getMotCle() { return _mot; }
	
	public void alimente(MotCle mot) {
		modeleArticles.clear();
		modeleOuvrages.clear();
		lvlRsultatDeLa.setText(lvlRsultatDeLa.getText() +
				getMotCle().getMotcle());
		for (String a : getMotCle().getArticles().keySet())
			modeleArticles.addElement(getMotCle().getArticle(a).afficheInfos());
		
		for (String o : getMotCle().getOuvrages().keySet())
			modeleOuvrages.addElement(getMotCle().getOuvrage(o).afficheInfos());
	}
	
	public void update(Observable observable, Object objet) {
		// maj de la vue lorque l'ouvrage a été modifié
		this.alimente(getMotCle());
		super.update(observable,  objet);
	}
}

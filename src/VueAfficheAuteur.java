import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.SwingConstants;


public class VueAfficheAuteur extends Vue {

	private static final long serialVersionUID = 1L;

	private JPanel content;
	
	private JButton btnNouvRech;
	
	private JList listOuvrage;
	private JList listArt;
	private JScrollPane scrollArt;
	private JScrollPane scrollOuvrage;
	
	private DefaultListModel modeleArticles = new DefaultListModel();
	private DefaultListModel modeleOuvrages = new DefaultListModel();
	
	private JLabel lblArt;
	private JLabel lblOuv;
	private JLabel lblRsultatDeLa;
	private JButton btnFermer;
	
	private Auteur _auteur;
	
	public VueAfficheAuteur(Controleur controleur) {
		super (controleur);
		content = new JPanel();
		content.setLayout(null);
		getFrame().setTitle("Affichage des ouvrage/articles de l'auteur");
		getFrame().setContentPane(content);
		getFrame().setBounds(100, 100, 525, 420);
		
		lblRsultatDeLa = new JLabel("Résultat de la recherche pour ");
		lblRsultatDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblRsultatDeLa.setFont(new Font("Dialog", Font.BOLD, 15));
		lblRsultatDeLa.setBounds(12, 12, 479, 15);
		
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
				getControleur().fermerVue(VueAfficheAuteur.this);
				getControleur().rechAuteur();
			}
		});
		
		btnFermer = new JButton("Fermer");
		btnFermer.setBounds(298, 371, 160, 25);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueAfficheAuteur.this);
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
		content.add(lblRsultatDeLa);
		
		getFrame().setVisible(true);
	}
	
	public void setAuteur(Auteur aut) { _auteur = aut; }
	public Auteur getAuteur() { return _auteur; }
	
	public void alimente(Auteur aut, HashMap<String, Ouvrage> ouv, HashMap<String, Article> art) {
		modeleOuvrages.clear();
		modeleArticles.clear();
		lblRsultatDeLa.setText(lblRsultatDeLa.getText() + aut.getAuteur());
		for (String isbn : ouv.keySet())
			modeleOuvrages.addElement(ouv.get(isbn).afficheInfos());
		for (String id : art.keySet())
			modeleArticles.addElement(art.get(id).afficheInfos());
		
	}
	
	public void update(Observable observable, Object objet) {
		// maj de la vue lorque l'ouvrage a été modifié
		this.alimente(getAuteur(), getAuteur().getOuvrages(), getAuteur().getArticles());
		super.update(observable,  objet);
	}
}

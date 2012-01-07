import javax.swing.JLabel;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;


public class VueAfficheAuteur extends Vue {

	private static final long serialVersionUID = 1L;

	private JPanel content;
	
	private JButton btnNouvRech;
	private JScrollPane scrollArt;
	private JScrollPane scrollOuvrage;
	
	private JLabel lblArt;
	private JLabel lblOuv;
	private JLabel lblRsultatDeLa;
	private JButton btnFermer;
	
	private Auteur _auteur;
	private JTextArea textOuvrages;
	private JTextArea textArticles;
	
	public VueAfficheAuteur(Controleur controleur) {
		super (controleur);
		content = new JPanel();
		content.setLayout(null);
		getFrame().setTitle("Affichage des ouvrage/articles de l'auteur");
		getFrame().setContentPane(content);
		getFrame().setBounds(100, 100, 580, 440);
		
		lblRsultatDeLa = new JLabel("Résultat de la recherche pour ");
		lblRsultatDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblRsultatDeLa.setFont(new Font("Dialog", Font.BOLD, 15));
		lblRsultatDeLa.setBounds(12, 12, 479, 15);
		
		lblOuv = new JLabel("Ouvrages Associés");
		lblOuv.setBounds(12, 103, 129, 15);
		
		lblArt = new JLabel("Articles Associés");
		lblArt.setBounds(12, 268, 121, 15);
		
		textOuvrages = new JTextArea();
		textOuvrages.setEditable(false);
		
		scrollOuvrage = new JScrollPane(textOuvrages);
		scrollOuvrage.setBounds(145, 61, 346, 132);
		
		textArticles = new JTextArea();
		textArticles.setEditable(false);
		
		scrollArt = new JScrollPane(textArticles);
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
		content.add(scrollArt);
		content.add(scrollOuvrage);
		content.add(lblArt);
		content.add(lblOuv);
		content.add(lblRsultatDeLa);
		
		getFrame().setVisible(true);
	}
	
	public void setAuteur(Auteur aut) { _auteur = aut; }
	public Auteur getAuteur() { return _auteur; }
	
	public void alimente(Auteur aut, HashMap<String, Ouvrage> ouv, HashMap<String, Article> art) {
		textOuvrages.setText("");
		textArticles.setText("");
		lblRsultatDeLa.setText(lblRsultatDeLa.getText() + aut.getAuteur());
		for (String isbn : ouv.keySet())
			textOuvrages.append(ouv.get(isbn).afficheInfos());
		for (String id : art.keySet())
			textArticles.append(art.get(id).afficheInfos());
		
	}
	
	public void update(Observable observable, Object objet) {
		// maj de la vue lorque l'ouvrage a été modifié
		this.alimente(getAuteur(), getAuteur().getOuvrages(), getAuteur().getArticles());
		super.update(observable,  objet);
	}
}

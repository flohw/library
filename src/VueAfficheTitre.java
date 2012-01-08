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


public class VueAfficheTitre extends Vue {

	private static final long serialVersionUID = 1L;

	private JPanel content;
	
	private JButton btnNouvRech;
	private JScrollPane scrollArt;
	private JScrollPane scrollOuvrage;
	
	private JLabel lblArt;
	private JLabel lblOuv;
	private JLabel lblRsultatDeLa;
	private JButton btnFermer;
	
	private HashMap<Integer, Ouvrage> _ouvrages;
	private HashMap<String, Article> _articles;
	private String _recherche;
	private JTextArea textOuvrages;
	private JTextArea textArticles;
	
	public VueAfficheTitre(Controleur controleur) {
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
				getControleur().fermerVue(VueAfficheTitre.this);
				getControleur().rechTitre();
			}
		});
		
		btnFermer = new JButton("Fermer");
		btnFermer.setBounds(298, 371, 160, 25);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueAfficheTitre.this);
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
	
	public void setOuvrages(HashMap<Integer, Ouvrage> ouv) { _ouvrages = ouv; }
	public HashMap<Integer, Ouvrage>getOuvrages() { return _ouvrages; }
	public void setArticles(HashMap<String, Article> art) { _articles = art; }
	public HashMap<String, Article> getArticles() { return _articles; }
	public void setRecherche(String rech) { _recherche = rech; }
	public String getRecherche() { return _recherche; }
	
	public void alimente(String recherche, HashMap<Integer, Ouvrage> ouvrages, HashMap<String, Article> articles) {
		textOuvrages.setText("");
		textArticles.setText("");
		lblRsultatDeLa.setText(lblRsultatDeLa.getText() + getRecherche());
		if (ouvrages.isEmpty())
			textOuvrages.setText("Aucun ouvrage correspondant");
		else
			for (Integer isbn : ouvrages.keySet())
				textOuvrages.append(ouvrages.get(isbn).afficheInfos());
		if (articles.isEmpty())
			textArticles.setText("Aucun article correspondant");
		else
			for (String id : articles.keySet())
				textArticles.append(articles.get(id).afficheInfos());
		
	}
	
	public void update(Observable observable, Object objet) {
		// maj de la vue lorque l'ouvrage a été modifié
		this.alimente(getRecherche(), getOuvrages(), getArticles());
		super.update(observable,  objet);
	}
}

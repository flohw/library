import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.Observable;
import javax.swing.JTextArea;


public class VueAfficheMC extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel content;
	
	private JLabel lblOuv;
	private JLabel lblArt;
	
	private JScrollPane scrollOuvrage;
	private JScrollPane scrollArt;
	
	private JButton btnNouvRech;
	private JButton btnFermer;
	
	private JTextArea textOuvrages;
	private JTextArea textArticles;
	
	private MotCle _mot;
	private JLabel lvlRsultatDeLa;

	public VueAfficheMC(Controleur controleur) {
		super (controleur);
		content = new JPanel();
		content.setLayout(null);
		getFrame().setTitle("Affichage des ouvrage/articles de l'auteur");
		getFrame().setContentPane(content);
		getFrame().setBounds(100, 100, 525, 450);
		
		lvlRsultatDeLa = new JLabel("Résultat de la recherche pour ");
		lvlRsultatDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lvlRsultatDeLa.setFont(new Font("Dialog", Font.BOLD, 15));
		lvlRsultatDeLa.setBounds(12, 12, 479, 15);
		
		lblOuv = new JLabel("Ouvrages Associés");
		lblOuv.setBounds(12, 103, 129, 15);
		
		lblArt = new JLabel("Articles Associés");
		lblArt.setBounds(12, 268, 121, 15);
		scrollOuvrage = new JScrollPane();
		scrollOuvrage.setBounds(145, 61, 346, 132);
		scrollArt = new JScrollPane();
		scrollArt.setBounds(145, 227, 346, 132);
		
		btnNouvRech = new JButton("Nouvelle Recherche");
		btnNouvRech.setBounds(67, 371, 160, 25);
		btnNouvRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueAfficheMC.this);
				getControleur().rechMotCle();
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
		content.add(scrollArt);
		
		textArticles = new JTextArea();
		textArticles.setEditable(false);
		scrollArt.setViewportView(textArticles);
		content.add(scrollOuvrage);
		
		textOuvrages = new JTextArea();
		textOuvrages.setEditable(false);
		scrollOuvrage.setViewportView(textOuvrages);
		content.add(lblArt);
		content.add(lblOuv);
		content.add(lvlRsultatDeLa);
		
		getFrame().setVisible(true);
	}
	
	public void setMotCle(MotCle mc) { _mot = mc; }
	public MotCle getMotCle() { return _mot; }
	
	public void alimente(MotCle mot) {
		textArticles.setText("");
		textOuvrages.setText("");
		lvlRsultatDeLa.setText(lvlRsultatDeLa.getText() + getMotCle().getMotcle());
		if (getMotCle().getArticles().isEmpty())
			textArticles.setText("Aucun article correspondant");
		else
			for (String a : getMotCle().getArticles().keySet())
				textArticles.append(getMotCle().getArticle(a).afficheInfos());
		if (getMotCle().getOuvrages().isEmpty())
			textOuvrages.setText("Aucun ouvrage correspondant");
		else
			for (Integer o : getMotCle().getOuvrages().keySet())
				textOuvrages.append(getMotCle().getOuvrage(o).afficheInfos());
	}
	
	public void update(Observable observable, Object objet) {
		// maj de la vue lorque l'ouvrage a été modifié
		this.alimente(getMotCle());
		super.update(observable,  objet);
	}
}

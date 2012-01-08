import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;


public class Auteur extends Observable implements Serializable {

	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************
	private static final long serialVersionUID = 1L;
	private String _prenom;
	private String _nom;
	private HashMap<Integer, Ouvrage> _ouvrages;
	private HashMap<String, Article> _articles;

	
	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************

	/**
	 * Constructeur. 
	 * @param prenom
	 * @param nom
	 */
	public Auteur(String nom, String prenom) {
		this.setAuteur(nom, prenom);
		this.setOuvrages(new HashMap<Integer, Ouvrage>());
		this.setArticles(new HashMap<String, Article>());
	} // Fin Constructeur
	
	private void setAuteur(String nom, String prenom) { setNom(nom); setPrenom(prenom); }
	private void setNom(String nom) { _nom = nom; }
	private void setPrenom(String prenom) { _prenom = prenom; }
	private void setOuvrages(HashMap<Integer, Ouvrage> ouvrages) { _ouvrages = ouvrages; }
	private void setOuvrage(Integer isbn, Ouvrage ouv) { getOuvrages().put(isbn, ouv); }
	private void setArticles(HashMap<String, Article> articles) { _articles = articles; }
	private void setArticle(String titre, Article art) { getArticles().put(titre, art); }
	public String getAuteur() { return getPrenom() + " " + getNom(); }
	public String getNom() { return _nom; }
	public String getPrenom() { return _prenom; }
	public HashMap<Integer, Ouvrage> getOuvrages() { return _ouvrages; }
	public HashMap<String, Article> getArticles() { return _articles; }
	public Ouvrage getOuvrage(String isbn) { return getOuvrages().get(isbn); }
	public Article getArticle(String titre) { return getArticles().get(titre); }
	
	public void ajouterOuvrage(Integer integer, Ouvrage ouvrage) {
		setOuvrage(integer, ouvrage);
		ouvrage.ajouterAuteur(this);
	}
	
	public void ajouterArticle(String titre, Article article) {
		setArticle(titre, article);
		article.ajouterAuteur(this);
	}

	public void notifierObservateurs() { this.setChanged(); this.notifyObservers(); }
	
} // Fin Classe Ouvrage

import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;


public class MotCle extends Observable implements Serializable {

	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************
	private static final long serialVersionUID = 1L;
	private String _mot;
	private HashMap<String, Article> _articles;
	private HashMap<Integer, Ouvrage> _ouvrages;
	
	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************

	/**
	 * Constructeur. 
	 */
	
	public MotCle(String mot) 
	{
		this.setMot(mot);
		this.setArticles(new HashMap<String, Article>());
		this.setOuvrages(new HashMap<Integer, Ouvrage>());
	} 
	
	private void setArticle(String titre, Article article) { _articles.put(titre, article); }
	private void setArticles(HashMap<String, Article> articles) { _articles = articles; }
	private void setOuvrage(Integer isbn, Ouvrage ouvrage) { _ouvrages.put(isbn, ouvrage); }
	private void setOuvrages(HashMap<Integer, Ouvrage> ouvrages) { _ouvrages = ouvrages; }
	private void setMot(String mot) { _mot = mot; }
	
	public String getMotcle() { return _mot; }
	public HashMap<String, Article> getArticles() { return _articles; }
	public HashMap<Integer, Ouvrage> getOuvrages() { return _ouvrages; }
	public Ouvrage getOuvrage(Integer o) { return _ouvrages.get(o); }
	public Article getArticle(String titre) { return _articles.get(titre); }

	public Article ajouterArticle(Article article) {
		this.setArticle(article.getTitre(), article);
		this.notifierObservateurs();
		return article;
	}
	
	public void notifierObservateurs() { this.setChanged(); this.notifyObservers(); }

	public void ajouterOuvrage(Integer integer, Ouvrage ouvrage) {
		setOuvrage(integer, ouvrage);
		ouvrage.ajouterMotCle(this);
	}
}

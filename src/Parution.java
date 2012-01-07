import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;


public class Parution extends Observable implements Serializable {

	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************

	private static final long serialVersionUID = 1L;
	private Integer _identifiant;
	private String _titre;
	private HashMap<String, Article> _articles;
	private Periodique _periodique;
	
	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************

	/**
	 * Constructeur. 
	 */
	
	public Parution(Integer identifiant, String titre) 
	{
		this.setId(identifiant);
		this.setTitre(titre);
		this.setArticles(new HashMap<String, Article>());
		this.setPeriodique(null);
	}

	public Article ajouterArticle(Article article) {
			this.setArticle(article.getTitre(), article);
			this.notifierObservateurs();
			return article;
		}
	
	private void setArticle(String titre, Article article) { _articles.put(titre, article); }
	private void setArticles(HashMap<String, Article> articles) { _articles = articles; }
	private void setId(Integer identifiant) { _identifiant = identifiant; }
	private void setTitre(String titre) { _titre = titre; }
	public void setPeriodique(Periodique periodique) { _periodique = periodique; }
	public Integer getId() { return _identifiant; }
	public String getTitre() { return _titre; }
	public Periodique getPeriodique() { return _periodique; }
	public HashMap<String, Article> getArticles() { return _articles; }
	public Article getArticle(String titre) { return getArticles().get(titre); }
	
	public int getNbArticles() { return _articles.size(); }
	
	public void notifierObservateurs() { this.setChanged(); this.notifyObservers(); }
	
	public String afficheInfos() {
		String r = getTitre() + "\n\t" + getNbArticles() + " articles :\n";
		for (String id : getArticles().keySet())
			r += "\t\t" + getArticle(id).getTitre() + ", page " + getArticle(id).getPage() + "\n";
		return r;
	}
	
	public String afficheParution() {
		return this.getId() + " - " + getTitre();
	}
	
}


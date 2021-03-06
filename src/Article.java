import java.io.Serializable;
import java.util.HashSet;
import java.util.Observable;


public class Article extends Observable implements Serializable {

	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************
	private static final long serialVersionUID = 1L;
	private String _titre;
	private Integer _page;
	private HashSet<Auteur> _auteurs;
	private Parution _parution;
	private HashSet<MotCle> _mots;
	
	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************

	/**
	 * Constructeur. 
	 */
	
	public Article(String titre, Integer page, Parution parution) 
	{
		this.setTitre(titre);
		this.setPage(page);
		this.setParution(parution);
		this.setAuteurs(new HashSet<Auteur>());
		this.setMots(new HashSet<MotCle>());
	} 

	private void setMot(MotCle mot) { _mots.add(mot); }
	private void setMots(HashSet<MotCle> mots) { _mots = mots; }
	private void setAuteur(Auteur auteur) { _auteurs.add(auteur); }
	private void setAuteurs(HashSet<Auteur> auteurs) { _auteurs = auteurs; }
	private void setParution(Parution parution) { _parution = parution; }
	private void setPage(Integer page) { _page = page; }
	private void setTitre(String titre) { _titre = titre; }
	public Integer getPage() { return _page; }
	public String getTitre() { return _titre; }
	public Parution getParution() { return _parution; }
	public HashSet<Auteur> getAuteurs() { return _auteurs; }
	public HashSet<MotCle> getMotsCles() { return _mots; }
	public MotCle getMotCle(MotCle mot) {
		MotCle r = null;
		for (MotCle m : getMotsCles())
		{
			if (m.equals(mot))
				r = m;
		}
		return r;
	}
	public Auteur getAuteur(Auteur auteur) {
		Auteur aut = null;
		for (Auteur a : getAuteurs()){
			if (a.equals(auteur))
				aut = a;
		}
		return aut;
	}

	public Parution ajouterParution(Integer identifiant, String titre) {
		Parution parution = new Parution(identifiant, titre);
		this.setParution(parution);
		this.notifierObservateurs();
		return parution;
	}
	public void ajouterMC(MotCle mot) {
		this.setMot(mot);
		mot.ajouterArticle(this);
	}
	public void ajouterAuteur(Auteur auteur) {
		this.setAuteur(auteur);
	}

	public void notifierObservateurs() { this.setChanged(); this.notifyObservers(); }

	public String afficheInfos() {
		String r = "Periodique : "+ getParution().getPeriodique().getNom() + ",\n";
		r += "Parution : " + getParution().getTitre() + ", Titre : " + getTitre() + ", Page " + getPage() + "\n";
		r += "-------------------------------------------------------------------\n";
		return r;
	}
}

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;

/**
 * Classe de gestion d'ouvrage.
 * @author IUT, refactoré par E. Ceret
 * @version 2.0
 */
public class Ouvrage extends Observable implements Serializable {

	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************

	private static final long serialVersionUID = 1L;
	
	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************

	private String _isbn;
	private String _titre;
	private HashSet<Auteur> _auteurs;
	private HashSet<MotCle> _motCles;
	private String _editeur;
	private GregorianCalendar _dateEdition;
	private int _derNumExemplaire;

	// Attributs d'Association
	private HashMap<Integer, Exemplaire> _exemplaires;
	
	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************

	public Ouvrage(String isbn, String titre, String editeur, GregorianCalendar dateEdition) {
		this.setIsbn(isbn);
		this.setTitre(titre);
		this.setAuteurs(new HashSet<Auteur>());
		this.setMotCles(new HashSet<MotCle>());
		this.setEditeur(editeur);
		this.setDateEdition(dateEdition);
		this.setExemplaires(new HashMap<Integer, Exemplaire>());
		this.setDerNumExemplaire(0);
		
	} // Fin Constructeur

	// ************************************************************************************************************
	// Méthodes privées
	// ************************************************************************************************************
	
	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs
	
	private void setMotCles(HashSet<MotCle> motCles) { _motCles = motCles; }
	private void setMotCle(MotCle mot) { getMotCles().add(mot); }
	private void setAuteurs(HashSet<Auteur> auteurs) { _auteurs = auteurs; }
	private void setAuteur(Auteur auteur) { _auteurs.add(auteur); }
	private void setDateEdition(GregorianCalendar date) { _dateEdition = date; }
	private void setEditeur(String editeur) { _editeur = editeur; }
	private void setExemplaires(HashMap<Integer, Exemplaire> exemplaires) { _exemplaires = exemplaires; }
	private void setExemplaire(int numero, Exemplaire exemplaire) { _exemplaires.put(numero, exemplaire); }
	private void setIsbn(String isbn) { this._isbn = isbn; }
	private void setTitre(String titre) { _titre = titre; }
	private void setDerNumExemplaire(int derNumExemplaire) { _derNumExemplaire  = derNumExemplaire ; }
	
	private int getDerNumExemplaire() { return _derNumExemplaire; }
	public HashSet<MotCle> getMotCles() { return _motCles; }
	public String getIsbn() { return _isbn; }
	public String getTitre() { return _titre; }
	public HashSet<Auteur> getAuteurs() { return _auteurs; }
	public String getEditeur() { return _editeur; }
	public GregorianCalendar getDateEdition() { return _dateEdition; }
	public Exemplaire getExemplaire(int numero) { return _exemplaires.get(numero); }
	public HashMap<Integer, Exemplaire> getExemplaires() { return _exemplaires; }
	public int getNbExemplaires() { return _exemplaires.size(); }
	
	public void notifierObservateurs() { this.setChanged(); this.notifyObservers(); }

	private int genererNumeroExemplaire() {
		this.setDerNumExemplaire(this.getDerNumExemplaire() + 1);
		return this.getDerNumExemplaire();
	}
	
	public Exemplaire ajouterExemplaire(GregorianCalendar dateReception, int stat) {
		int numero = this.genererNumeroExemplaire();
		if (this.verifDate(dateReception)){
			Exemplaire exemplaire = new Exemplaire(numero, dateReception, stat, this);
			this.setExemplaire(numero, exemplaire);
			this.notifierObservateurs();
			return exemplaire;
		}
		else
			return null;
	}
	
	public void ajouterAuteur (Auteur auteur) { this.setAuteur(auteur); }
	public void ajouterMotCle(MotCle motCle) { this.setMotCle(motCle); }

	private boolean verifDate(GregorianCalendar dateRecep) {
		return (dateRecep.after(_dateEdition) || dateRecep.equals(_dateEdition));
	}
	
	public int getNbExemplairesEnConsultation(){
		int nb = 0;
		for (int i : this.getExemplaires().keySet()){
			if (getExemplaire(i).estEnConsultation())
				nb = nb + 1;
		}
		return nb;
	}
	
	public int getNbExemplairesEmpruntable(){
		int nb = 0;
		for (int i : this.getExemplaires().keySet()){
			if (getExemplaire(i).estEmpruntable())
				nb = nb + 1;
		}
		return nb;
	}

	public String afficheInfos() {
		String r = "Titre : " + getTitre() + ", Nombre d'exemplaires : " + this.getNbExemplaires() + "\n";
		r += "-------------------------------------------------------------------\n";
		return r;
	}

	public String afficheOuvrage() { return getIsbn() + " - " + getTitre(); }
	
}

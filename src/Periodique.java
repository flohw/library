import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Observable;


public class Periodique extends Observable implements Serializable {

	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************

	private static final long serialVersionUID = 1L;
	private Integer _issn;
	private String _nom;
	private GregorianCalendar _dateAbonnement;
	private HashMap<Integer, Parution> _parutions;
	
	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************

	/**
	 * Constructeur. 
	 */
	
	public Periodique(Integer issn, String nom, GregorianCalendar dateAbonnement)  {
		this.setIssn(issn);
		this.setNom(nom);
		this.setDate(dateAbonnement);
		this.setParutions(new HashMap<Integer, Parution>());
	} 
	
	private void setNom(String nom)  { _nom = nom; }
	private void setIssn(Integer issn) { _issn = issn; }
	private void setDate(GregorianCalendar dateAbonnement) { _dateAbonnement = dateAbonnement; }
	public Integer getIssn() { return _issn; }
	public String getNom() { return _nom; }
	public GregorianCalendar getDate() { return _dateAbonnement; }
	public HashMap<Integer, Parution> getParutions() { return _parutions; }
	public Parution getParution(Integer id) { return _parutions.get(id); }
	
	public int getNbParutions() { return getParutions().size(); }

	public void setParution(Integer identifiant, Parution parution) {
		this.notifierObservateurs();
		_parutions.put(identifiant, parution);
		parution.setPeriodique(this);
	}
	
	private void setParutions(HashMap<Integer, Parution> parutions) {
		_parutions = parutions;
	}

	public void notifierObservateurs() { this.setChanged(); this.notifyObservers(); }

	public String afficheInfos() {
		return getIssn() + " - " + getNom();
	}

	
} // Fin ajouterParution
	

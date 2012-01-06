

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Observable;

/**
 * Classe de gestion d'un exemplaire d'un ouvrage
 * @author IUT, refactoré par E. Ceret
 * @version 2.0
 *
 */
public class Exemplaire extends Observable implements Serializable {

	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************
	private static final long serialVersionUID = 1L;
	final static int EMPRUNTABLE = 0, EN_CONSULTATION = 1, NON_DISPONIBLE = 2;
	
	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************

	private int _numero;
	private int _statut;
	private GregorianCalendar _dateReception;
	private Ouvrage _ouvrage;

	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************
	public Exemplaire(int numero, GregorianCalendar dateReception, int statut, Ouvrage ouvrage) {
		this.setNumero(numero);
		this.setDateReception(dateReception);
		this.setStatut(statut);
		this.setOuvrage(ouvrage);
		
	} // Fin Constructeur

	// ************************************************************************************************************
	// Méthodes privées
	// ************************************************************************************************************

	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs
	private void setNumero(int numero) { _numero = numero; }
	private void setDateReception(GregorianCalendar dateReception) { _dateReception = dateReception; }
	private void setOuvrage(Ouvrage ouvrage) { _ouvrage = ouvrage; }

	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************
	
	// ------------------------------------------------------------------------------------------------------------
	// Accesseurs
	public Ouvrage getOuvrage() { return _ouvrage; }
	public int getNumero() { return _numero; }
	public GregorianCalendar getDateReception() { return _dateReception; }
	public int getStatut() { return _statut; }

	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs
	public void setStatut(int statut) { _statut = statut; }


	// ------------------------------------------------------------------------------------------------------------
	// Prédicats d'Etat
	public boolean estEmpruntable() {
		return (this.getStatut() == Exemplaire.EMPRUNTABLE);
	}

	public boolean estNonDisponible() {
		return (this.getStatut() == Exemplaire.NON_DISPONIBLE);
	}

	public boolean estEnConsultation() {
		return (this.getStatut() == Exemplaire.EN_CONSULTATION);
	}

	// ------------------------------------------------------------------------------------------------------------
	// Pour les affichages
	
	public String libStatut() {
		if (this.getStatut() == Exemplaire.NON_DISPONIBLE) {
			return "non disponible";
		} else if (this.getStatut() == Exemplaire.EMPRUNTABLE) {
			return "empruntable";
		} else if (this.getStatut() == Exemplaire.EN_CONSULTATION) {
			return "consultable";
		} else {
			return "erreur dans le statut de l'exemplaire";
		}
	} // Fin libStatut

	public String afficheInfos() {
		return "Exemplaire numéro " + getNumero() + " reçu le " + ESDate.ecrireDate(getDateReception()) + " : " + libStatut() + "\n";
	}
	
	public void notifierObservateurs() { this.setChanged(); this.notifyObservers(); }

} // Fin Classe Exemplaire

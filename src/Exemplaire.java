

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
	/**
	 * Constructeur. Crée un exemplaire d'ouvrage .
	 * 
	 * @param numero		numéro de l'exemplaire dans l'ouvrage.
	 * @param dateReception	date de réception de cet exemplaire.
	 * @param statut  		statut de l'exemplaire (en consultation, empruntable)
	 * @param ouvrage		ouvrage dont cet exemplaire est un représentant.
	 */
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
	
	/**
	 * @param numero le numéro à affecter
	 */
	private void setNumero(int numero) {
		_numero = numero;
	}

	/**
	 * @param dateReception la date de réception àaffecter
	 */
	private void setDateReception(GregorianCalendar dateReception) {
		_dateReception = dateReception;
	}

	/**
	 * @param ouvrage l'ouvrage lié à l'exemplaire
	 */
	private void setOuvrage(Ouvrage ouvrage) {
		_ouvrage = ouvrage;
	} // Fin setOuvrage

	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************
	
	// ------------------------------------------------------------------------------------------------------------
	// Accesseurs
	
	/**
	 * @return l'ouvrage lié à l'exemplaire
	 */
	public Ouvrage getOuvrage() {
		return _ouvrage;
	}

	/**
	 * @return le numéro de l'exemplaire
	 */
	public int getNumero() {
		return _numero;
	}

	/**
	 * @return la date de réception de l'exemplaire
	 */
	public GregorianCalendar getDateReception() {
		return _dateReception;
	}

	/**
	 * @return le statut de l'exemplaire
	 */
	public int getStatut() {
		return _statut;
	}

	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs
	
	/**
	 * @param statut le statut à affecter
	 */
	public void setStatut(int statut) {
		_statut = statut;
	}


	// ------------------------------------------------------------------------------------------------------------
	// Prédicats d'Etat

	/**
	 * @return vrai si l'exemplaire est empruntable
	 */
	public boolean estEmpruntable() {
		return (this.getStatut() == Exemplaire.EMPRUNTABLE);
	}

	/**
	 * @return vrai si l'exemplaire est NON disponible
	 */
	public boolean estNonDisponible() {
		return (this.getStatut() == Exemplaire.NON_DISPONIBLE);
	}

	/**
	 * @return vrai si l'exemplaire est en consultation
	 */
	public boolean estEnConsultation() {
		return (this.getStatut() == Exemplaire.EN_CONSULTATION);
	}

	// ------------------------------------------------------------------------------------------------------------
	// Pour les affichages
	
	/**
	 * @return le libellé correspondant au statut courant de l'exemplaire
	 */
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

} // Fin Classe Exemplaire


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Classe de gestion des Dates (affichages et saisies)
 * @author IUT,  A.Culet pour les dates dans IHM graphique!
 * @version 1.0
 *
 */
public class ESDate  {
	
	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************

	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************

	/**
	 * Constructeur. Aucun traitement requis.
	 */
	public ESDate() {
	}
	
	// ************************************************************************************************************
	// Méthodes privées
	// ************************************************************************************************************

	// ------------------------------------------------------------------------------------------------------------
	// Traitements internes

	/**
	 * @return un entier représentant l'année courante.
	 */
	private static int anneeCourante() {
		// Instancie une variable date
		GregorianCalendar dateCourante = new GregorianCalendar();
		// Affecte la date courante à cette variable
		dateCourante.setTime(new Date());
		// Extrait l'année de cette variable et la renvoie
		return dateCourante.get(GregorianCalendar.YEAR);
	}
	
	/**
	 * @return un entier représentant le mois courant.
	 */
	private static int moisCourant() {
		// Instancie une variable date
		GregorianCalendar dateCourante = new GregorianCalendar(); 
		// Affecte la date courante à cette variable
		dateCourante.setTime(new Date());

		// Extrait le mois de cette variable et la renvoie
		return dateCourante.get(GregorianCalendar.MONTH);
	}	
	

	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************

	//	---------------------------------------------------------------------------------------------------------------------
	//	Gestion des dates
	/**
	 * permet la saisie d'une date au format MM/AAAA. Le mois doit être entre 1 et 12 
	 * et l'année entre 1900 et 2100. Le contrôle de cohérence entre les différents champs
	 * est effectué grâce à une conversion de la saisie en date. 
	 * 
	 * @return GregorianCalendar : la date saisie ou null si le format de la date est incorrect
	 */
	public static GregorianCalendar lireDate(String date)  {
		boolean ok = false;
		String[] champsDate ;
		int jour = 1;
		int mois = moisCourant();
		int an = anneeCourante();
		GregorianCalendar dateSaisie = null ;
		
		// Conversion de la chaîne en 2 variables mois, an. 
		champsDate = date.split("/");
		
        // On initialise ok à vrai. Toute erreur détectée le remettra à faux
        ok = true ;

		// Calcul du mois saisi (si pas saisi : valorisé avec le mois courant)
		if (champsDate.length >= 1) {
		    	try{
		    		mois = Integer.parseInt(champsDate[0]);
		    		if ((mois<1 )|| (mois>12)){ok = false;}
		    	} catch (Exception e) {
		        	ok = false ;
		    	}
		    }
		    
		// Calcul de l'année saisie (si pas saisie : valorisée avec l'année courante)
		if (champsDate.length == 2) {
		    	try{
		    		an = Integer.parseInt(champsDate[1]);
		    		if ((an<1900 )|| (an>2100)){ok = false;}
		    	} catch (Exception e) {
		        	ok = false ;
		    	}
		    }

		 // Conversion des trois champs saisis en date
		 if (ok) {
		    	dateSaisie = new GregorianCalendar(an, mois-1, jour);
		    	return  dateSaisie;
		    	}
		 else {return null; }
		
	} // Fin lireDate

	
	//	---------------------------------------------------------------------------------------------------------------------
	//	Affichages d'une date
	
	/**
	 * Convertit une date en chaine au format MM/AAAA. Si la date passée vaut null, la chaine résultante est "nul".
	 * @param date date à convertir
	 * @return chaine au formatMM/AAAA
	 */
	public static  String ecrireDate(GregorianCalendar date)   {
		if (date == null) {
		    return "nul";
		} else {
	       // return date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH)+1) + "/" + date.get(Calendar.YEAR);
	        return (date.get(Calendar.MONTH)+1) + "/" + date.get(Calendar.YEAR);
		}
	} // Fin ecrireDate
	
} // Fin de classe ESDate

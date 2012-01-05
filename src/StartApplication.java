

/**
 * Classe de gestion de l'application bibliothèque. Contient le main de l'application.
 * @author IUT,  refactoré par A. Culet
 * @version 3.0 application graphique *
 */
public class StartApplication {

	// ************************************************************************************************************
	// Constructeur de l'application
	// ************************************************************************************************************
	
	// ------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur de l'application. 
	 * Instancie le controleur
	 * Affiche la fenêtre principale
	 */
	public StartApplication() {
		
		// Creation de l'instance du controleur
		// récupération de tous les objets sérialisés de l'application
		Controleur controleur = new Controleur().restaure();
		// création affichage de la fenêtre principale
		controleur.menuBiblio();
		
	} // Fin Constructeur

	// ************************************************************************************************************
	// Programme principal
	// ************************************************************************************************************
	
	public static void main(String args[]) {
		new StartApplication();
	} // Fin main

} // Fin Classe StartApplication


import javax.swing.UIManager;




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
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new StartApplication();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // Fin main

} // Fin Classe StartApplication


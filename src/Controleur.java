
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
/**
 * Classe controleur et application (système)
 * @author IUT,   A. Culet
 * @version 1.0 
 */

//public class Controleur extends Observable implements Serializable{
public class Controleur implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * La classe Controleur est unique pour tous les cas d'utilisation
	 * Elle est également la classe "application" qui gère l'ensemble des objets de l'appli
	 */	
	// ************************************************************************************************************
		// Attributs
		// ************************************************************************************************************
	
		// Attributs d'Association
		// Ensemble des ouvrages de la bibliothèque
		private HashMap<String, Ouvrage> _ouvrages;
		private HashMap<String, Periodique> _periodiques;
		private HashSet<Auteur> _auteurs;
		private HashSet<MotCle> _motsCles;
		
		// les différentes fenêtres pour chaque fonctionnalité
		private VueMenuBiblio _vueMenuBiblio = null;
		
		// une seule fenêtre est active à la fois; les autres sont à null.
		// permet de connaître l'état des fenêtres de l'interface
		private VueSaisieOuvrage _vueSaisieOuvrage = null;
		private VueSaisieExemplaire _vueSaisieExemplaire = null;
		private VueConsulterOuvrage _vueConsulterOuvrage = null;
		private VueRechMotCle _vueRechMotCle = null;
		private VueRechAuteur _vueRechAuteur = null;
		private VueConsulterPeriodique _vueConsulterPeriodique = null;
		private VueNouveauPeriodique _vueNouveauPeriodique = null;
		private VueNouvelArticle _vueNouvelArticle = null;
		private VueNouvelleParution _vueNouvelleParution = null;
		private VueAfficheAuteur _vueAfficheAuteur = null;
		private VueAfficheMC _vueAfficheMC = null;

		// ************************************************************************************************************
		// Constructeur
		// ************************************************************************************************************

		public Controleur() {
			this.setOuvrages(new HashMap<String, Ouvrage>());
			this.setPeriodiques(new HashMap<String, Periodique>());
			this.setAuteurs(new HashSet<Auteur>());
			this.setMotsCles(new HashSet<MotCle>());
		} // Fin Controleur

		// ************************************************************************************************************
		// Méthodes privées
		// ************************************************************************************************************

		// ------------------------------------------------------------------------------------------------------------
		// Affecteurs
		
		// Setteur de variables
		private void setAuteurs(HashSet<Auteur> auteurs) { _auteurs = auteurs; }
		private void setMotsCles(HashSet<MotCle> motsCles) { _motsCles = motsCles; }
		private void setOuvrage(Ouvrage ouvrage, String isbn) { this.getOuvrages().put(isbn, ouvrage); }
		private void setPeriodique(Periodique periodique, String issn) { this.getPeriodiques().put(issn, periodique); }
		private void setOuvrages(HashMap<String, Ouvrage> ouvrages) { _ouvrages = ouvrages; }
		private void setPeriodiques(HashMap<String, Periodique> periodiques) { _periodiques = periodiques; }
		
		////////////////////////////////////////////////////////////////////////////
		// Setteur de vues
		////////////////////////////////////////////////////////////////////////////
		private void setVueMenuBiblio(VueMenuBiblio vue) { _vueMenuBiblio = vue; }
		private void setVueSaisieOuvrage(VueSaisieOuvrage vue) { _vueSaisieOuvrage = vue; }
		private void setVueSaisieExemplaire(VueSaisieExemplaire vue) { _vueSaisieExemplaire = vue; }
		private void setVueConsulterOuvrage(VueConsulterOuvrage vue) { _vueConsulterOuvrage = vue; }
		private void setVueRechMotCle(VueRechMotCle vue) { _vueRechMotCle = vue; }
		private void setVueRechAuteur(VueRechAuteur vue) { _vueRechAuteur = vue; }
		private void setVueConsulterPeriodique(VueConsulterPeriodique vue) { _vueConsulterPeriodique = vue; }
		private void setVueNouveauPeriodique(VueNouveauPeriodique vue) { _vueNouveauPeriodique = vue; }
		private void setVueNouvelArticle(VueNouvelArticle vue) { _vueNouvelArticle = vue; }
		private void setVueNouvelleParution(VueNouvelleParution vue) { _vueNouvelleParution = vue; }		
		private void setVueAfficheAuteur(VueAfficheAuteur vue) { _vueAfficheAuteur = vue; }		
		private void setVueAfficheMC(VueAfficheMC vue) { _vueAfficheMC = vue; }		
		
		// ------------------------------------------------------------------------------------------------------------
		// Accesseurs
		
		////////////////////////////////////////////////////////////////////////////
		// Getteur de variables
		////////////////////////////////////////////////////////////////////////////
		public HashMap<String, Ouvrage> getOuvrages() { return _ouvrages; }
		public HashMap<String, Periodique> getPeriodiques() { return _periodiques; }
		public HashSet<Auteur> getAuteurs() { return _auteurs; }
		public HashSet<MotCle> getMotsCles() { return _motsCles; }
		public Ouvrage getOuvrage(String isbn) { return this.getOuvrages().get(isbn); }
		public Periodique getPeriodique(String issn) { return this.getPeriodiques().get(issn); }
		public Auteur getAuteur(Auteur auteur) {
			Auteur newAuteur = null;
			for (Auteur aut : getAuteurs())
			{
				if (aut.equals(auteur))
					newAuteur = aut;
			}
			return newAuteur;
		}
		public MotCle getMotCle(String mc) {
			MotCle newMotCle = null;
			for (MotCle mot : getMotsCles())
			{
				if (mot.getMotcle() == mc)
					newMotCle = mot;
			}
			return newMotCle;
		}

		////////////////////////////////////////////////////////////////////////////
		// Getteur de vues
		////////////////////////////////////////////////////////////////////////////
		private VueMenuBiblio getVueMenuBiblio() { return _vueMenuBiblio ; }
		private VueSaisieOuvrage getVueSaisieOuvrage() { return _vueSaisieOuvrage ; }
		private VueSaisieExemplaire getVueSaisieExemplaire() { return _vueSaisieExemplaire ; }
		private VueConsulterOuvrage getVueConsulterOuvrage() { return _vueConsulterOuvrage ; }
		private VueRechMotCle getVueRechMotCle() { return _vueRechMotCle ; }
		private VueRechAuteur getVueRechAuteur() { return _vueRechAuteur ; }
		private VueConsulterPeriodique getVueConsulterPeriodique() { return _vueConsulterPeriodique ; }
		private VueNouveauPeriodique getVueNouveauPeriodique() { return _vueNouveauPeriodique ; }
		private VueNouvelArticle getVueNouvelArticle() { return _vueNouvelArticle ; }
		private VueNouvelleParution getVueNouvelleParution() { return _vueNouvelleParution ; }		
		private VueAfficheAuteur getVueAfficheAuteur() { return _vueAfficheAuteur ; }		
		private VueAfficheMC getVueAfficheMC() { return _vueAfficheMC ; }
		
		////////////////////////////////////////////////////////////////////////////
		// Affichage des CU
		////////////////////////////////////////////////////////////////////////////
		public void menuBiblio() {
			try {
				this.setVueMenuBiblio(new VueMenuBiblio(this));	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Consulter ouvrage
		public void consulterOuvrage() {
			try {
				this.setVueConsulterOuvrage (new VueConsulterOuvrage(this));
				this.getVueMenuBiblio().getFrame().setVisible(false); 	
				this.getVueConsulterOuvrage().setEtat(Vue.initiale);
				this.getVueConsulterOuvrage().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// SAISIR EXEMPLAIRE
		public void saisirExemplaire() {
			try {
				this.setVueSaisieExemplaire(new VueSaisieExemplaire(this));
				this.getVueMenuBiblio().getFrame().setVisible(false); 
				this.getVueSaisieExemplaire().setEtat(Vue.initiale);
				this.getVueSaisieExemplaire().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// SAISIR OUVRAGE
		public void saisirOuvrage() {
			try {
				this.setVueSaisieOuvrage(new VueSaisieOuvrage(this));
				this.getVueMenuBiblio().getFrame().setVisible(false); 
				this.getVueSaisieOuvrage().setEtat(Vue.initiale);
				this.getVueSaisieOuvrage().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//RECHERCHER MOT CLEF
		public void rechMotCle() {
			try {
				this.setVueRechMotCle(new VueRechMotCle(this));
				// le Menu est caché
				this.getVueMenuBiblio().getFrame().setVisible(false); 
				// la vue courante est VueRechMotCle
				this.getVueRechMotCle().setEtat(Vue.initiale);
				this.getVueRechMotCle().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//RECHERCHER AUTEUR
		public void rechAuteur() {
			try {
				this.setVueRechAuteur(new VueRechAuteur(this));
				// le Menu est caché
				this.getVueMenuBiblio().getFrame().setVisible(false); 
				// la vue courante est VueRechAuteur
				this.getVueRechAuteur().setEtat(Vue.initiale);
				this.getVueRechAuteur().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		

		//CONSULTER PERIODIQUES
		public void consulterPeriodique() {
			try {
				this.setVueConsulterPeriodique(new VueConsulterPeriodique(this));
				// le Menu est caché
				this.getVueMenuBiblio().getFrame().setVisible(false); 
				// la vue courante est VueConsulterPeriodique
				this.getVueConsulterPeriodique().setEtat(Vue.initiale);
				this.getVueConsulterPeriodique().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//NOUVEAU PERIODIQUE
		public void saisiePeriodique() {
			try {
				this.setVueNouveauPeriodique(new VueNouveauPeriodique(this));
				// le Menu est caché
				this.getVueMenuBiblio().getFrame().setVisible(false); 
				// la vue courante est VueNouveauPeriodique
				this.getVueNouveauPeriodique().setEtat(Vue.initiale);
				this.getVueNouveauPeriodique().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//NOUVEL ARTICLE
		public void saisieArticle() {
			try {
				this.setVueNouvelArticle(new VueNouvelArticle(this));
				this.getVueMenuBiblio().getFrame().setVisible(false); 
				this.getVueNouvelArticle().setEtat(Vue.initiale);
				this.getVueNouvelArticle().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//NOUVELLE PARUTION
		public void saisirParution() {
			try {
				this.setVueNouvelleParution(new VueNouvelleParution(this));
				// le Menu est caché
				this.getVueMenuBiblio().getFrame().setVisible(false); 
				// la vue courante est VueNouvelleParution
				this.getVueNouvelleParution().setEtat(Vue.initiale);
				this.getVueNouvelleParution().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//AFFICHER RECHERCHE AUTEUR
		public void affRechAuteur() {
			try {
				this.setVueAfficheAuteur(new VueAfficheAuteur(this));
				// le Menu est caché
				this.getVueMenuBiblio().getFrame().setVisible(false); 
				this.getVueAfficheAuteur().setEtat(Vue.initiale);
				this.getVueAfficheAuteur().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//AFFICHER RECHERCHE MC
		public void affRechMC() {
			try {
				this.setVueAfficheMC(new VueAfficheMC(this));
				// le Menu est caché
				this.getVueMenuBiblio().getFrame().setVisible(false); 
				this.getVueAfficheMC().setEtat(Vue.initiale);
				this.getVueAfficheMC().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		public void fermerVue (Vue vue) {
			if (vue instanceof VueMenuBiblio ) {	
			// Quitte l'aplication. Sauvegarde les objets du modèle
				this.sauve();
				System.exit(0);
				}
			vue.dispose();
			// le Menu est rendu de nouveau visible
			this.getVueMenuBiblio().getFrame().setVisible(true);
			this.resetVues();
		}
		
		// Restaure l'état de l'interface avec seule la fenêtre du Menu principal active
		private void resetVues() {
			this.setVueSaisieOuvrage(null); //CHECK
			this.setVueConsulterOuvrage(null); //CHECK
			this.setVueSaisieExemplaire(null); //CHECK
			this.setVueRechMotCle(null); //CHECK
			this.setVueRechAuteur(null); //CHECK
			this.setVueConsulterPeriodique(null); //CHECK
			this.setVueNouveauPeriodique(null); //CHECK
			this.setVueNouvelArticle(null); //CHECK
			this.setVueNouvelleParution(null); //CHECK
			this.setVueAfficheAuteur(null); //CHECK
			this.setVueAfficheMC(null); //CHECK
		}
		
		////////////////////////////////////////////////////////////////////////////
		// Opérations liées à la sérialisation des objets de l'application
		////////////////////////////////////////////////////////////////////////////
		public Controleur restaure() {
			try {
				FileInputStream fichier = new FileInputStream("Fsauv.ser");
				ObjectInputStream in = new ObjectInputStream(fichier);
				return((Controleur) in.readObject());
			} catch (Exception e) {
				Message dialog = new Message("Pbs de Restauration ou fichier non encore créé");
				dialog.setVisible(true);
				return this;
			} 
		}
		private void sauve() {
			try {
				FileOutputStream f = new FileOutputStream("Fsauv.ser");
				ObjectOutputStream out = new ObjectOutputStream(f);
				out.writeObject(this);
			} catch (Exception e) {
				Message dialog = new Message("Pb de Sauvegarde dans le fichier");
				dialog.setVisible(true);
			}
		}
		////////////////////////////////////////////////////////////////////////////
		// Opérations liées à l'application en réponse à une action de l'utilisateur dans une vue
		////////////////////////////////////////////////////////////////////////////
		
		public Ouvrage rechOuvrage(String isbn) {
			Ouvrage ouv = this.getOuvrage(isbn);
			if (ouv == null) {			
				int option = JOptionPane.showConfirmDialog(null, "Ouvrage inconnu, voulez-vous le créer ?",
						"Erreur Ouvrage", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if(option != JOptionPane.NO_OPTION )
				{
					if (this.getVueConsulterOuvrage() != null)
						this.getVueConsulterOuvrage().setEtat(Vue.alternate);
					if (this.getVueSaisieExemplaire() != null)
						this.getVueSaisieExemplaire().setEtat(Vue.alternate);
				}
			} else {
				if (this.getVueSaisieExemplaire() != null) {
					ouv.addObserver(this.getVueSaisieExemplaire());
					this.getVueSaisieExemplaire().setEtat(Vue.inter1);
					this.getVueSaisieExemplaire().alimente(ouv);
				}
				if (this.getVueConsulterOuvrage() != null) {
					this.getVueConsulterOuvrage().setEtat(Vue.inter1);
					this.getVueConsulterOuvrage().alimente(ouv);
				}
			}
			return ouv;
		} // Fin rechOuvrage
		
		public boolean nouvOuvrage(String isbn, String titre, HashSet<Auteur> auteurs, String editeur,
			GregorianCalendar dateEdition, HashSet<String> motsCles) {
			// vérification de la présence des infos obligatoires et du format de la date
			if (this.getOuvrage(isbn )== null) {
				// Instanciation de l'ouvrage
				// Ajout de l'ouvrage dans l'ensemble des ouvrages de la bibliothèque
				Ouvrage ouvrage = new Ouvrage(isbn, titre, editeur, dateEdition);
				this.setOuvrage(ouvrage, isbn);
				for (Auteur aut : auteurs)
				{
					if (getAuteur(aut) == null)
						this.getAuteurs().add(aut);
					aut.ajouterOuvrage(isbn, ouvrage);
				}
				MotCle mot = null;
				for (String mc : motsCles)
				{
					mot = getMotCle(mc);
					if (mot == null)
					{
						mot = new MotCle(mc);
						this.getMotsCles().add(mot);
					}
					mot.ajouterOuvrage(isbn, ouvrage);
				}
				Message dialog = new Message("Ouvrage enregistré");
				dialog.setVisible(true);
				this.fermerVue (this.getVueSaisieOuvrage());
				return true;
			} else {
				Message dialog = new Message("Ouvrage déjà présent");
				dialog.setVisible(true);
				return false;
			}
		}
		
		public void nouvExemplaire(Ouvrage ouv, String dateReception, String statut) {
			// vérification de la présence de la date et de son format
			if (dateReception.length() == 0 ){
					Message dialog = new Message("La date de réception est obligatoire");
					dialog.setVisible(true);
					}
			else {
				GregorianCalendar date = ESDate.lireDate (dateReception);
				if (date == null) {
					Message dialog = new Message("Le format de la date est incorrect");
					dialog.setVisible(true);
					}
				else {
					int statutEx;
					if (statut == "empruntable")
						statutEx = Exemplaire.EMPRUNTABLE ;
					else
						statutEx = Exemplaire.EN_CONSULTATION ;
				
					Exemplaire exemplaire = ouv.ajouterExemplaire(date, statutEx);
					if (exemplaire != null) {
						this.getVueSaisieExemplaire().setEtat(Vue.finale);
						Message dialog = new Message("Exemplaire enregistré");
						dialog.setVisible(true);
					} else {
						Message dialog = new Message("Date de Reception incorrecte / à la date d'Edition.");
						dialog.setVisible(true);
					}
				}
			}
		}
		
		public Periodique rechPeriodique(String issn) {
			Periodique per = this.getPeriodique(issn);
			if (per == null)
			{			
				int option = JOptionPane.showConfirmDialog(null, "Periodique inconnu, voulez-vous le créer ?",
						"Erreur Periodique", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if(option != JOptionPane.NO_OPTION )
				{
					if (this.getVueConsulterPeriodique() != null)
						this.getVueConsulterPeriodique().setEtat(Vue.alternate);
					if (this.getVueNouvelArticle() != null)
						this.getVueNouvelArticle().setEtat(Vue.alternate2);
					if (this.getVueNouvelleParution() != null)
						this.getVueNouvelleParution().setEtat(Vue.alternate);
					
				}
			}
			else
			{
				if (this.getVueConsulterPeriodique() != null)
					this.getVueConsulterPeriodique().alimente(per);
				if (this.getVueNouvelleParution() != null)
					this.getVueNouvelleParution().alimente(per);
			}
			return per;
		}
		
		public boolean nouveauPeriodique (String issn, String nom, GregorianCalendar date) {
			if (this.getPeriodique(issn) == null) {
				Periodique periodique = new Periodique(issn, nom, date);
				this.setPeriodique(periodique, issn);
				return true;
			} else {
				Message dialogue = new Message("Le périodique existe déjà");
				dialogue.setVisible(true);
				return false;
			}
		}
		
		
		public void nouvelleParution(Periodique pe, Integer id, String titre) {
			Parution pa = getPeriodique(pe.getIssn()).getParution(id);
			if (pa != null)
			{
				Message dialog = new Message("La parution existe deja pour le périodique");
				dialog.setVisible(true);
			}
			else
			{
				pe.setParution(id, new Parution(id, titre));			
				int option = JOptionPane.showConfirmDialog(null, "La parution a été enregistrée pour " + pe.getNom() +"" +
						", voulez-vous en créer une autre ?",
						"Nouvelle Parution", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if(option != JOptionPane.NO_OPTION)
				{
					if (this.getVueNouvelleParution() != null)
						this.getVueNouvelleParution().setEtat(Vue.initiale);
				}
				else
					this.getVueNouvelleParution().setEtat(Vue.finale);
			}
		}
		
		public Article rechArticle(Parution parution, String titre) {
			Article article = parution.getArticle(titre);
			if (article != null)
			{
				Message dg = new Message("Ce titre existe déjà pour cette parution");
				dg.setVisible(true);
			}
			else
				getVueNouvelArticle().setEtat(Vue.inter3);
			return article;
		}
		
		public Parution rechParution(Periodique pe, Integer id)
		{
			Parution pa = pe.getParution(id);
			if (pa == null)
			{		
				int option = JOptionPane.showConfirmDialog(null, "La parution n'existe pas, voulez-vous la créer ?",
						"Nouvelle Parution", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if(option == JOptionPane.YES_OPTION)
				{
					if (this.getVueNouvelArticle() != null)
						this.getVueNouvelArticle().setEtat(Vue.alternate);
				}
			}
			else
				this.getVueNouvelArticle().setEtat(Vue.inter2);
			return pa;
		}
		
		public void nouvArticle(String titre, Integer page, HashSet<Auteur> auteurs, HashSet<String> motsCles, Parution pa) {
			Article newArticle = new Article(titre, page, pa);
			pa.ajouterArticle(newArticle);
			Auteur newAuteur = null;
			for (Auteur aut : auteurs) {
				newAuteur = this.getAuteur(aut);
				if (newAuteur == null)
					newAuteur = new Auteur(aut.getNom(), aut.getPrenom());
				newArticle.ajouterAuteur(newAuteur);
			}
			MotCle mot = null;
			for (String mc : motsCles) {
				mot = getMotCle(mc);
				if (mot == null) {
					mot = new MotCle(mc);
					this.getMotsCles().add(mot);
				}
				newArticle.ajouterMC(mot);
			}
			Message dialog = new Message("Article enregistré");
			dialog.setVisible(true);
			getVueNouvelArticle().setEtat(Vue.finale);
		}
		/*****************************************/
		/* Lecture des lignes d'un fichier texte */
		/*****************************************/
		public HashSet<String> lectureLignesFichier()
		{
			HashSet<String> motsCles = new HashSet<String>();
			try  {
				BufferedReader in = new BufferedReader(new FileReader("ListeAutorite.txt"));
				String ligne;
				while ((ligne= in.readLine()) != null)  {
					motsCles.add(ligne);
				}
				in.close();
			}
			catch (IOException e) {
			 	System.out.println("$$$$$ PB de Lecture dans le fichier ListeAutorite.txt ");
				System.out.println();
			}
			return motsCles;
		} //  lectureLignesFichier;

}

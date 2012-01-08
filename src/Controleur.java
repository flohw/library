import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

//public class Controleur extends Observable implements Serializable{
public class Controleur implements Serializable{
	
	private static final long serialVersionUID = 1L;
	final static int information = 0, attention = 1, erreur = 2;

	/**
	 * La classe Controleur est unique pour tous les cas d'utilisation
	 * Elle est également la classe "application" qui gère l'ensemble des objets de l'appli
	 */	
	// ************************************************************************************************************
		// Attributs
		// ************************************************************************************************************
	
		// Attributs d'Association
		// Ensemble des ouvrages de la bibliothèque
		private HashMap<Integer, Ouvrage> _ouvrages;
		private HashMap<Integer, Periodique> _periodiques;
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
			this.setOuvrages(new HashMap<Integer, Ouvrage>());
			this.setPeriodiques(new HashMap<Integer, Periodique>());
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
		private void setOuvrage(Ouvrage ouvrage, Integer isbn) { this.getOuvrages().put(isbn, ouvrage); }
		private void setPeriodique(Periodique periodique, Integer issn) { this.getPeriodiques().put(issn, periodique); }
		private void setOuvrages(HashMap<Integer, Ouvrage> ouvrages) { _ouvrages = ouvrages; }
		private void setPeriodiques(HashMap<Integer, Periodique> periodiques) { _periodiques = periodiques; }
		
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
		public HashMap<Integer, Ouvrage> getOuvrages() { return _ouvrages; }
		public HashMap<Integer, Periodique> getPeriodiques() { return _periodiques; }
		public HashSet<Auteur> getAuteurs() { return _auteurs; }
		public HashSet<MotCle> getMotsCles() { return _motsCles; }
		public Ouvrage getOuvrage(Integer isbn) { return this.getOuvrages().get(isbn); }
		public Periodique getPeriodique(Integer issn) { return this.getPeriodiques().get(issn); }
		public Auteur getAuteur(Auteur auteur) {
			Auteur newAuteur = null;
			for (Auteur aut : getAuteurs()) {
				if (aut.getAuteur().equals(auteur.getAuteur()))
					newAuteur = aut;
			}
			return newAuteur;
		}
		public Auteur getAuteur(String auteur) {
			Auteur newAuteur = null;
			for (Auteur aut : getAuteurs()) {
				if (aut.getAuteur().equals(auteur))
					newAuteur = aut;
			}
			return newAuteur;
		}
		public MotCle getMotCle(String mc) {
			MotCle newMotCle = null;
			for (MotCle mot : getMotsCles())
			{
				if (mot.getMotcle().equals(mc))
					newMotCle = mot;
			}
			return newMotCle;
		}
		
		public Integer getIdentifiant(String ouvrage) {
			String [] identifiant = ouvrage.split(" - ");
			return Integer.decode(identifiant[0]);
		}

		////////////////////////////////////////////////////////////////////////////
		// Getteur de vues
		////////////////////////////////////////////////////////////////////////////
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
		private VueMenuBiblio getVueMenuBiblio() { return _vueMenuBiblio; }
		
		////////////////////////////////////////////////////////////////////////////
		// Affichage des CU
		////////////////////////////////////////////////////////////////////////////
		public void menuBiblio() {
			try {
				this.setVueMenuBiblio(new VueMenuBiblio(this));
				getVueMenuBiblio().setEtat(Vue.initiale);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Consulter ouvrage
		public void consulterOuvrage() {
			try {
				if (this.getOuvrages().isEmpty()) {
					int option = JOptionPane.showConfirmDialog(null, "Aucun ouvrage enregistré, voulez-vous en créer ?",
							"Erreur Periodique", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if(option == JOptionPane.YES_OPTION )
						saisirOuvrage();
					else
						menuBiblio();
				} else {
					this.setVueConsulterOuvrage (new VueConsulterOuvrage(this)); 	
					this.getVueConsulterOuvrage().setEtat(Vue.initiale);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// SAISIR EXEMPLAIRE
		public void saisirExemplaire() {
			try {
				if (this.getOuvrages().isEmpty()) {
					int option = JOptionPane.showConfirmDialog(null, "Aucun ouvrage enregistré, voulez-vous en créer ?",
							"Erreur Periodique", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if(option == JOptionPane.YES_OPTION )
						saisirOuvrage();
					else
						menuBiblio();
				} else {
					this.setVueSaisieExemplaire(new VueSaisieExemplaire(this));
					this.getVueSaisieExemplaire().setEtat(Vue.initiale);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// SAISIR OUVRAGE
		public void saisirOuvrage() {
			try {
				this.setVueSaisieOuvrage(new VueSaisieOuvrage(this));
				this.getVueSaisieOuvrage().setEtat(Vue.initiale);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//RECHERCHER MOT CLEF
		public void rechMotCle() {
			try {
				if (this.getMotsCles().isEmpty()) {
					menuBiblio();
					new Message("Aucun mot clé associé à un ouvrage ou à un article", Controleur.erreur);
				} else {
					this.setVueRechMotCle(new VueRechMotCle(this)); 
					this.getVueRechMotCle().setEtat(Vue.initiale);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//RECHERCHER AUTEUR
		public void rechAuteur() {
			try {
				if (this.getAuteurs().isEmpty()) {
					menuBiblio();
					new Message("Aucun auteur n'est enregistré, créez des ouvrages ou des articles", Controleur.erreur);
				} else {
					this.setVueRechAuteur(new VueRechAuteur(this));
					this.getVueRechAuteur().setEtat(Vue.initiale);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		

		//CONSULTER PERIODIQUES
		public void consulterPeriodique() {
			try {
				if (this.getPeriodiques().isEmpty()) {
					int option = JOptionPane.showConfirmDialog(null, "Aucun periodique enregistré, voulez-vous en créer ?",
							"Erreur Periodique", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if(option == JOptionPane.YES_OPTION )
						saisiePeriodique();
					else
						menuBiblio();
				} else {
					this.setVueConsulterPeriodique(new VueConsulterPeriodique(this));
					this.getVueConsulterPeriodique().setEtat(Vue.initiale);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//NOUVEAU PERIODIQUE
		public void saisiePeriodique() {
			try {
				this.setVueNouveauPeriodique(new VueNouveauPeriodique(this)); 
				this.getVueNouveauPeriodique().setEtat(Vue.initiale);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//NOUVEL ARTICLE
		public void saisieArticle() {
			try {
				if (this.getPeriodiques().isEmpty()) {
					int option = JOptionPane.showConfirmDialog(null, "Aucun periodique enregistré, voulez-vous en créer ?",
							"Erreur Periodique", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if(option == JOptionPane.YES_OPTION )
						saisiePeriodique();
					else
						menuBiblio();
				} else {
					this.setVueNouvelArticle(new VueNouvelArticle(this));
					this.getVueNouvelArticle().setEtat(Vue.initiale);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//NOUVELLE PARUTION
		public void saisirParution() {
			try {
				if (this.getPeriodiques().isEmpty()) {
					int option = JOptionPane.showConfirmDialog(null, "Aucun périodique enregistré, voulez-vous en créer ?",
							"Erreur Periodique", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if(option == JOptionPane.YES_OPTION )
						saisiePeriodique();
					else
						menuBiblio();
				} else {
					this.setVueNouvelleParution(new VueNouvelleParution(this));
					this.getVueNouvelleParution().setEtat(Vue.initiale);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//AFFICHER RECHERCHE AUTEUR
		public void affRechAuteur(String auteur) {
			try {
				this.setVueAfficheAuteur(new VueAfficheAuteur(this));
				Auteur aut = getAuteur(auteur);
				aut.addObserver(getVueAfficheAuteur());
				getVueAfficheAuteur().setAuteur(aut);
				this.getVueAfficheAuteur().setEtat(Vue.initiale);
				aut.notifierObservateurs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//AFFICHER RECHERCHE MC
		public void affRechMC(String motCle) {
			try {
				this.setVueAfficheMC(new VueAfficheMC(this));
				MotCle mc = getMotCle(motCle);
				mc.addObserver(getVueAfficheMC());
				getVueAfficheMC().setMotCle(mc);
				this.getVueAfficheMC().setEtat(Vue.initiale);
				mc.notifierObservateurs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		public void fermerVue (Vue vue) {
			vue.dispose();
			vue.getFrame().setVisible(false);
			this.resetVues();
		}
		
		public void quitter(VueMenuBiblio vue) {
				this.fermerVue(vue);
				this.sauve();
				System.exit(0);
		}
		
		// Restaure l'état de l'interface avec seule la fenêtre du Menu principal active
		private void resetVues() {
			this.setVueSaisieOuvrage(null);
			this.setVueConsulterOuvrage(null);
			this.setVueSaisieExemplaire(null);
			this.setVueRechMotCle(null);
			this.setVueRechAuteur(null);
			this.setVueConsulterPeriodique(null);
			this.setVueNouveauPeriodique(null);
			this.setVueNouvelArticle(null);
			this.setVueNouvelleParution(null);
			this.setVueAfficheAuteur(null);
			this.setVueAfficheMC(null);
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
				System.out.print(e.getMessage());
				new Message("Pbs de Restauration ou fichier non encore créé", Controleur.erreur);
				return this;
			} 
		}
		private void sauve() {
			try {
				DataOutputStream f = new DataOutputStream(new FileOutputStream("Fsauv.ser"));
				ObjectOutputStream out = new ObjectOutputStream(f);
				out.writeObject(this);
			} catch (Exception e) {
				System.out.print(e.getMessage());
				new Message("Pb de Sauvegarde dans le fichier", Controleur.erreur);
			}
		}
		////////////////////////////////////////////////////////////////////////////
		// Opérations liées à l'application en réponse à une action de l'utilisateur dans une vue
		////////////////////////////////////////////////////////////////////////////
		
		public Ouvrage rechOuvrage(Integer isbn, String titre, String editeur, GregorianCalendar date) {
			Ouvrage ouv = this.getOuvrage(isbn);
			if (ouv != null) {
				int option = JOptionPane.showConfirmDialog(null, "Cet ouvrage existe déjà, voulez vous créer un exemplaire ?",
						"Erreur Ouvrage", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if(option == JOptionPane.YES_OPTION ) {
					fermerVue(getVueSaisieOuvrage());
					saisirExemplaire();
				}
			} else {
				this.getVueSaisieOuvrage().setEtat(Vue.inter1);
				ouv = new Ouvrage(isbn, titre, editeur, date);
			}
			return ouv;
		}
		
		public void rechOuvrage(String livre) {
			Integer isbn = getIdentifiant(livre);
			Ouvrage ouv = this.getOuvrage(isbn);
			if (this.getVueSaisieExemplaire() != null) {
				ouv.addObserver(this.getVueSaisieExemplaire());
				this.getVueSaisieExemplaire().setOuvrage(ouv);
				this.getVueSaisieExemplaire().setEtat(Vue.inter1);
				ouv.notifierObservateurs();
			}
			if (this.getVueConsulterOuvrage() != null) {
				if (this.getOuvrages().isEmpty()) {
					int option = JOptionPane.showConfirmDialog(null, "Aucun ouvrage n'est enregistré, voulez-vous le créer ?",
							"Erreur Ouvrage", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(option == JOptionPane.YES_OPTION ) {
						fermerVue(getVueConsulterOuvrage());
						saisirOuvrage();
					}
				} else {
					ouv.addObserver(this.getVueConsulterOuvrage());
					this.getVueConsulterOuvrage().setOuvrage(ouv);
					ouv.notifierObservateurs();
					int nbConsult = ouv.getNbExemplairesEnConsultation();
					int nbEmpr = ouv.getNbExemplairesEmpruntable();
					if ((nbConsult + nbEmpr)== 0) {
						int option = JOptionPane.showConfirmDialog(null, "Aucun exemplaire n'est enregistré, voulez-vous le créer ?",
								"Erreur Ouvrage", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

						if(option == JOptionPane.YES_OPTION ) {
							fermerVue(getVueConsulterOuvrage());
							saisirExemplaire();
						}
					}
				}
			}
		} // Fin rechOuvrage
		
		public boolean auteurExiste(Auteur aut, HashSet<Auteur> auteurs) {
			boolean exist = false;
			for (Auteur a : auteurs) {
				if (aut.getAuteur().equals(a.getAuteur()))
					exist = true;
			}
			
			if (exist) {
				new Message("Cet auteur a déjà été enregistré", Controleur.attention);
			} else {
				new Message("Auteur enregistré", Controleur.information);
				auteurs.add(aut);
				if (getVueSaisieOuvrage() != null)
					getVueSaisieOuvrage().setEtat(Vue.inter2);
				if (getVueNouvelArticle() != null)
					getVueNouvelArticle().setEtat(Vue.finale);
			}
			return exist;
		}
		
		public void nouvOuvrage(Ouvrage ouv, HashSet<Auteur> auteurs, HashSet<String> motsCles) {
			// vérification de la présence des infos obligatoires et du format de la date
			// Instanciation de l'ouvrage
			// Ajout de l'ouvrage dans l'ensemble des ouvrages de la bibliothèque
			this.setOuvrage(ouv, ouv.getIsbn());
			for (Auteur aut : auteurs) {
				Auteur a = getAuteur(aut);
				if (a == null) {
					a = new Auteur(aut.getNom(), aut.getPrenom());
					this.getAuteurs().add(a);
				}
				a.ajouterOuvrage(ouv.getIsbn(), ouv);
			}
			MotCle mot = null;
			for (String mc : motsCles) {
				mot = getMotCle(mc);
				if (mot == null) {
					mot = new MotCle(mc);
					this.getMotsCles().add(mot);
				}
				mot.ajouterOuvrage(ouv.getIsbn(), ouv);
			}
			
			int option = JOptionPane.showConfirmDialog(null, "Ouvrage enregistré, voulez-vous en créer un autre ?",
					"Erreur Ouvrage", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if(option == JOptionPane.YES_OPTION )
				getVueSaisieOuvrage().setEtat(Vue.initiale);
			else {
				this.fermerVue (this.getVueSaisieOuvrage());
				this.menuBiblio();
			}
		}
		
		public void nouvExemplaire(Ouvrage ouv, String dateReception, String statut) {
			// vérification de la présence de la date et de son format
			if (dateReception.length() == 0 )
					new Message("La date de réception est obligatoire", Controleur.erreur);
			else {
				GregorianCalendar date = ESDate.lireDate (dateReception);
				if (date == null)
					new Message("Le format de la date est incorrect", Controleur.erreur);
				else {
					int statutEx;
					if (statut == "empruntable")
						statutEx = Exemplaire.EMPRUNTABLE ;
					else
						statutEx = Exemplaire.EN_CONSULTATION ;
				
					Exemplaire exemplaire = ouv.ajouterExemplaire(date, statutEx);
					if (exemplaire != null) {
						this.getVueSaisieExemplaire().setEtat(Vue.finale);
						new Message("Exemplaire enregistré", Controleur.information);
					} else
						new Message("Date de Reception incorrecte / à la date d'Edition.", Controleur.erreur);
				}
			}
		}
		
		public void rechPeriodique(String livre) {
			Integer issn = this.getIdentifiant(livre);
			Periodique per = this.getPeriodique(issn);
			if (this.getVueConsulterPeriodique() != null) {
				per.addObserver(getVueConsulterPeriodique());
				getVueConsulterPeriodique().setPeriodique(per);
				per.notifierObservateurs();
				if (per.getNbParutions() == 0) {
					int option = JOptionPane.showConfirmDialog(null, "Aucune parution pour ce périodique, voulez-vous en créer ?",
							"Aucune Parution", JOptionPane.YES_NO_OPTION    , JOptionPane.QUESTION_MESSAGE); 
					if(option == JOptionPane.YES_OPTION ) {
						fermerVue(getVueConsulterPeriodique());
						saisirParution();
					}
				}
			}
			
			if (this.getVueNouvelleParution() != null) {
				per.addObserver(getVueNouvelleParution());
				getVueNouvelleParution().setPeriodique(per);
				this.getVueNouvelleParution().setEtat(Vue.finale);
				per.notifierObservateurs();
			}
			
			if (this.getVueNouvelArticle() != null) {
				if (per.getNbParutions() != 0) {
					per.addObserver(getVueNouvelArticle());
					getVueNouvelArticle().setPeriodique(per);
					getVueNouvelArticle().setEtat(Vue.inter1);
					per.notifierObservateurs();
				} else if (per.getNbParutions() == 0) {			
					int option = JOptionPane.showConfirmDialog(null, "Il n'y a pas de parutions, voulez-vous les créer ?",
							"Erreur Parutions", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if(option == JOptionPane.YES_OPTION ) {
						fermerVue(getVueNouvelArticle());
						saisirParution();
					} else {
						per.addObserver(getVueNouvelArticle());
						getVueNouvelArticle().setPeriodique(per);
						getVueNouvelArticle().setEtat(Vue.initiale);
						per.notifierObservateurs();
					}
				}
			}
		}
		
		public void nouveauPeriodique (Integer issn, String nom, GregorianCalendar date) {
			if (this.getPeriodique(issn) == null) {
				Periodique periodique = new Periodique(issn, nom, date);
				this.setPeriodique(periodique, issn);
				int option = JOptionPane.showConfirmDialog(null, "Periodique enregistré, voulez-vous en créer un nouveau ?",
						"Nouveau Periodique", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if(option == JOptionPane.YES_OPTION )
					getVueNouveauPeriodique().setEtat(Vue.initiale);
				else
				{
					fermerVue(getVueNouveauPeriodique());
					menuBiblio();
				}
			} else
				new Message("Le périodique existe déjà", Controleur.information);
		}
		
		
		public void nouvelleParution(Periodique pe, Integer id, String titre) {
			Parution pa = getPeriodique(pe.getIssn()).getParution(id);
			if (pa != null)
				new Message("La parution existe deja pour le périodique", Controleur.attention);
			else {
				pe.setParution(id, new Parution(id, titre));			
				int option = JOptionPane.showConfirmDialog(null, "La parution a été enregistrée pour " + pe.getNom() +"" +
						", voulez-vous en créer une autre ?",
						"Nouvelle Parution", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if(option == JOptionPane.YES_OPTION)
						getVueNouvelleParution().setEtat(Vue.initiale);
				else {
					fermerVue(getVueNouvelleParution());
					menuBiblio();
				}
			}
		}
		
		public Article rechArticle(Parution pa, String titre) {
			Article article = pa.getArticle(titre);
			if (article != null)
				new Message("Ce titre existe déjà pour cette parution", Controleur.attention);
			else
				getVueNouvelArticle().setEtat(Vue.inter3);
			return article;
		}
		
		public void rechParution(Periodique pe, String livre) {
			Integer id = this.getIdentifiant(livre); 
			Parution pa = pe.getParution(id);
			if (pa == null) {
				int option = JOptionPane.showConfirmDialog(null, "La parution n'existe pas, voulez-vous la créer ?",
						"Nouvelle Parution", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if(option == JOptionPane.YES_OPTION)
				{
					if (this.getVueNouvelArticle() != null)
						fermerVue(getVueNouvelArticle());
					saisirParution();
				}
			} else {
				pa.addObserver(getVueNouvelArticle());
				getVueNouvelArticle().setParution(pa);
				this.getVueNouvelArticle().setEtat(Vue.inter2);
				pa.notifierObservateurs();
			}
		}
		
		public void nouvArticle(String titre, Integer page, HashSet<Auteur> auteurs, HashSet<String> motsCles, Parution pa) {
			Article newArticle = new Article(titre, page, pa);
			pa.ajouterArticle(newArticle);
			for (Auteur aut : auteurs) {
				Auteur a = getAuteur(aut);
				if (a == null) {
					a = new Auteur(aut.getNom(), aut.getPrenom());
					this.getAuteurs().add(a);
				}
				a.ajouterArticle(newArticle.getTitre(), newArticle);
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
			int option = JOptionPane.showConfirmDialog(null, "L'article a bien été enregistré, voulez-vous en créer un nouveau ?",
					"Nouvelle Parution", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if(option == JOptionPane.YES_OPTION)
					getVueNouvelArticle().setEtat(Vue.initiale);
			else {
				fermerVue(getVueNouvelArticle());
				menuBiblio();
			}
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
		}

}

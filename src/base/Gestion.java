package base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.io.*;

import utilisateurs.Eleve;
import utilisateurs.Emprunteur;
import utilisateurs.Gestionnaire;
import utilisateurs.Personne;
import utilisateurs.Professeur;

import Materiel.Materiel;
import Materiel.MaterielEmprunte;
import Materiel.Stock;
import Outils.FichierData;


/** 
* Cette classe fait le lien entre un utilisateur
* et le stock sur lequel il travaille. Elle contient
* un ensemble de fonctions qui permettent d'effectuer des actions
* sur le stock. Certaines fonctions sont reservees aux gestionnaires.

* 
* @author lyameina
*/

public class Gestion
{

	private Stock stock;
	private ArrayList<MaterielEmprunte> refus ;
	private HashMap<Emprunteur, String> hashMapEmprunteur;
	private HashMap<Gestionnaire, String> hashMapGestionnaire;
	private Personne utilisateurCourant;
	private String motDePasseGestionnaire;
	

	/** 
	* Constructeur avec paramètres qui cree un nouvel objet Gestion
	* avec un utilisateur et qui lit la sauvegarde utilisateurs
	*
	*/ 
	public Gestion()
	{
		//recuperation du HashMap motDePasse:
		FichierData f  = new FichierData();
		hashMapEmprunteur = f.deserialisationHashMapEmprunteur("hashMapEmprunteur");
		hashMapGestionnaire = f.deserialisationHashMapGestionnaire("hashMapGestionnaire");
		
		//création de l'utilisateur courant:
		utilisateurCourant = new Personne();
				
		//création du stock;
		stock = new Stock();
	}
	
	/** 
	* Regarde dans le HashMap si l'utilisateur existe .
	* L'utilisateurCourant change si il existe
	*
	* @param nom Le nom de l'utilisateur
	* @param prenom Le prenom de l'utilisateur
	* @param statut Le satut de l'utilisateur (false = emprunteur ; true = gestionnaire). 
	*/ 

	public boolean existe(String nom, String prenom, boolean gestionnaire ){
		if (gestionnaire){
			Gestionnaire aTester = new Gestionnaire (nom, prenom);
			if (hashMapGestionnaire.containsKey(aTester)){
				utilisateurCourant = aTester;
				return true;
			}
			else
				return false;
		}
		else{
			Emprunteur aTester = new Emprunteur (nom, prenom);
			if (hashMapEmprunteur.containsKey(aTester)){
				utilisateurCourant = aTester;
				return true;
			}
			else
				return false;
		}
	}
	
	public boolean rendre (MaterielEmprunte choix, nombreRendu, nombreHS){
		if (nombreRendu <= choix.getMatEmprunt().getNombre()){
			String idEmprunt = choix.getId();
			stock.renduEmprunt(idEmprunt, nombreRendus, nombreHS);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Renvoie true si l'utilisateur est correctement loggé.
	 * Set definitivement l'utlisateur courant
	 * @param nom
	 * @param Prenom
	 * @param motDePasse
	 * @param gestionnaire
	 * @return true su l'utilisateur est loggé
	 */
	private boolean isLogged(String nom, String prenom, String motDePasse, boolean gestionnaire){
		if (existe(nom, prenom, gestionnaire)){
			if (gestionnaire){
				Gestionnaire aTester = new Gestionnaire(nom,prenom);
				String vraiMotDePasse = hashMapGestionnaire.get(aTester);
				if (vraiMotDePasse.equals(motDePasse)) {
					for( Gestionnaire g : hashMapGestionnaire.keySet()){
						if (g.equals(aTester))
							utilisateurCourant = g;
							return true;
					}
				}
					
			}
			else{
				Emprunteur aTester = new Emprunteur(nom,prenom);
				String vraiMotDePasse = hashMapEmprunteur.get(aTester);
				if (vraiMotDePasse.equals(motDePasse)) {
					for( Emprunteur e : hashMapEmprunteur.keySet()){
						if (e.equals(aTester))
							utilisateurCourant = e;
							return true;
					}
				}
				
			}
		}
		return false;
	}
	
	
	
	public boolean password (String motdepasse, boolean gestionnaire, boolean creationGestionnaire){
		//si c'est la verification lors de la création d'un gestionnaire:
		if (creationGestionnaire){
			if(motdepasse.equals(motDePasseGestionnaire))
				return true;
		return false;
		}
		else{
			return (this.isLogged(utilisateurCourant.getNom(), utilisateurCourant.getPrenom(), motdepasse, gestionnaire));
		}
	}
	
	
	public void createNewGestionnaire(String nom, String prenom, String passwd){
		utilisateurCourant = new Gestionnaire (nom, prenom);
		hashMapGestionnaire.put((Gestionnaire)utilisateurCourant, passwd);
	}
	
	public void createNewProf(String nom, String prenom, String passwd){
		utilisateurCourant = new Professeur(nom, prenom);
		hashMapEmprunteur.put((Emprunteur)utilisateurCourant, passwd);
	}
	
	public void createNewEleve(String nom, String prenom, String passwd){
		utilisateurCourant = new Eleve(nom, prenom);
		hashMapEmprunteur.put((Emprunteur)utilisateurCourant, passwd);
	}
	
	
	//Fonctions associees à l'Emprunteur:
	
	public ArrayList<MaterielEmprunte> listeEmpruntParEmprunteur(){
		
		return stock.empruntsParEmprunteur((Emprunteur)utilisateurCourant);
	}
	
	public ArrayList<Materiel> listeMaterielEmpruntable( String motAChercher , Date dateDebut, Date dateFin){
		
		return stock.materielDispo(dateDebut, dateFin, motAChercher);
	}
	
	public String emprunt(Materiel choix, int nombre, Date dateDebut, Date dateFin){
		
		if (choix.empruntable(dateDebut, dateFin, ((Emprunteur)utilisateurCourant))){
			if (nombre < choix.getNombre()){
				MaterielEmprunte m = new MaterielEmprunte(choix, ((Emprunteur)utilisateurCourant),dateDebut, dateFin);
				stock.emprunter(m);
				return "Reservation effectuée";
			}
			else
				return "Erreur: Nous n'avons pas autant de materiel (nombre demandé trop grand)\n Nous avons seulement "+ choix.getNombre() + " exemplaire(s)";
		}
		return "Erreur: Vous n'êtes pas autorisé(e) à utiliser ce materiel";
		
	}
	
	public String AfficherStockTotal(){
		return stock.afficherStock();
	}
	
	//Fonctions associees au Gestionnaire:
	
	
	
}

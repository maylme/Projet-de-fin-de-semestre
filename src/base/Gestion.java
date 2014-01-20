package base;

import java.util.ArrayList;
import java.io.*;

/** 
* Cette classe fait le lien entre un utilisateur
* et le stock sur lequel il travaille. Elle contient
* un ensemble de fonctions qui permettent d'effectuer des actions
* sur le stock. Certaines fonctions sont reservees aux gestionnaires.
* Elle permet aussi d'etablir des statistiques sur les utilisateurs du
* programme.
* 
* @author Vincent Montalieu et Sonia Tual 
* @version 1.2 (3.Dec.2013)
*/

public class Gestion
{
	private Stock stock;
	private ArrayList<Personne> listeUtilisateurs;
	private Personne utilisateur;

	/** 
	* Constructeur par defaut qui cree un nouvel objet Gestion
	* ayant comme stock un nouveau stock, comme utilisateur 
	* "INCONNU Inconnu" avec comme statut celui d'emprunteur.
	*/ 
	public Gestion()
	{
		this("inconnu", "inconnu", false) ;
	}

	/** 
	* Constructeur avec paramètres qui cree un nouvel objet Gestion
	* avec un utilisateur et qui lit la sauvegarde utilisateurs
	* afin de voir si l'utilisateur existe deja.
	*
	* @param nom Le nom de l'utilisateur en cours.
	* @param prenom Le prenom de l'utilisateur en cours.
	* @param statut Le statut de l'utilisateur en cours
	* (false = emprunteur ; true = gestionnaire).
	*/ 
	public Gestion(String nom, String prenom, boolean statut)
	{
		stock = new Stock();
		listeUtilisateurs = deserialisationListePersonne("listeUtilisateurs");
		definirUtilisateur(nom, prenom,statut);
	}

	/** 
	* Methode de lecture de la sauvegarde utilisateurs.
	* Precondition : il faut compiler depuis le dossier
	* GestionDeStock.
	*
	* @param nomListe Le nom du fichier dans lequel lire.
	* @return Une ArrayList de Personne qui contient les differents
	* utilisateurs sauvegardes en memoire lors de precedentes utilisations.
	*/ 
	private ArrayList<Personne> deserialisationListePersonne(String nomListe)
	{
		try
		{
			File file = new File("data/" + nomListe + ".dat");
		    if (!file.exists())
		    {
        		return new ArrayList<Personne>();
    		} 
    		else if (file.length() <= 4)
    		{
        		return new ArrayList<Personne>();
        	}
        	else
        	{
        		FileInputStream fichierData = new FileInputStream("data/" + nomListe + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fichierData);
				return (ArrayList<Personne>) ois.readObject();
        	}	
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
		catch (java.lang.ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return new ArrayList<Personne>();
	}

	/** 
	* Methode de sauvegarde des utilisateurs.
	* Precondition : il faut compiler depuis le dossier
	* GestionDeStock.
	*
	* @param listeASerialiser Le nom de l'ArrayList a sauvegarder.
	* @param nomListe Le nom du fichier dans lequel sauvegarder la liste.
	*/
	private void serialisationListe(ArrayList<Personne> listeASerialiser, String nomListe)
	{
		try
		{ 
			FileOutputStream fichierListe = new FileOutputStream("data/" + nomListe + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
			oos.writeObject(listeASerialiser);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
	}

	/** 
	* Methode de definition de l'utilisateur en cours. Regarde dans
	* la liste des utilisateurs (historique) si l'utilisateur existe deja.
	*
	* @param listeASerialiser Le nom de l'ArrayList a sauvegarder.
	* @param nomListe Le nom du fichier dans lequel sauvegarder la liste.
	*/ 
	public void definirUtilisateur(String nom, String prenom, boolean statut)
	{
		int index = rechercheIndexPersonne(nom, prenom, statut) ;

		if(index>=0)
		{
			utilisateur = listeUtilisateurs.get(index) ;
		}

		else
		{
			utilisateur = new Personne(nom, prenom, statut);
			listeUtilisateurs.add(utilisateur) ;
			serialisationListe(listeUtilisateurs, "listeUtilisateurs");
		}
	}

	/** 
	* Methode de retour du statut de l'utilisateur.
	*
	* @return Un booleen qui correspond au statut de l'utilisateur :
	* false pour un emprunteur et true pour un gestionnaire.
	*/ 
	public boolean statut()
	{
		return utilisateur.getStatut();
	}

	/** 
	* Methode de recherche d'utilisateur dans la liste des utilisateurs.
	*
	* @param nom Le nom de l'utilisateur a rechercher.
	* @param prenom Le prenom de l'utilisateur a rechercher.
	* @param statut Le statut de l'utilisateur a rechercher.
	* @return Un entier representant l'indice dans la liste de Personne
	* correspondant a l'utilisateur recherche ou -1 si l'utilisateur
	* n'a pas ete trouve.
	*/
	private int rechercheIndexPersonne(String nom, String prenom, boolean statut)
	{
		boolean trouve = false;
		int indice = 0;
		int i = 0;

		while(trouve==false && i<listeUtilisateurs.size())
		{
			if(listeUtilisateurs.get(i).getNom().toUpperCase().equals(nom.toUpperCase()) && listeUtilisateurs.get(i).getPrenom().toUpperCase().equals(prenom.toUpperCase()) && listeUtilisateurs.get(i).getStatut()==statut)
			{
				trouve = true;
				indice = i;			
			}

			i++;
		}

		if(trouve)
		{
			return indice ;
		}			

		else
		{
			return -1 ;
		}
	}

	/** 
	* Methode d'ajout d'un materiel au stock disponible.
	*
	* @param type Le nom du materiel a ajouter.
	* @param nombre Le nombre d'exemplaires de ce materiel a ajouter.
	*/
	public void ajoutMaterielStock(String type, int nombre)
	{
		if(statut())
		{
			stock.ajouterNouveauMateriel(type,nombre) ;
		}
	}

	/** 
	* Methode de suppression d'un materiel du stock disponible.
	*
	* @param type Le nom du materiel a supprimer.
	* @return Un entier symbolisant l'action effectuee :
	* 1 si la suppression a eu lieu, 0 si le stock etait vide a la base
	* et -2 si la methode est appelee par un emprunteur.
	*/
	public int retirerMaterielStock(String type)
	{
		if(statut())
		{
			return stock.retirerMaterielDisponible(type) ;
		}

		else
		{
			return -2 ;
		}
	}

	/** 
	* Methode de suppression d'un materiel en reparation.
	*
	* @param type Le nom du materiel a supprimer.
	* @return Un entier symbolisant l'action effectuee :
	* 1 si la suppression a eu lieu, 0 si aucun materiel n'etait
	* en reparation a la base et -2 si la methode est
	* appelee par un emprunteur.
	*/
	public int retirerMaterielReparation(String type)
	{
		if(statut())
		{
			return stock.retirerMaterielReparation(type) ;
		}

		else
		{
			return -2 ;
		}
	}

	/** 
	* Methode de mise en reparation d'un materiel. Le materiel
	* est enleve du stock et ajoute a la liste des reparations.
	*
	* @param type Le nom du materiel a reparer.
	* @return Un entier symbolisant l'action effectuee :
	* 1 si la mise en reparation a eu lieu, 0 si le materiel
	* n'etait pas dans le stock a la base et -2 si la methode
	* est appelee par un emprunteur ou en cas d'erreur de saisie.
	*/
	public int aReparer(String type, int exemplairesAReparer)
	{
		if(statut())
		{
			return stock.aReparer(type, exemplairesAReparer) ;
		}

		else
		{
			return -2 ;
		}
	}

	/** 
	* Methode de retour de reparation d'un materiel. Le materiel
	* est enleve de la liste des reparations et remis dans le stock.
	*
	* @param type Le nom du materiel a supprimer.
	* @return Un entier symbolisant l'action effectuee :
	* 1 si la suppression a eu lieu, 0 si le stock etait vide a la base
	* et -2 si la methode est appelee par un emprunteur.
	*/
	public int retourReparation(String type, int exemplairesARetirer)
	{
		if(statut())
		{
			return stock.retourReparation(type, exemplairesARetirer) ;
		}

		else
		{
			return -2;
		}
	}

	/** 
	* Methode d'emprunt d'un materiel. Le materiel est enleve
	* du stock.
	*
	* @param type Le nom du materiel a emprunter.
	* @param nombreExemplaires Le nombre d'exemplaires a emprunter.
	* @param date La date d'emprunt.
	* @param duree La duree d'emprunt.
	* @return Un entier symbolisant l'action effectuee :
	* -1 si l'emprunt a eu lieu, -2 en cas d'erreur de frappe
	* et le nombre max d'exemplaires disponibles si l'utilisateur
	* souhaite emprunter trop d'exemplaires.
	*/
	public int emprunter(String type, int nombreExemplaires, String date, int duree)
	{
		int retour = stock.emprunter(type, nombreExemplaires, utilisateur, date, duree);
		int index = rechercheIndexPersonne(utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getStatut()) ;

		if(retour==-1)
		{
			if(index>=0)
			{
				listeUtilisateurs.get(index).incrementNbrEmprunt(nombreExemplaires) ;
			}				

			else
			{
				listeUtilisateurs.add(new Personne(utilisateur, nombreExemplaires)) ;
			}
			serialisationListe(listeUtilisateurs, "listeUtilisateurs");
		}

		return retour ;
	}

	/** 
	* Methode de retour d'un materiel. Le materiel est remis dans
	* le stock.
	*
	* @param type Le nom du materiel a rendre.
	* @param nombreExemplaires Le nombre d'exemplaires a rendre.
	* @return Un booleen symbolisant l'action effectuee :
	* true si le retour a eu lieu, false sinon.
	*/
	public boolean rendre(String type, int nombreExemplaires)
	{
		return stock.rendre(type, nombreExemplaires, utilisateur);
	}

	/** 
	* Methode de recherche du type le plus emprunte.
	*
	* @return L'objet TypeDeMateriel le plus emprunte
	* ou NULL si aucun emprunt n'a ete effectue ou que
	* la fonction est appelee par un emprunteur.
	*/
	public TypeDeMateriel materielPlusEmprunte()
	{
		if(statut())
		{
			return stock.materielPlusEmprunte();
		}

		else
		{
			return null;
		}
	}

	/** 
	* Methode de recherche du type le plus repare.
	*
	* @return L'objet TypeDeMateriel le plus repare
	* ou NULL si aucune reparation n'a ete effectuee ou que
	* la fonction est appelee par un emprunteur.
	*/
	public TypeDeMateriel materielPlusRepare()
	{
		if(statut())
		{
			return stock.materielPlusRepare();
		}

		else
		{
			return null;
		}		
	}

	/** 
	* Methode de recherche de l'utilisateur ayant
	* le plus emprunte de materiel.
	*
	* @return L'utilisateur ayant le plus emprunte
	* ou NULL si aucun emprunt n'a ete effectue ou que
	* la fonction est appelee par un emprunteur.
	*/
	public Personne plusGrosEmprunteur()
	{
		if(statut())
		{
			int index = 0;

			if(listeUtilisateurs.size()>0)
			{
				for(int i=0 ; i<listeUtilisateurs.size()-1; i++)
				{
					if(listeUtilisateurs.get(index).getNbrEmprunt() < listeUtilisateurs.get(i+1).getNbrEmprunt())
					{
						index = i+1;
					}			
		
					else
					{
						index = 0;
					}
				}		
			}

			if(listeUtilisateurs.get(index).getNbrEmprunt()>0)
				return listeUtilisateurs.get(index) ;

			else
				return null ;
		}

		else
		{
			return null;
		}
	}

	/** 
	* Methode de retour du stock disponible.
	*
	* @return Une chaine de caracteres representant
	* le stock disponible.
	*/
	public String afficherStock()
	{
		return stock.afficherStock();
	}

	/** 
	* Methode de retour des reparations en cours.
	*
	* @return Une chaine de caractees representant
	* les reparations en cours.
	*/
	public String afficherReparations()
	{
		if(statut())
		{
			return stock.afficherReparations();
		}

		else
		{
			return "Vous n'avez pas les permissions necessaires !" ;
		}
	}

	/** 
	* Methode de retour des emprunts en cours.
	*
	* @return Une chaine de caractères representant
	* les emprunts en cours.
	*/
	public String afficherEmprunts()
	{
		if(statut())
		{
			return stock.afficherEmprunts();
		}

		else
		{
			return "Vous n'avez pas les permissions necessaires !" ;
		}
	}

	/** 
	* Methode de retour de l'historique des utilisateurs.
	*
	* @return Une chaine de caractères representant
	* les differents utilisateurs.
	*/
	public String afficherUtilisateurs()
	{
		String retour = "     UTILISATEURS\n\n" ;

		for(int i = 0 ; i<listeUtilisateurs.size() ; i++)
		{
			retour += listeUtilisateurs.get(i) ;
		}

		return retour ;
	}

	/** 
	* Methode de sauvegarde dans un fichier lisible .txt
	* de l'ensemble des donnees concernant le programme :
	* stock disponible, reparations en cours, emprunts en cours
	* et liste d'utilisateurs.
	*/
	public void serialisationFichierLisible()
	{
		try
		{ 
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/salleIHM.txt")));
			out.println("liste du stock disponible :");
			out.println(stock.getListeDisponible());
			out.println("\nliste du materiel emprunte :");
			out.println(stock.getListeEmprunts());
			out.println("\nliste du materiel en reparation :");
			out.println(stock.getListeReparations());
			out.println("\nliste des utilisateurs :");
			out.println(listeUtilisateurs);
			out.flush();
			out.close();
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
	}
}
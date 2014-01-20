package Materiel;

import java.util.Date;

import Materiel.Materiel;
import utilisateurs.Emprunteur;

/** 
* La classe MaterielEmprunte represente une materiel avec
* un emprunteur, la date d'emprunt et la duree d'emprunt.
* 
* @author Sonia Tual et Vincent Montalieu
* @version 1.0 (4.Dec.2013) 
*/

public class MaterielEmprunte implements java.io.Serializable
{
	private Materiel emprunt ;
	private Emprunteur emprunteur ;
	private Date dateEmprunt ;
	private int dureeEmprunt ; //unité en jours

	/** 
	* Constructeur de la classe MaterielEmprunte
	* Il recupere un Materiel, un emprunteur (Emprunteur), une Date
	* pour la date d'emprunt et la duree de l'emprunt en jours.
	*
	* @param emprunt Un Materiel represente le materiel emprunte
	* @param emprunteur Une Personne
	* @param dateEmprunt une Date representant la date d'emprunt du materiel.
	* @param dureeEmprunt C'est un entier representant la duree d'emprunt du materiel en jours.
	*/
	public MaterielEmprunte(Materiel emprunt, Emprunteur emprunteur, Date dateEmprunt, int dureeEmprunt)
	{
		this.emprunt = emprunt ;
		this.emprunteur = emprunteur ;
		this.dateEmprunt = dateEmprunt ;
		this.dureeEmprunt = dureeEmprunt ;
	}

	/** 
	* Constructeur par defaut de la classe MaterielEmprunte
	* Par defaut, on construit un nouveau Materiel, un nouvel emprunteur,
	* la date du jour pour une duree de 10 jours.
	*/ 
	public MaterielEmprunte()
	{
		this(new Materiel(),new Emprunteur(), new Date(), 10);
	}

	/** 
	* Methode publique utilisee pour acceder au
	* materiel emprunte a partir d'une autre classe.
	*
	* @return Un Materiel representant le materiel emprunte.
	*/ 
	public Materiel getMatEmprunt()
	{
		return emprunt;
	}

	/** 
	* Methode publique utilisee pour acceder a
	* l'emprunteur a partir d'une autre classe.
	* 
	* @return Emprunteur representant l'emprunteur du materiel.
	*/ 
	public Emprunteur getEmprunteur()
	{
		return emprunteur;
	}

	/** 
	* Methode publique utilisee pour acceder l'information
	* date d'emprunt a partir d'une autre classe.
	* 
	* @return Date contenant la date de l'emprunt.
	*/ 
	public Date getDateEmprunt()
	{
		return dateEmprunt;
	}

	/** 
	* Methode publique utilisee pour acceder a l'information
	* duree d'emprunt a partir d'une autre classe.
	* 
	* @return Entier representant la duree de l'emprunt.
	*/
	public int getDureeEmprunt()
	{
		return dureeEmprunt;
	}

	/** 
	* Methode publique utilisee pour modifier la
	* duree d'emprunt a partir d'une autre classe.
	*
	* @param newDureeEmprunt Entier representant la nouvelle duree de l'emprunt
	* que l'on veut modifier.
	*/ 
	public void setDureeEmprunt(int newDureeEmprunt)
	{
		dureeEmprunt=newDureeEmprunt;
	}

	/** 
	* Methode publique utilisee pour afficher un materiel emprunte.
	* Le nom de l'emprunteur apparait en majuscule, puis le prenom de l'emprunteur,
	* ensuite la date d'emprunt et pour finir la duree d'emprunt en jours.
	*
	* @return La chaine de caractere contenant la description
	* du materiel emprunte a afficher.
	*/ 
	public String toString()
	{
		return emprunt + "\nEmprunteur : "+ emprunteur.getNom().toUpperCase() + " " + emprunteur.getPrenom() + "\nDate d'emprunt : " + dateEmprunt.toString() +"\nDurée d'emprunt : " + dureeEmprunt + " jour(s)\n";
	}
}
package base;

import Materiel.TypeDeMateriel;
import utilisateurs.Personne;

/** 
* La classe MaterielEmprunte represente une materiel avec
* un emprunteur, la date d'emprunt et la duree d'emprunt.
* 
* @author Sonia Tual et Vincent Montalieu
* @version 1.0 (4.Dec.2013) 
*/

public class MaterielEmprunte implements java.io.Serializable
{
	private TypeDeMateriel emprunt ;
	private Personne emprunteur ;
	private String dateEmprunt ; // format jour/mois/année   2 chiffres pour chaque
	private int dureeEmprunt ; //unité en jours

	/** 
	* Constructeur de la classe MaterielEmprunte
	* Il recupere un TypeDeMateriel, un emprunteur (Personne), une chaine de caractere
	* pour la date d'emprunt sous le format JJ/MM/AA et la duree de l'emprunt en jours.
	*
	* @param emprunt Un TypeDeMateriel represente le materiel emprunte
	* @param emprunteur Une Personne
	* @param dateEmprunt C'est une chaine de caractere representant la date d'emprunt du materiel.
	* @param dureeEmprunt C'est un entier representant la duree d'emprunt du materiel en jours.
	*/
	public MaterielEmprunte(TypeDeMateriel emprunt, Personne emprunteur, String dateEmprunt, int dureeEmprunt)
	{
		this.emprunt = emprunt ;
		this.emprunteur = emprunteur ;
		this.dateEmprunt = dateEmprunt ;
		this.dureeEmprunt = dureeEmprunt ;
	}

	/** 
	* Constructeur par defaut de la classe MaterielEmprunte
	* Par defaut, on construit un nouveau TypeDeMateriel, une nouvelle personne,
	* la date est fixee au 1 janvier 2013 pour une duree de 10 jours.
	*/ 
	public MaterielEmprunte()
	{
		this(new TypeDeMateriel(),new Personne(), "01/01/13", 10);
	}

	/** 
	* Methode publique utilisee pour acceder au
	* materiel emprunte a partir d'une autre classe.
	*
	* @return Un TypeDeMateriel representant le materiel emprunte.
	*/ 
	public TypeDeMateriel getMatEmprunt()
	{
		return emprunt;
	}

	/** 
	* Methode publique utilisee pour acceder a
	* l'emprunteur a partir d'une autre classe.
	* 
	* @return Personne representant l'emprunteur du materiel.
	*/ 
	public Personne getEmprunteur()
	{
		return emprunteur;
	}

	/** 
	* Methode publique utilisee pour acceder l'information
	* date d'emprunt a partir d'une autre classe.
	* 
	* @return Chaine de caractere contenant la date de l'emprunt.
	*/ 
	public String getDateEmprunt()
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
		return emprunt + "\nEmprunteur : "+ emprunteur.getNom().toUpperCase() + " " + emprunteur.getPrenom() + "\nDate d'emprunt : " + dateEmprunt +"\nDurée d'emprunt : " + dureeEmprunt + " jour(s)\n";
	}
}
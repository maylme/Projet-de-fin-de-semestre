package Materiel;

import java.util.UUID;

/** 
* La class Materiel représente un objet matériel.
* Un Materiel est defini par :
* des Caracteristiques
* un nombre de Materiel
* la duree maximum d'emprunt de ce materiel
*  
* @author Sonia Tual et Vincent Montalieu 
* @version 2.0 (4.Dec.2013) 
*/

public class Materiel implements java.io.Serializable
{
	public static final int DUREE_EMPRUNT_MAX_CLASSIQUE = 15;

	protected int nombreExemplaires ;
	protected int dureeMaxEmprunt;
	protected Caracteristiques caracteristiques;
	
	/** 
	* Constructeur de la classe.
	* exemplaires. 
	*/
	public Materiel(Caracteristiques c, int dureeMax, int nbExemplaires)
	{
		caracteristiques = c;
		nombreExemplaires = nbExemplaires;
		dureeMaxEmprunt = dureeMax;
	}

	/** 
	* Constructeur de la classe TypeDeMateriel
	* Il construit le matériel avec son nom passé en paramètre
	* et le nombre d'exemplaire. Il crée l'identifiant correspondant
	* à ce matériel.
	*
	* @param nomType Une chaine de caractère représentant le nom du type de matériel. 
	* @param nombreExemplaires Entier représentant le nombre d'exemplaire du matériel.
	*/
	public Materiel(String nomType, int nombreExemplaires)
	{
		this.nombreExemplaires = nombreExemplaires ;
		this.dureeMaxEmprunt=DUREE_EMPRUNT_MAX_CLASSIQUE;
	}

	/** 
	* Méthode publique permettant de faire un affichage du matériel.
	* Elle affiche le nom, le nombre d'exemplaire et 
	* l'identifiant avec un saut de ligne entre chaque.
	* 
	* @return La chaine de caractère contenant la description
	* du matériel à afficher.
	*/
	public String toString()
	{
		return "\nType de matériel : " + nomType + "\nNombre de matériel de ce type : " +  nombreExemplaires ;
	}

	/** 
	* Méthode publique utilisée pour accéder à la 
	* valeur nomType de la classe à partir d'une autre classe.
	* 
	* @return Chaine de caractère contenant le nom du type de matériel.
	*/ 
	public String getNom()
	{
		return nomType;
	}

	/** 
	* Méthode publique utilisée pour accéder à la 
	* valeur dureeMaxEmprunt de la classe à partir d'une autre classe.
	* 
	* @return int contenant la duree max d'emprunt du type de matériel.
	*/ 
	public int getDureeMaxEmprunt()
	{
		return dureeMaxEmprunt;
	}

	
	/** 
	* Méthode publique utilisée pour accéder à la 
	* valeur nombreExemplaire de la classe à partir d'une autre classe.
	* 
	* @return Entier contenant le nombre d'exemplaire du matériel.
	*/
	public int getNombre()
	{
		return nombreExemplaires;
	}
	
	/** 
	* Méthode publique utilisée pour accéder à la 
	* valeur indentifiant de la classe à partir d'une autre classe.
	* 
	* @return Chaine de caractère contenant l'identifiant du matériel.
	*/ 


	/** 
	* Méthode publique utilisée pour modifier la 
	* valeur du nombre d'exemplaire du matériel à partir d'une autre classe.
	* 
	* @param nombre Entier représentant le nombre d'exemplaire
	* que l'on veut mettre à la place de l'ancien.
	*/
	public void setNombre(int nombre)
	{
		nombreExemplaires = nombre ;
	}

	/** 
	* Méthode publique utilisée pour modifier la 
	* valeur du nombre d'exemplaire du matériel à partir d'une autre classe.
	* Cette méthode ajoute le nombre passé en paramètre au nombre
	* d'exemplaire déjà existant. 
	*
	* @param nombre Entier représentant le nombre d'exemplaire
	* que l'on veut ajouter.
	*/
	public void incrNombre(int nombre)
	{
		nombreExemplaires += nombre ;
	}

	/** 
	* Méthode publique utilisée pour modifier la 
	* valeur du nombre d'exemplaire du matériel à partir d'une autre classe.
	* Cette méthode retire le nombre passé en paramètre au nombre
	* d'exemplaire déjà existant. 
	* 
	* @param nombre Entier représentant le nombre d'exemplaire
	* que l'on veut retirer.
	*/
	public void decrNombre(int nombre)
	{
		nombreExemplaires -= nombre ;
	}	
}
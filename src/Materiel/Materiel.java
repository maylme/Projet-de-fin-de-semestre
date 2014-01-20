package Materiel;

import java.util.UUID;

/** 
* La class TypeDeMateriel représente un objet matériel.
* Il a un nom de type, le nombre d'exemplaire de ce matériel
* et un identifiant. 
* 
* @author Sonia Tual et Vincent Montalieu 
* @version 2.0 (4.Dec.2013) 
*/

public class Materiel implements java.io.Serializable
{
	protected String nomType;
	protected int nombreExemplaires ;
	protected String id = UUID.randomUUID().toString();
	public static final int DUREE_EMPRUNT_MAX_CLASSIQUE = 15;
	public static final int DUREE_EMPRUNT_MAX_SPEC = 30;
	protected int dateEmprunt;
	/** 
	* Constructeur par défaut de la classe.
	* Il crée l'objet Tablette Android avec 20
	* exemplaires. 
	*/
	public Materiel()
	{
		this("Tablette Android", 20) ;
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
		this.nomType = nomType ;
		this.nombreExemplaires = nombreExemplaires ;
		this.dateEmprunt=DUREE_EMPRUNT_MAX_CLASSIQUE;
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
	public String getId()
	{
		return id;
	}

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
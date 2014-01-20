package base;

/** 
* La class TypeDeMateriel représente un objet matériel.
* Il a un nom de type, le nombre d'exemplaire de ce matériel
* et un identifiant. 
* 
* @author Sonia Tual et Vincent Montalieu 
* @version 2.0 (4.Dec.2013) 
*/

public class TypeDeMateriel implements java.io.Serializable
{
	private String nomType ;
	private int nombreExemplaires ;
	private static int IDENT_BASE = 1000 ;
	private String ident ;

	/** 
	* Constructeur par défaut de la classe.
	* Il crée l'objet Tablette Android avec 20
	* exemplaires. 
	*/
	public TypeDeMateriel()
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
	public TypeDeMateriel(String nomType, int nombreExemplaires)
	{
		this.nomType = nomType ;
		this.nombreExemplaires = nombreExemplaires ;

		if(nomType.length()>=3)
		{
			ident = nomType.substring(0,3).toUpperCase() + IDENT_BASE ;
		}

		else
		{
			ident = nomType.toUpperCase() + IDENT_BASE ;
		}		

		IDENT_BASE ++ ;		
	}

	/** 
	* Constructeur permettant de créer un matériel grâce au nom du type
	* passé en paramètre, le nombre d'exemplaire et l'identifiant. 
	* 
	* @param nomType Nom du type de matériel créé.
	* @param nombreExemplaires Nombre d'exemplaire du matériel
	* @param ident Identifiant du matériel
	*/ 
	public TypeDeMateriel(String nomType, int nombreExemplaires, String ident)
	{
		this.nomType = nomType ;
		this.nombreExemplaires = nombreExemplaires ;
		this.ident = ident ;
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
		return "\nType de matériel : " + nomType + "\nNombre de matériel de ce type : " +  nombreExemplaires + "\nIdentification : " + ident ;
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
	public String getIdent()
	{
		return ident;
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
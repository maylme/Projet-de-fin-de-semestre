package utilisateurs;

/** 
* La classe Personne represente une personne avec
* son nom et son prenom 
* d'emprunts.
* 
* @version 2.0 (20.Jan.2014) 
*/

public class Personne implements java.io.Serializable
{
	protected String nom ;
	protected String prenom ;

	/** 
	* Constructeur de la classe Personne
	* Il construit une personne avec son nom passe en parametre et
	* son prenom.
	*
	* @param nom Le nom de la personne
	* @param prenom Le prenom de la personne
	*/ 
	public Personne(String nom, String prenom)
	{
		if (nom!="" && prenom!="")
		{
			this.nom = nom ;
			this.prenom = prenom ;
		}
	}

	

	/** 
	* Constructeur par defaut de la classe Personne.
	* Il cree une personne emprunteur de nom a et de prenom b.
	*/
	public Personne()
	{
		this("a","b");
	}

	/** 
	* Methode publique utilisee pour acceder a la 
	* valeur nom de la classe a partir d'une autre classe.
	* 
	* @return Chaine de caractere contenant le nom de la personne.
	*/
	public String getNom()
	{
		return nom;
	}

	/** 
	* Methode publique utilisee pour acceder a la 
	* valeur prenom de la classe a partir d'une autre classe.
	* 
	* @return Chaine de caractere contenant le prenom de la personne.
	*/
	public String getPrenom()
	{
		return prenom;
	}

	

	/** 
	* Methode publique permettant de comparer deux personnes.
	* Il compare si le nom et le prenom sont les memes.
	* 
	* @param unePersonne Objet representant une personne
	* @return retourne un boolean true si c'est la meme personne
	* sinon retourne faux.
	*/
	public boolean equals(Personne unePersonne)
	{
		if (nom.toUpperCase().equals(unePersonne.getNom().toUpperCase()) && prenom.toUpperCase().equals(unePersonne.getPrenom().toUpperCase()))
			return true;
		else
			return false;
	}

	/** 
	* Methode publique permettant de faire un affichage d'une personne.
	* Elle affiche le nom (en majuscule), le prenom.
	* 
	* @return La chaine de caractere contenant la description
	* de la personne a afficher.
	*/
	public String toString()
	{
		return nom.toUpperCase() + " " + prenom + "\n";
	}


}
package base;

/** 
* La classe Personne represente une personne avec
* son nom, son prenom, son statut et son nombre
* d'emprunts.
* 
* @author Sonia Tual et Vincent Montalieu
* @version 1.0 (4.Dec.2013) 
*/

public class Personne implements java.io.Serializable
{
	private String nom ;
	private String prenom ;
	private boolean statut ;
	private int nombreEmprunts ;

	/** 
	* Constructeur de la classe Personne
	* Il construit une personne avec son nom passe en parametre,
	* son prenom et le statut.
	* Le parametre statut permet de differencier les emprunteurs
	* des gestionnnaires, false correspond aux emprunteurs et true
	* aux gestionnnaires.
	*
	* @param nom Le nom de la personne
	* @param prenom Le prenom de la personne
	* @param statut Le statut permettant de distinguer un emprunteur
	* d'un gestionnnaire.
	*/ 
	public Personne(String nom, String prenom, boolean statut)
	{
		if (nom!="" && prenom!="")
		{
			this.nom = nom ;
			this.prenom = prenom ;
			nombreEmprunts = 0 ;
			this.statut = statut ;
		}
	}

	/** 
	* Constructeur de la classe Personne.
	* Il cree une personne avec objet Personne passe en 
	* parametre, et le nombre d'emprunts. Il reprend les informations
	* de la personne et modifie le nombre d'emprunts.
	* 
	* @param utilisateur Personne a partir de laquelle on cree
	* la nouvelle personne.
	* @param emprunts Entier representant le nombre d'emprunts
	* que la personne a effectue. 
	*/
	public Personne(Personne utilisateur, int emprunts)
	{
		nom = utilisateur.getNom() ;
		prenom = utilisateur.getPrenom() ;
		nombreEmprunts = emprunts ;
	}

	/** 
	* Constructeur par defaut de la classe Personne.
	* Il cree une personne emprunteur de nom a et de prenom b.
	*/
	public Personne()
	{
		this("a","b",false);
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
	* Methode publique utilisee pour acceder a la 
	* valeur nombre d'emprunts de la classe a partir d'une autre classe.
	* 
	* @return Entier contenant le nombre d'emprunts de la personne.
	*/
	public int getNbrEmprunt()
	{
		return nombreEmprunts;
	}

	/** 
	* Methode publique utilisee pour modifier la 
	* valeur du nombre d'emprunt de la personne a partir
	* d'une autre classe.
	* 
	* @param nombreEmprunts Entier representant le nombre
	* d'emprunts que la personne a effectue
	*/
	public void setNbrEmprunt(int nombreEmprunts)
	{
		this.nombreEmprunts=nombreEmprunts;
	}

	/** 
	* Methode publique utilisee pour incrementer la 
	* valeur du nombre d'emprunt de la personne a partir
	* d'une autre classe.
	* 
	* @param nbrEmprunt Entier representant le nombre
	* d'emprunts ajoute.
	*/
	public void incrementNbrEmprunt(int nbrEmprunt)
	{
		nombreEmprunts+=nbrEmprunt;
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
	* Elle affiche le nom (en majuscule), le prenom et le statut.
	* 
	* @return La chaine de caractere contenant la description
	* de la personne a afficher.
	*/
	public String toString()
	{
		return nom.toUpperCase() + " " + prenom + statutString() + "\n";
	}

	/** 
	* Methode publique permettant de renvoyer le statut ecrit en
	* toute lettre.
	* 
	* @return La chaine de caractere contenant le statut.
	*/
	public String statutString()
	{
		if(statut)
		{
			return " - Gestionnaire";
		}

		else
		{
			return " - Emprunteur";			
		}
	}

	/** 
	* Methode publique permettant de renvoyer le statut de la personne.
	* 
	* @return Retourne la variable boolean statut.
	*/
	public boolean getStatut()
	{
		return statut ;
	}
}
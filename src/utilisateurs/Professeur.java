package utilisateurs;

/**
 * Un Professeur est un Emprunteur
 * les Professeurs qui peuvent emprunter plusieurs matériels
 * pour un cours donné. Les enseignants peuvent garder le matériel 
 * pour une durée longue si le matériel est disponible. 
 * Ils peuvent le réserver à l'avance pour la période de leur cours.
 * 
 * @author lyameina
 *
 */
@SuppressWarnings("serial")
public class Professeur extends Emprunteur{

	private static final int DUREEMAXEMPRUNTPROF=30;
	
	/** 
	* Constructeur de la classe Professeur
	* Il construit un Emprunteur avec son nom passe en parametre et
	* son prenom
	*
	* @param nom Le nom de la personne
	* @param prenom Le prenom de la personne
	*/ 
	public Professeur(String nom, String prenom)
	{
		super(nom, prenom, DUREEMAXEMPRUNTPROF);
	}
	
	/** 
	* Constructeur de la classe Professeur.
	* Il cree une personne avec objet Personne passe en 
	* parametre. Il reprend les informations
	* de la personne et modifie le nombre d'emprunts.
	* 
	* @param utilisateur Personne a partir de laquelle on cree
	* un nouvel eleve.
	*/
	public Professeur(Personne utilisateur)
	{
		super(utilisateur, DUREEMAXEMPRUNTPROF);
	}

	public String typeEmprunteur(){
		return "Professeur";
	}
	
	/**Constructeur par defaut d'un Emprunteur 
	 * de nom : a et prenom : b
	 *
	 */
	public Professeur()
	{
		super();
	}
}

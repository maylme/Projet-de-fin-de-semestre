package utilisateurs;
/**
 * Un emprunteur est une personne qui n'est pas un gestionnaire
 * 
 * @author lyameina
 *
 */
public class Emprunteur extends Personne{
	
	protected int nombreEmprunts ;
	
	/** 
	* Constructeur de la classe Emprunteur
	* Il construit un Emprunteur avec son nom passe en parametre,
	* son prenom et son nombre d'Emprun
	*
	* @param nom Le nom de la personne
	* @param prenom Le prenom de la personne
	* @param nbEmprunt le nombre d'objets empruntés au total.
	*/ 
	public Emprunteur(String nom, String prenom)
	{
		super(nom,prenom);
		nombreEmprunts = 0;
		
	}
	
	/** 
	* Constructeur de la classe Emprunteur.
	* Il cree une personne avec objet Personne passe en 
	* parametre, et le nombre d'emprunts. Il reprend les informations
	* de la personne et modifie le nombre d'emprunts.
	* 
	* @param utilisateur Personne a partir de laquelle on cree
	* la nouvelle personne.
	* @param emprunts Entier representant le nombre d'emprunts
	* que la personne a effectue. 
	*/
	public Emprunteur(Personne utilisateur)
	{
		nom = utilisateur.getNom() ;
		prenom = utilisateur.getPrenom() ;
		nombreEmprunts = 0 ;
	}

	
	/**Constructeur par defaut d'un Emprunteur 
	 * de nom : a et prenom : b
	 *
	 */
	public Emprunteur()
	{
		super();
		nombreEmprunts = 0;
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
}

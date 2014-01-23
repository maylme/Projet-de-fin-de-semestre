package utilisateurs;
/**
 * Un emprunteur est une personne qui n'est pas un gestionnaire
 * 
 * @author lyameina
 *
 */

public class Emprunteur extends Personne implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final int DUREE_MAX_DEMPRUNT = 15;
	protected int nombreEmprunts ;
	protected int dureeMaxDEmprunt;
	
	
	public Emprunteur (String nom, String prenom){
		super(nom, prenom);
		dureeMaxDEmprunt = DUREE_MAX_DEMPRUNT;
	}
	/** 
	* Constructeur de la classe Emprunteur
	* Il construit un Emprunteur avec son nom passe en parametre,
	* son prenom et sa duree max d'Emprunt
	*
	* @param nom Le nom de la personne
	* @param prenom Le prenom de la personne
	* @param duree La duree max d'emprunt de la personne
	*/ 
	public Emprunteur(String nom, String prenom, int duree)
	{
		super(nom,prenom);
		nombreEmprunts = 0;
		dureeMaxDEmprunt= duree;
		
	}
	
	/** 
	* Constructeur de la classe Emprunteur.
	* Il cree une personne avec objet Personne passe en 
	* parametre, et la duree max d'emprunts. Il reprend les informations
	* de la personne et modifie le nombre d'emprunts.
	* 
	* @param utilisateur Personne a partir de laquelle on cree
	* la nouvelle personne.
	* @param duree La duree max d'emprunt de la personne
	*/
	public Emprunteur(Personne utilisateur, int duree)
	{
		nom = utilisateur.getNom() ;
		prenom = utilisateur.getPrenom() ;
		nombreEmprunts = 0 ;
		dureeMaxDEmprunt = duree;
	}

	
	/**Constructeur par defaut d'un Emprunteur 
	 * de nom : a et prenom : b
	 *
	 */
	public Emprunteur()
	{
		super();
		nombreEmprunts = 0;
		dureeMaxDEmprunt = DUREE_MAX_DEMPRUNT;
	}
	
	/**
	 * Accesseur de la duree maximum d'emprunt 
	 * @return la duree maximum d'emprunt d'un objet
	 */
	public int getDureeMaxDEmprunt(){
		return dureeMaxDEmprunt;
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
	
	public String typeEmprunteur(){
		return "Emprunteur";
	}
	@Override
   public int hashCode(){
       int result = 0;
       result = 31*result+ dureeMaxDEmprunt;
       result = 31*result + (nom !=null ? nom.hashCode() : 0);
       result = 31*result + (prenom  !=null ? prenom.hashCode() : 0);
      
       return result;
   }

}

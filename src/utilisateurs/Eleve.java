package utilisateurs;
/**
 * Un eleve est un empreinteur.
 * Les étudiant peuvent emprunter le matériel 
 * sur une période courte. Ils ne peuvent pas faire de 
 * réservation plus d'une semaine à l'avance.
 * @author lyameina
 *
 */
public class Eleve extends Emprunteur{
	
	private int dureeMaxDEmprunt;
	
	/** 
	* Constructeur de la classe Eleve
	* Il construit un Emprunteur avec son nom passe en parametre,
	* son prenom et son nombre d'Emprun
	*
	* @param nom Le nom de la personne
	* @param prenom Le prenom de la personne
	* @param dureeMax la duree maximum d'emprunt
	*/ 
	public Eleve(String nom, String prenom,int dureeMax)
	{
		super(nom, prenom);
		dureeMaxDEmprunt = dureeMax;
	}
	
	/** 
	* Constructeur de la classe Eleve.
	* Il cree une personne avec objet Personne passe en 
	* parametre, et le nombre d'emprunts. Il reprend les informations
	* de la personne et modifie le nombre d'emprunts.
	* 
	* @param utilisateur Personne a partir de laquelle on cree
	* la nouvelle personne.
	* @param emprunts Entier representant le nombre d'emprunts
	* que la personne a effectue. 
	* @param dureeMax la duree maximale d'emprunt
	*/
	public Eleve(Personne utilisateur, int dureeMax)
	{
		super(utilisateur);
		dureeMaxDEmprunt = dureeMax;
	}

	
	/**Constructeur par defaut d'un Emprunteur 
	 * de nom : a et prenom : b
	 *
	 */
	public Eleve()
	{
		super();
		nombreEmprunts = 0;
	}
	
	/**
	 * Accesseur de la duree maximum d'emprunt 
	 * @return la duree maximum d'emprunt d'un objet
	 */
	public int getDureeMaxDEmprunt(){
		return dureeMaxDEmprunt;
	}
	
	/**
	 * Modificateur de la duree maximum d'emprunt
	 * @param duree la nouvelle duree maximum d'emprunt
	 */
	public void setDureeMaxDEmprunt(int duree){
		dureeMaxDEmprunt = duree;
	}

}

package Materiel;

import java.util.Date;

import utilisateurs.Emprunteur;
import utilisateurs.Personne;
import utilisateurs.Professeur;
import utilisateurs.Eleve;

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
	public static final int DUREE_EMPRUNT_MAX = 15;

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
	* Constructeur de la classe Materiel
	* Il construit le matériel avec ses caracterisque en paramètre
	*
	* @param c Caractéristiques du materiel.
	*/
	public Materiel(Caracteristiques c)
	{
		this.nombreExemplaires = 1 ;
		this.dureeMaxEmprunt=DUREE_EMPRUNT_MAX;
		caracteristiques = c;
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
		return "\nCaractéristiques du matériel : " + caracteristiques + "\nNombre de matériel de ce type : " +  nombreExemplaires + "/nDuree max d'emprunt : " + dureeMaxEmprunt ;
	}

	/** 
	* Méthode publique utilisée pour accéder aux 
	* caractéristiques du matériel à partir d'une autre classe.
	* 
	* @return Caracteristiques contenant les caractéristiques du matériel.
	*/ 
	public Caracteristiques getCaracteristiques()
	{
		return caracteristiques;
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
	
	/** 
	* Méthode publique utilisée pour vérifier si deux types 
	* Materiel ont les mêmes caractéristiques et la même
	* durée max d'emprunt.
	* 
	* @param mat Materiel.
	* @return boolean representant le résultat de l'égalité entre deux Materiel.
	*/
	public boolean equals(Materiel mat)
	{
		if (this.dureeMaxEmprunt == mat.getDureeMaxEmprunt()){
			if (this.caracteristiques.equals(mat.getCaracteristiques())){
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	/** 
	* Méthode publique utilisée pour renvoyer le materiel
	* emprunte ou null si cela ne satisfait pas les conditions
	* d'emprunt du materiel
	* 
	* @param debut Date de début de l'emprunt.
	* @param duree Entier representant la duree de l'emprunt.
	* @param emprunteur Emprunteur représente la personne demandant un emprunt.
	* @return MaterielEmprunte modelisant le materiel emprunte avec l'emprunteur
	* la date et la duree de l'emprunt.
	*/
	public MaterielEmprunte matEmprunte(Date debut, Date fin, Emprunteur emprunteur)
	{
		return new MaterielEmprunte(this, emprunteur, debut, fin);
	}
	
	/** 
	* Méthode publique permettant de créé un clone du materiel
	* 
	* @param mat Materiel a cloner.
	* @return Materiel qui a été cloné.
	*/
	public Materiel clone()
	{
		return new Materiel(caracteristiques, dureeMaxEmprunt, nombreExemplaires);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Materiel && ((Materiel) obj).getCaracteristiques().equals(caracteristiques) && ((Materiel)obj).getDureeMaxEmprunt()==dureeMaxEmprunt && ((Materiel)obj).getNombre()==nombreExemplaires )
			return true;
		
		else
			return false;
	}
	
	@Override
   public int hashCode(){
       int result = 0;
       result = 31*result + dureeMaxEmprunt;
       result = 31*result + (caracteristiques !=null ? caracteristiques.hashCode() : 0);
       result = 31*result + nombreExemplaires;
      
       return result;
   }
	
	/**
	 * Methode qui renvoie un boolean pour savoir si l'emprunt de ce materiel est possible
	 * Pour un Materiel "de base" on considère que c'est possible.
	 * @param dateDebut
	 * @param duree
	 * @param e
	 * @return
	 */
	public boolean empruntable(Date dateDebut, Date dateFin, Emprunteur e ){
		long dureeEmprunt = (dateFin.getTime() - dateDebut.getTime())/ (1000*60*60*24);
		if (dureeMaxEmprunt < dureeEmprunt){
			if (e.getDureeMaxDEmprunt() <= (int) dureeEmprunt){
				
				if (e.typeEmprunteur().equals("Eleve")){
					int dureeMaxReservation = ((Eleve)e).getDureeMaxReservation();
					Date today = new Date();
					long dureeAujourdhuiResevation = (dateDebut.getTime() - today.getTime())/ (1000*60*60*24);
					if (dureeAujourdhuiResevation > dureeMaxReservation)
						return false;
					else
						return true;
				}
				else
					return true;
			}
			return false;
		}
		return false;
	}
}
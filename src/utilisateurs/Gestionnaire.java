package utilisateurs;

/**
 * Penser à virer le boolean statu. Et mettre les specificité d'un gestionnaire
 * ici.
 * 
 * @author lyameina
 */
@SuppressWarnings("serial")
public class Gestionnaire extends Personne implements java.io.Serializable {

    /**
     * Constructeur de la classe Gestionnaire Il construit un Emprunteur avec
     * son nom passe en parametre et son prenom
     * 
     * @param nom
     *            Le nom de la personne
     * @param prenom
     *            Le prenom de la personne
     */
    public Gestionnaire(String nom, String prenom) {
        super(nom, prenom);
    }

    /**
     * Constructeur de la classe Gestionnaire Il cree une personne avec objet
     * Personne passe en parametre. Il reprend les informations de la personne.
     * 
     * @param utilisateur
     *            Personne a partir de laquelle on cree la nouvelle personne.
     */
    public Gestionnaire(Personne utilisateur) {
        nom = utilisateur.getNom();
        prenom = utilisateur.getPrenom();
    }

    /**
     * Constructeur par defaut d'un Gestionnaire de nom : a et prenom : b
     * 
     */
    public Gestionnaire() {
        super();
    }

}

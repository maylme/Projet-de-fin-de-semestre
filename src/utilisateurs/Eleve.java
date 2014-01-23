package utilisateurs;

/**
 * Un eleve est un emprunteur. Les étudiants peuvent emprunter le matériel sur
 * une période courte. Ils ne peuvent pas faire de réservation plus d'une
 * semaine à l'avance.
 * 
 * @author lyameina
 * 
 */
@SuppressWarnings("serial")
public class Eleve extends Emprunteur implements java.io.Serializable {

    private static final int DUREEMAXEMPRUNTELEVE = 15;
    private static final int DUREEMAXRESERVATION = 8;

    /**
     * Constructeur de la classe Eleve Il construit un Emprunteur avec son nom
     * passe en parametre, son prenom
     * 
     * @param nom
     *            Le nom de la personne
     * @param prenom
     *            Le prenom de la personne
     */
    public Eleve(String nom, String prenom) {
        super(nom, prenom, DUREEMAXEMPRUNTELEVE);
    }

    /**
     * Constructeur de la classe Eleve. Il cree une personne avec objet Personne
     * passe en parametre. Il reprend les informations de la personne et modifie
     * le nombre d'emprunts.
     * 
     * @param utilisateur
     *            Personne a partir de laquelle on cree un nouvel eleve.
     */
    public Eleve(Personne utilisateur) {
        super(utilisateur, DUREEMAXEMPRUNTELEVE);
    }

    /**
     * Constructeur par defaut d'un Emprunteur de nom : a et prenom : b
     * 
     */
    public Eleve() {
        super();
    }

    public String typeEmprunteur() {
        return "Eleve";
    }

    /**
     * Accesseur de la duree maximum de reservation
     * 
     * @return la duree maximum de reservation d'un objet
     */
    public int getDureeMaxReservation() {
        return DUREEMAXRESERVATION;
    }
}

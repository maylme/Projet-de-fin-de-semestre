package materiel;

import java.util.ArrayList;
import java.util.HashMap;

import outils.FichierData;


/**
 * Contient les caracteristiques pour construire un Materiel. Un objet
 * Caracteristiques est representé par un HashMap contenant comme cle les
 * catégories de carcteristiques (Type, Marque, Poids...) et une valeur
 * associée, le tout sous forme de chaines de caracteres. Les cles entrées par
 * le gestionnaire au moment de créer des caracteristiques doivent appartenir à
 * l'ensemble des cles contenues dans clePossible, sans quoi il devra confirmer
 * l'ajout d'une nouvelle clé à cette liste.
 * 
 * @author Maylanie MESNIER
 * 
 */
@SuppressWarnings("serial")
public class Caracteristiques implements java.io.Serializable {
    private ArrayList<String> clePossible;
    private final String NOMFICHIER = "listeCaracteristiques";
    private HashMap<String, String> resultat;
    
    /**
     * Constructeur par défaut qui crée une liste vide de caractéristiques et importe
     * la liste des cles possibles crées auparavant dans la liste ClePossible.
     * 
     */
    public Caracteristiques() {
        FichierData f = new FichierData();
        clePossible = f.deserialisationListeString(NOMFICHIER);
        resultat = new HashMap<String, String>();
    }

    /**
     * Constructeur qui initialise la liste des caracteristiques avec son paramètre h.
     * 
     * @param h
     *            - HashMap representant les caracteristiques
     */
    public Caracteristiques(HashMap<String, String> h) {
        FichierData f = new FichierData();
        clePossible = f.deserialisationListeString(NOMFICHIER);
        resultat = h;
    }
    
    
    /**
     * Méthode permettant d'ajouter des caracteristiques de type categorie-valeur.
     * 
     * 
     * 
     * @param cle
     *          - La specification que l'on veut ajouter
     * @param valeur
     *          - La valeur de la specification
     * @throws CleInexistanteException
     */
    public void addSpecification(String cle, String valeur)
            throws CleInexistanteException {
        if (!(clePossible.contains(cle))) {
            throw new CleInexistanteException();
        } else {
            resultat.put(cle, valeur);
        }
    }
    /**
     * Methode qui permet de verifier qu'une cle est présente dans la liste des
     * cles possibles.
     * 
     * @param cle
     *          - La cle à rechercher
     * @return true si la cle est presente, false sinon.
     */
    public boolean existeCle(String cle) {
        if (!(clePossible.contains(cle))) {
            return false;
        }
        return true;
    }
    
    
    /**
     * Methode permettant d'ajouter une cle à la liste des cles possibles.
     * 
     * @param cle
     *            - La clé à ajouter.
     */
    public void addCle(String cle) {
        if (!(existeCle(cle))) {
            clePossible.add(cle);
            FichierData f = new FichierData();
            f.serialisationListeString(clePossible, NOMFICHIER);
        }
    }
    
    
    /**
     * Getter de la liste resultat.
     * @return Le HashMap contenant les caracteristiques
     */
    public HashMap<String, String> getResultat() {
        return resultat;
    }

    /**
     * Getter de la liste des cles possibles ClePossible
     * @return 
     *          La liste clePossible
     */
    public ArrayList<String> getClePossible() {
        return clePossible;
    }
    
    /**
     * Redéfinition de la methode equals pour la classe Caracteristiques.
     * 
     * @param c
     *          - La caracteristique à comparer
     * @return
     *          True si la caracteristique courante et la caracteristique c ont les 
     *          mêmes cles et valeurs.
     */
    public boolean equals(Caracteristiques c) {
        if (c.getResultat().equals(resultat)) {
            for (String cle : resultat.keySet()) {
                if (!(c.getResultat().get(cle).equals(resultat.get(cle))))
                    return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (resultat != null ? resultat.hashCode() : 0);
        result = 31 * result
                + (clePossible != null ? clePossible.hashCode() : 0);
        return result;
    }
    
    /**
     * Methode qui vérifie si une valeur de caracteristique est présente dans resultat.
     * 
     * @param motAChercher
     *          La caractéristique recherchée.
     * @return
     *          true si la caractéristique est trouvée, false sinon.
     */
    public boolean searchValue(String motAChercher) {
        return resultat.containsValue(motAChercher);
    }

    @Override
    public String toString() {
        String res = "";
        for (String key : resultat.keySet()) {
            res += key + " : " + resultat.get(key) + "\n";
        }
        return res;
    }
}


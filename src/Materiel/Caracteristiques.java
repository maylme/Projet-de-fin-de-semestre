package Materiel;

import java.util.ArrayList;
import java.util.HashMap;

import Outils.FichierData;

/**
 * Contient les caracteristiques pour construire un Materiel Un objet
 * Caracteristiques est representé par un hash map dont les possibilités de cles
 * sont restreinte. Quand le gestionnaire rentrera les caracteristique, il devra
 * utiliser les cles existentes ou en creer de nouvelle pour les utiliser.
 * 
 * @author lyameina
 * 
 */
@SuppressWarnings("serial")
public class Caracteristiques implements java.io.Serializable {
    private ArrayList<String> clePossible;
    private final String NOMFICHIER = "listeCaracteristiques";
    private HashMap<String, String> resultat;

    public Caracteristiques() {
        FichierData f = new FichierData();
        clePossible = f.deserialisationListeString(NOMFICHIER);
        resultat = new HashMap<String, String>();
    }

    /**
     * constructeur utilisé dans la classe gestion car tous est verifier au fur
     * et à mesure.
     * 
     * @param h
     *            HashMap representant les caracteristiques
     */
    public Caracteristiques(HashMap<String, String> h) {
        FichierData f = new FichierData();
        clePossible = f.deserialisationListeString(NOMFICHIER);
        resultat = h;
    }

    public void addSpecification(String cle, String valeur)
            throws CleInexistanteException {
        if (!(clePossible.contains(cle))) {
            throw new CleInexistanteException();
        } else {
            resultat.put(cle, valeur);
        }
    }

    public boolean existeCle(String cle) {
        if (!(clePossible.contains(cle))) {
            return false;
        }
        return true;
    }

    public void addCle(String cle) {
        if (existeCle(cle)) {
            clePossible.add(cle);
            FichierData f = new FichierData();
            f.serialisationListeString(clePossible, NOMFICHIER);
        }
    }

    public HashMap<String, String> getResultat() {
        return resultat;
    }

    public ArrayList<String> getClePossible() {
        return clePossible;
    }

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

    public boolean searchValue(String motAChercher) {
        return resultat.containsValue(motAChercher);
    }

    public String toString() {
        String res = "";
        for (String key : resultat.keySet()) {
            res += key + " : " + resultat.get(key) + "\n";
        }
        return res;
    }
}
/**
 * GEstion ------------------------------ Caracteristiques c = new
 * Caracteristique() ;
 * 
 * String cle = Cle que le gars veut utiliser; String valeur = valeur que le
 * gars veut utiliser
 * 
 * 
 * try{ c.addSpecification (cle, valeur); } catch (CleInexistanteEcxeption e){
 * demander si il veut creer la cle si oui: c.addCle(String cle)
 * c.addSpecification (cle, valeur) }
 * 
 * }
 */

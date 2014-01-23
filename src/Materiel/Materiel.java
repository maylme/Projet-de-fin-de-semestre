package Materiel;

import java.util.Date;

import utilisateurs.Emprunteur;
import utilisateurs.Eleve;

/**
 * La classe Materiel représente un groupement de matériel identique.
 * Un objet Materiel est représenté par : 
 * - des caractéristiques représentées par la classe Caracteristiques
 * - une duree maximum d'emprunt
 * - un nombre d'exemplaires
 * 
 * @author Sonia Tual et Vincent Montalieu
 * @version 2.0 (4.Dec.2013)
 */

public class Materiel implements java.io.Serializable {

    public static final int DUREE_EMPRUNT_MAX = 15;
    protected int nombreExemplaires;
    protected int dureeMaxEmprunt;
    protected Caracteristiques caracteristiques;

    /**
     * Constructeur principal de la classe Materiel avec toutes les informations nécessaires
     * passées en paramètre.
     * 
     * @param c
     *          Les caracteristiques du matériel
     * @param dureeMax
     *          La durée max d'emprunt
     * @param nbExemplaires
     *          Le nombre d'exemplaires
     */
    public Materiel(Caracteristiques c, int dureeMax, int nbExemplaires) {
        caracteristiques = c;
        nombreExemplaires = nbExemplaires;
        dureeMaxEmprunt = dureeMax;
    }
    
    /**
     * Constructeur de la classe Materiel qui initialise automatiquement la duree 
     * max d'emprunt à 15 jours.
     * 
     * @param c
     *          Les caracteristiques du matériel
     * @param nbExemplaires
     *          Le nombre d'exemplaires
     */
    public Materiel(Caracteristiques c, int nbExemplaires) {
        caracteristiques = c;
        nombreExemplaires = nbExemplaires;
        dureeMaxEmprunt = DUREE_EMPRUNT_MAX;
    }

    /**
     * Constructeur de la classe Materiel. Il construit le matériel avec seulement
     * ses caracteristiques en paramètre, la durée emprunt max est initialisée à 15 par
     * défaut, et le nombre d'exemplaires à 1.
     * 
     * @param c
     *            Caractéristiques du materiel.
     */
    public Materiel(Caracteristiques c) {
        this.nombreExemplaires = 1;
        this.dureeMaxEmprunt = DUREE_EMPRUNT_MAX;
        caracteristiques = c;
    }

    /**
     * Redéfinition de la méthode toString pour un objet Materiel. Elle
     * affiche les caractéristiques, le nombre d'exemplaires et la durée max 
     * d'emprunt du matériel.
     * 
     * @return La chaine de caractère contenant la description du matériel à
     *         afficher.
     */
    public String toString() {
        return "\nCaractéristiques du matériel : " + caracteristiques
                + "\nNombre de matériel de ce type : " + nombreExemplaires
                + "\nDuree max d'emprunt : " + dureeMaxEmprunt;
    }

    /**
     * Méthode utilisée pour accéder aux caractéristiques du matériel.
     * 
     * @return Un objet Caracteristiques contenant les caractéristiques du matériel
     */
    public Caracteristiques getCaracteristiques() {
        return caracteristiques;
    }

    /**
     * Méthode utilisée pour accéder à la valeur dureeMaxEmprunt de la
     * classe
     * 
     * @return entier correspondant à la duree max d'emprunt du matériel.
     */
    public int getDureeMaxEmprunt() {
        return dureeMaxEmprunt;
    }

    /**
     * Méthode utilisée pour accéder à la valeur nombreExemplaire de la
     * classe. 
     * 
     * @return Entier contenant le nombre d'exemplaire du matériel.
     */
    public int getNombre() {
        return nombreExemplaires;
    }

    /**
     * Méthode permettant de modifier la valeur du nombre d'exemplaire
     * du matériel.
     * 
     * @param nombre
     *            Entier représentant le nouveau nombre d'exemplaires
     */
    public void setNombre(int nombre) {
        nombreExemplaires = nombre;
    }

    /**
     * Méthode utilisée pour incrémenter la valeur du nombre d'exemplaires
     * du matériel avec la valeur nombre.
     * 
     * @param nombre
     *            Entier représentant le nombre d'exemplaire que l'on veut
     *            ajouter.
     */
    public void incrNombre(int nombre) {
        nombreExemplaires += nombre;
    }

    /**
     * Méthode utilisée pour décrémenter le nombre d'exemplaires
     * du matériel de la valeur nombre.
     * 
     * @param nombre
     *            Entier représentant le nombre d'exemplaire que l'on veut
     *            retirer.
     */
    public void decrNombre(int nombre) {
        nombreExemplaires -= nombre;
    }

    /**
     * Méthode utilisée pour vérifier si deux objets Materiel ont les
     * mêmes caractéristiques et la même durée maximale d'emprunt.
     * 
     * @param mat
     *            Le matériel à comparer
     * @return boolean representant le résultat de l'égalité entre deux
     *         Materiel
     */
    public boolean equals(Materiel mat) {
        if (this.dureeMaxEmprunt == mat.getDureeMaxEmprunt()) {
            if (this.caracteristiques.equals(mat.getCaracteristiques())) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    /**
     * Méthode publique permettant de créer un clone du materiel
     * 
     * @param mat
     *            Materiel a cloner.
     * @return Materiel qui a été cloné.
     */
    public Materiel clone() {
        return new Materiel(caracteristiques, dureeMaxEmprunt,
                nombreExemplaires);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Materiel
                && ((Materiel) obj).getCaracteristiques().equals(
                        caracteristiques)
                && ((Materiel) obj).getDureeMaxEmprunt() == dureeMaxEmprunt
                && ((Materiel) obj).getNombre() == nombreExemplaires)
            return true;

        else
            return false;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + dureeMaxEmprunt;
        result = 31 * result
                + (caracteristiques != null ? caracteristiques.hashCode() : 0);
        result = 31 * result + nombreExemplaires;

        return result;
    }

    /**
     * Methode qui renvoie un boolean pour savoir si l'emprunt de ce materiel
     * est possible ou non.
     * Pour un objet Materiel "de base", cela dépend de la duree d'emprunt maximum
     * du matériel et de la durée d'emprunt maximum de l'emprunteur s'il en a une.
     * Dans notre cas, seul l'eleve a une duree max d'emprunt.
     * 
     * @param dateDebut
     *          la date de début de l'emprunt ou la reservation
     * @param dateFin
     *          la date de fin de l'emprunt et la reservation
     * @param e
     *          l'emprunteur qui souhaite emprunter ce matériel
     * @return true si l'emprunt est possible par l'emprunteur e, false sinon
     */
    public boolean empruntable(Date dateDebut, Date dateFin, Emprunteur e) {
        long dureeEmprunt = (dateFin.getTime() - dateDebut.getTime())
                / (1000 * 60 * 60 * 24);
        if (dureeMaxEmprunt > dureeEmprunt) {
            if (e.getDureeMaxDEmprunt() >= (int) dureeEmprunt) {

                if (e.typeEmprunteur().equals("Eleve")) {
                    int dureeMaxReservation = ((Eleve) e)
                            .getDureeMaxReservation();
                    Date today = new Date();
                    long dureeAujourdhuiResevation = (dateDebut.getTime() - today
                            .getTime()) / (1000 * 60 * 60 * 24);
                    if (dureeAujourdhuiResevation > dureeMaxReservation)
                        return false;
                    else
                        return true;
                } else
                    return true;
            }
            return false;
        }
        return false;
    }
}
package Materiel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.io.*;

import utilisateurs.Emprunteur;
import utilisateurs.Personne;

/**
 * La class Stock represente un stock qui contient plusieurs HashMap.
 * 
 * @author Sonia Tual et Vincent Montalieu
 * @version 2.1 (4.Dec.2013)
 */

public class Stock {

    private HashMap<String, Materiel> disponible;
    private HashMap<String, Materiel> reparations;
    private HashMap<String, MaterielEmprunte> emprunts;
    private HashMap<String, Materiel> statistiquesReparations;
    private HashMap<String, Materiel> statistiquesEmprunts;

    /**
     * Constructeur de la classe Stock Il construit le stock avec les 5 listes
     * (disponible, reparations, emprunts, statistiquesReparations,
     * statistiquesEmprunts).
     */
    public Stock() {
        disponible = deserialisationListeTypeDeMateriel("disponible");
        reparations = deserialisationListeTypeDeMateriel("reparations");
        emprunts = deserialisationListeMaterielEmprunte("emprunts");
        statistiquesReparations = deserialisationListeTypeDeMateriel("statistiquesReparations");
        statistiquesEmprunts = deserialisationListeTypeDeMateriel("statistiquesEmprunts");
    }

    /**
     * Methode privee de deserialiser les listes du type TypeDeMateriel C'est a
     * dire qui a partir d'un fichier (ici un .dat) recupere les listes qui ont
     * ete prealablement enregistre. Ou si le fichier est vide ou non present on
     * cree un nouvelle liste. La methode est privee, car elle est utilisee que
     * dans cette classe.
     * 
     * @param nomListe
     *            Une chaine de caractere representant le nom de la liste a
     *            deserialiser.
     * @return Retourne la liste obtenue.
     */
    private HashMap<String, Materiel> deserialisationListeTypeDeMateriel(
            String nomListe) {
        try {
            File file = new File("data/" + nomListe + ".dat");
            if (!file.exists()) {
                return new HashMap<String, Materiel>();
            } else if (file.length() <= 4) // un fichier vide cree par le
            // programme fait 4 octets.
            {
                return new HashMap<String, Materiel>();
            } else {
                FileInputStream fichierData = new FileInputStream("data/"
                        + nomListe + ".dat");
                ObjectInputStream ois = new ObjectInputStream(fichierData);
                return (HashMap<String, Materiel>) ois.readObject();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<String, Materiel>();
    }

    /**
     * Methode privee de deserialiser les listes du type MaterielEmprunte C'est
     * a dire qui a partir d'un fichier (ici un .dat) recupere la liste qui a
     * ete prealablement enregistre. Ou si le fichier est vide ou non present on
     * cree un nouvelle liste. La methode est privee, car elle est utilisee que
     * dans cette classe.
     * 
     * @param nomListe
     *            Une chaine de caractere representant le nom de la liste a
     *            deserialiser.
     * @return Retourne la liste obtenue.
     */
    private HashMap<String, MaterielEmprunte> deserialisationListeMaterielEmprunte(
            String nomListe) {
        try {
            File file = new File("data/" + nomListe + ".dat");
            if (!file.exists()) {
                return new HashMap<String, MaterielEmprunte>();
            } else if (file.length() <= 4) {
                return new HashMap<String, MaterielEmprunte>();
            } else {
                FileInputStream fichierData = new FileInputStream("data/"
                        + nomListe + ".dat");
                ObjectInputStream ois = new ObjectInputStream(fichierData);
                return (HashMap<String, MaterielEmprunte>) ois.readObject();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<String, MaterielEmprunte>();
    }

    /**
     * Methode privee de serialiser les listes. C'est a dire qui enregistrer
     * dans un fichier (ici un .dat) la liste de type TyperDeMateriel passee en
     * parametre, parce que lors de la fermeture du programme il faut pouvoir
     * recuperer les informations. La methode est privee, car elle est utilisee
     * que dans cette classe.
     * 
     * @param listeASerialiser
     *            La liste a serialiser dans un fichier.
     * @param nomListe
     *            Une chaine de caractere representant le nom de la liste a
     *            serialiser.
     */
    private void serialisationListe(HashMap<String, Materiel> listeASerialiser,
            String nomListe) {
        try {
            FileOutputStream fichierListe = new FileOutputStream("data/"
                    + nomListe + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
            oos.writeObject(listeASerialiser);
            oos.flush();
            oos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode privee de serialiser les listes. C'est a dire qui enregistrer
     * dans un fichier (ici un .dat) la liste de type MaterielEmprunte passee en
     * parametre, parce que lors de la fermeture du programme il faut pouvoir
     * recuperer les informations. La methode est privee, car elle est utilisee
     * que dans cette classe.
     * 
     * @param listeASerialiser
     *            La liste a serialiser dans un fichier.
     * @param nomListe
     *            Une chaine de caractere representant le nom de la liste a
     *            serialiser.
     */
    private void serialisationListeEmprunts(
            HashMap<String, MaterielEmprunte> listeASerialiser, String nomListe) {
        try {
            FileOutputStream fichierListe = new FileOutputStream("data/"
                    + nomListe + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
            oos.writeObject(listeASerialiser);
            oos.flush();
            oos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode public permettant de recuperer le nombre d'exemplaire de ce qu'il
     * y a en stock, tous types confondus.
     * 
     * @return Entier representant le nombre de materiel en stock.
     */
    public int exemplairesEnStock() {
        int stockTotal = 0;
        for (String id : disponible.keySet()) {
            stockTotal += disponible.get(id).getNombre();
        }

        return stockTotal;
    }

    /**
     * Methode public permettant d'ajouter un nouveau materiel a la liste
     * disponible, ou s'il est deja present ajoute seulement le nombre
     * d'exemplaire au materiel existant.
     * 
     * @param name
     *            Le nom du type de materiel a ajouter.
     * @param nombre
     *            Le nombre d'exemplaire de materiel a ajouter.
     */
    public void ajouterNouveauMateriel(String name, int nombre) {

        Materiel nouveauMat = new Materiel(name, nombre);
        if (disponible.containsKey(name))
            disponible.get(name).incrNombre(nombre);
        else
            disponible.put(name, nouveauMat);
        serialisationListe(disponible, "disponible");
    }

    /**
     * Methode public permettant de retourner le type de materiel possedant le
     * plus d'exemplaires disponibles
     * 
     * @return Retourne un TypeDeMateriel correspondant au plus grand nombre
     *         d'exemplaire du type disponible.
     */
    public Materiel typeDeMaterielenPlusGrandNombre() {
        Materiel matMax = new Materiel("", -1);
        for (String id : disponible.keySet()) {
            Materiel matCourant = disponible.get(id);
            if (matMax.getNombre() < matCourant.getNombre())
                matMax = matCourant;
        }
        return matMax;
    }

    /**
     * Methode public permettant de retourner le nombre d'exemplaires
     * disponibles ayant un nom bien défini
     * 
     * @param type
     *            Le nom du type de materiel a trouver dans la liste disponible.
     * @return Le nombre d'exemplaire du type passe en parametre.
     */
    public int exemplairesEnStockType(String type) {
        int nombreType = 0;

        for (String id : disponible.keySet()) {
            if (disponible.containsKey(id))
                nombreType += disponible.get(id).getNombre();
        }
        return nombreType;
    }

    /**
     * Methode public permettant d'ajouter un nombre d'exemplaires d'un type
     * preexistant a la liste du materiel a reparer Met a jour la liste du
     * materiel disponible
     * 
     * @param type
     *            Le nom du type de materiel a reparer.
     * @param exemplairesAReparer
     *            Nombre d'exemplaires a reparer
     * @return Retourne -1 si les exemplaires ont bien ete mis en reparation,
     *         sinon retourne le nombre d'exemplaires disponibles (nombre max que
     *         l'on peut mettre en reparation) ou -2 si erreur
     */
    public int aReparer(String type, int exemplairesAReparer) {
        int nombreDisponibles = retirerExemplairesTypeDeMateriel(type,
                exemplairesAReparer, disponible);

        if (nombreDisponibles == -1) {
            ajouterExemplairesTypeDeMateriel(type, exemplairesAReparer,
                    reparations);
            ajouterExemplairesTypeDeMateriel(type, exemplairesAReparer,
                    statistiquesReparations);
        }
        serialisationListe(disponible, "disponible");
        serialisationListe(reparations, "reparations");
        serialisationListe(statistiquesReparations, "statistiquesReparations");

        return nombreDisponibles;
    }

    /**
     * Methode public permettant de retirer un nombre d'exemplaires de la liste
     * de reparation et d'ajouter a la liste disponible
     * 
     * @param type
     *            Le nom du type de materiel a rendre.
     * @param exemplairesARetirer
     *            Nombre d'exemplaire a rendre
     * @return Retourne 1 si le retour c'est bien passe, sinon retourne 0
     */
    public int retourReparation(String type, int exemplairesARetirer) {
        
        if (reparations.containsKey(type)) {
            int nombreRetour = reparations.get(type).getNombre();

            if (nombreRetour >= exemplairesARetirer) {
                if (nombreRetour == exemplairesARetirer) {
                    reparations.remove(type);
                    ajouterExemplairesTypeDeMateriel(type, nombreRetour,
                            disponible);
                }

                else if (nombreRetour > exemplairesARetirer) {
                    reparations.get(type).decrNombre(exemplairesARetirer);
                    ajouterExemplairesTypeDeMateriel(type, exemplairesARetirer,
                            disponible);
                }
                serialisationListe(reparations, "reparations");
                serialisationListe(disponible, "disponible");

                return 1;
            }

            else {
                return 0;
            }
        }

        else {
            return 0;
        }
        
    }

    /**
     * Methode privee permettant d'ajouter un nombre d'exemplaires a un type
     * dans une liste de materiel de type Materiel.
     * 
     * @param type
     *            Le nom du type de materiel.
     * @param exemplairesAAjouter
     *            Nombre d'exemplaire a ajouter
     * @param liste
     *            Liste de type TypeDeMateriel dans laquelle il faut ajouter le
     *            nombre d'exemplaire
     */
    private void ajouterExemplairesTypeDeMateriel(String type,
            int exemplairesAAjouter, HashMap<String, Materiel> liste) {

        if (liste.containsKey(type)) {
            liste.get(type).incrNombre(exemplairesAAjouter);
            return;
        }
        Materiel mtl = new Materiel(type, exemplairesAAjouter);
        liste.put(type, mtl);
    }

    /**
     * Methode privee permettant de retirer un nombre d'exemplaires d'un type de
     * materiel Retourne -1 si tous les exemplaires passes ont ete retires
     * Retourne le nombre max d'exemplaires pouvant etre retires sinon ou -2 si
     * erreur
     * 
     * @param type
     *            Le nom du type de materiel a retirer.
     * @param exemplairesARetirer
     *            Nombre d'exemplaire a retirer
     * @return Retourne -1 si les exemplaires ont bien ete retires, sinon
     *         retourne le nombre max d'exemplaires pouvant etre retires ou -2
     *         si erreur
     */
    private int retirerExemplairesTypeDeMateriel(String type,
            int exemplairesARetirer, HashMap<String, Materiel> liste) {
        if (!(liste.containsKey(type)))
            return -2;
        else {
            if (liste.get(type).getNombre()<exemplairesARetirer) 
                return liste.get(type).getNombre();
            liste.get(type).decrNombre(exemplairesARetirer);
            return -1;
        }

    }

    /**
     * Methode public permettant de creer un emprunt
     * 
     * @return Retourne -1 si l'emprunt est valide Retourne le nombre
     *         d'exemplaires pouvant être empruntes sinon ou -2 si erreur.
     */
    public int emprunter(String type, int nombreExemplaires,
            Emprunteur emprunteur, Date date, int duree) {
        int nombreDisponibles = retirerExemplairesTypeDeMateriel(type,
                nombreExemplaires, disponible);

        if (nombreDisponibles == -1) {
            ajouterExemplairesTypeDeMateriel(type, nombreExemplaires,
                    statistiquesEmprunts);
            MaterielEmprunte nouvelEmprunt = new MaterielEmprunte(new Materiel(
                    type, nombreExemplaires), emprunteur, date, duree);
            if (verifierValidite(nouvelEmprunt)) {
                emprunts.put(nouvelEmprunt.getId(), nouvelEmprunt);
            }
        }
        serialisationListe(disponible, "disponible");
        serialisationListe(statistiquesEmprunts, "statistiquesEmprunts");
        serialisationListeEmprunts(emprunts, "emprunts");

        return nombreDisponibles;
    }

    
    private boolean verifierValidite(MaterielEmprunte emprunt) {
        return true;
    }
    
    /**
     * Methode public permettant de rendre un materiel donc retirer de la liste
     * de reparations et mettre dans disponible
     * 
     * @return Retourne true si le rendu est valide, sinon false
     */
    public boolean rendre(String type, int nombreExemplaires,
            Personne emprunteur) {
        for (String id : emprunts.keySet()) {
            if (emprunts.get(id).getMatEmprunt().equals(type) 
                    && emprunts.get(id).getMatEmprunt().getNombre()>=nombreExemplaires) {
                int nombreRetour = emprunts.get(id).getMatEmprunt().getNombre();
    
                if (nombreRetour == nombreExemplaires) {
                    emprunts.remove(id);
                    ajouterExemplairesTypeDeMateriel(type, nombreRetour, disponible);
                }
    
                else if (nombreRetour > nombreExemplaires) {
                    emprunts.get(id).getMatEmprunt()
                            .decrNombre(nombreExemplaires);
                    ajouterExemplairesTypeDeMateriel(type, nombreExemplaires,
                            disponible);
                }
                serialisationListeEmprunts(emprunts, "emprunts");
                serialisationListe(disponible, "disponible");
    
                return true;
            }
        }
        return false;
    }

    /**
     * Methode privee permettant de chercher l'index dans la liste de
     * MaterielEmprunte correspondant au type passe en parametre
     * 
     * @return Retourne l'index si le type existe Retourne -1 sinon
     */
    private int rechercheIndexMaterielEmprunte(String type,
            int nombreExemplaires, Personne emprunteur) {
        boolean trouve = false;
        int index = 0;
        int i = 0;

        while (trouve == false && i < emprunts.size()) {
            if (emprunts.get(i).getEmprunteur().equals(emprunteur)
                    && emprunts.get(i).getMatEmprunt().getNom().toUpperCase()
                            .equals(type.toUpperCase())
                    && emprunts.get(i).getMatEmprunt().getNombre() >= nombreExemplaires) {
                trouve = true;
                index = i;
            }

            i++;
        }

        if (trouve) {
            return index;
        }

        else {
            return -1;
        }
    }

    /**
     * Methode publique permettant de trouver le materiel le plus emprunte.
     * 
     * @return Retourne le TypeDeMateriel le plus emprunte sinon retourne null.
     */
    public Materiel materielPlusEmprunte() {
        int index = 0;

        if (statistiquesEmprunts.size() > 0) {
            for (int i = 0; i < statistiquesEmprunts.size() - 1; i++) {
                if (statistiquesEmprunts.get(index).getNombre() < statistiquesEmprunts
                        .get(i + 1).getNombre()) {
                    index = i + 1;
                }

                else {
                    index = 0;
                }
            }
            return statistiquesEmprunts.get(index);
        }

        else {
            return null;
        }
    }

    /**
     * Methode publique permettant de trouver le materiel le plus repare.
     * 
     * @return Retourne le TypeDeMateriel le plus repare sinon retourne null
     */
    public Materiel materielPlusRepare() {
        int index = 0;

        if (statistiquesReparations.size() > 0) {
            for (int i = 0; i < statistiquesReparations.size() - 1; i++) {
                if (statistiquesReparations.get(index).getNombre() < statistiquesReparations
                        .get(i + 1).getNombre()) {
                    index = i + 1;
                }

                else {
                    index = 0;
                }
            }
            return statistiquesReparations.get(index);
        }

        else {
            return null;
        }
    }

    /**
     * Methode publique permettant de retirer un materiel (un type) de la liste
     * disponible.
     * 
     * @param type
     *            Le type de materiel a retirer de la liste
     * @return Retourne 1 si le type a bien ete retirer, sinon retourne 0.
     */
    public int retirerMaterielDisponible(String type) {

        if (disponible.containsKey(type)) {
            disponible.remove(type);
            serialisationListe(disponible, "disponible");
            return 1;
        }

        else {
            return 0;
        }
    }

    /**
     * Methode publique permettant de retirer un materiel (un type) de la liste
     * reparations.
     * 
     * @param type
     *            Le type de materiel a retirer de la liste
     * @return Retourne 1 si le type a bien ete retirer, sinon retourne 0.
     */
    public int retirerMaterielReparation(String type) {

        if (reparations.containsKey(type)) {
            reparations.remove(type);
            serialisationListe(reparations, "reparations");
            return 1;
        }

        else {
            return 0;
        }
    }

    /**
     * Methode publique permettant de faire un affichage par defaut de la
     * classe, ici c'est un message d'erreur car il n'est pas possible
     * d'afficher stock
     * 
     * @return La chaine de caractere contenant le message qu'il faut afficher
     *         lors d'une demande d'affichage de stock.
     */
    public String toString() {
        return "Affichage impossible : utilisez l'affichage spécifique (Matériel disponible, Matériel en réparation, Emprunts en cours";
    }

    /**
     * Methode publique permettant de faire un affichage de la liste disponible.
     * 
     * @return La chaine de caractere contenant le contenu de la liste
     *         disponible
     */
    public String afficherStock() {
        String retour = "\n     STOCK DISPONIBLE\n";

        for (int i = 0; i < disponible.size(); i++) {
            retour += disponible.get(i) + "\n";
        }

        return retour;
    }

    /**
     * Methode publique permettant de faire un affichage de la liste
     * reparations.
     * 
     * @return La chaine de caractere contenant le contenu de la liste
     *         reparations
     */
    public String afficherReparations() {
        String retour = "\n     STOCK REPARATION\n";

        for (int i = 0; i < reparations.size(); i++) {
            retour += reparations.get(i);
        }

        return retour;
    }

    /**
     * Methode publique permettant de faire un affichage de la liste emprunts.
     * 
     * @return La chaine de caractere contenant le contenu de la liste emprunts
     */
    public String afficherEmprunts() {
        String retour = "\n     EMPRUNTS EN COURS\n";

        for (int i = 0; i < emprunts.size(); i++) {
            retour += emprunts.get(i);
        }

        return retour;
    }

    /**
     * Methode publique utilisee pour acceder a la liste diponible a partir
     * d'une autre classe.
     * 
     * @return Retourne la liste de type TypeDeMateriel disponible
     */
    public HashMap<String, Materiel> getListeDisponible() {
        return disponible;
    }

    /**
     * Methode publique utilisee pour acceder a la liste reparations a partir
     * d'une autre classe.
     * 
     * @return Retourne la liste de type TypeDeMateriel reparations
     */
    public HashMap<String, Materiel> getListeReparations() {
        return reparations;
    }

    /**
     * Methode publique utilisee pour acceder a la liste emprunts a partir d'une
     * autre classe.
     * 
     * @return Retourne la liste de type MaterielEmprunte emprunts
     */
    public HashMap<String, MaterielEmprunte> getListeEmprunts() {
        return emprunts;
    }
}
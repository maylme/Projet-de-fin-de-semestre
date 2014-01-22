package Materiel;

import java.util.ArrayList;
import Outils.*;
import java.util.Date;
import java.util.HashMap;
import java.io.*;

import utilisateurs.Emprunteur;
import utilisateurs.Personne;

/**
 * La classe Stock est une representation par listes de type ArrayList d'un stock
 * de materiel ainsi que d'un referencement des emprunts et reservations.
 * 
 * @author RABEHASY Riana, TUAL Sonia, MESNIER Maylanie
 * @version 2.1 (4.Dec.2013)
 */

public class Stock {

    private ArrayList<Materiel> stockTotal;
    private ArrayList<Materiel> reparations;
    private ArrayList<MaterielEmprunte> empruntsEtReservs;
    FichierData f;

    /*
     * private ArrayList<Materiel> statistiquesReparations; private
     * ArrayList<Materiel> statistiquesEmprunts;
     */

    /**
     * Constructeur de la classe Stock Il construit le stock avec les 5 listes
     * (disponible, reparations, emprunts, statistiquesReparations,
     * statistiquesEmprunts).
     */
    public Stock() {
        f = new FichierData();
        stockTotal = f.deserialisationListeMateriel("stockTotal");
        reparations = f.deserialisationListeMateriel("reparations");
        empruntsEtReservs = f.deserialisationListeMaterielEmprunte("empruntsEtReservs");
        /*statistiquesReparations =
                deserialisationListeTypeDeMateriel("statistiquesReparations");
        statistiquesEmprunts =
                deserialisationListeTypeDeMateriel("statistiquesEmprunts");
                */

    }
    
    /**
     * Methode permettant d'obtenir l'emplacement d'un certain type de materiel
     * dans une liste de materiel.
     * 
     * @param mat
     *          Le matériel recherché
     * @param liste
     *          La liste a parcourir
     * @return
     *          Un entier correspondant a l'index de l'element dans la liste, ou -1 si le materiel n'est
     *          pas present
     */
    public int rechercheIndexMateriel(Materiel mat, ArrayList<Materiel> liste) {
        for (int i=0; i<liste.size(); ++i) {
            if (liste.get(i).equals((mat))) return i;
        }
        return -1;
    }
    
    /**
     * Methode permettant d'obtenir l'emplacement dans une liste d'emprunts d'un 
     * certain type d'emprunts. 
     * 
     * @param matEmp 
     *          le type d'emprunt recherche
     * @param liste 
     *          la liste a parcourir
     * @return
     *          Un entier correspondant a l'index de l'element dans la liste, ou
     *          -1 s'il n'est pas present.
     */         
    public int rechercheIndexMaterielEmprunte(String idEmprunt, ArrayList<MaterielEmprunte> liste) {
        for (int i=0; i<liste.size(); ++i) {
            if (idEmprunt.equals(liste.get(i).getId())) return i;
        }
        return -1;
    }

    /**
     * Methode qui ajoute intelligement du materiel dans une liste de materiel donnee, 
     * 
     * @param mat
     *          le type de materiel a ajouter
     * @param liste
     *          la liste a modifier
     */
    public void ajouterMateriel(Materiel mat, ArrayList<Materiel> liste) {
        int index=rechercheIndexMateriel(mat, liste);
        if (index>=0) liste.get(index).incrNombre(mat.getNombre());
        else liste.add(mat);
    }
    /**
     * Methode permettant de retirer un certain nombre d'exemplaires de materiel
     * d'une liste de materiel, et d'enlever de materiel de la liste si le nombre
     * d'exemplaires est 0
     * 
     * @param mat
     *          Le type de materiel a enlever
     * @param liste
     *          La liste a manipuler
     */
    public void retirerMateriel(Materiel mat, ArrayList<Materiel> liste) {
        int index=rechercheIndexMateriel(mat, liste);
        if (index>=0) {
            liste.get(index).decrNombre(mat.getNombre());
            if (liste.get(index).getNombre() <= 0) {
                liste.remove(index);
            }
        }
    }
    
    /**
     * Methode qui enleve du materiel du stock total pour l'ajouter a la liste
     * des reparations
     * 
     * @param aReparer
     */
    public void aReparer(Materiel aReparer) {
        retirerMateriel(aReparer, stockTotal);
        ajouterMateriel(aReparer, reparations);
        f.serialisationListeMateriel(stockTotal, "stockTotal");
        f.serialisationListeMateriel(reparations, "reparations");
    }
    
    private boolean conflitDates(Date dateDebut, Date dateFin, MaterielEmprunte emprunt) {
        Date dateDebutEmprunt = emprunt.getDateEmprunt();
        Date dateFinEmprunt = emprunt.getDateFin();
        return (((dateDebutEmprunt.compareTo(dateDebut) <= 0 && dateFinEmprunt
                            .compareTo(dateDebut) > 0)
                            || (dateDebutEmprunt.compareTo(dateFin) < 0 && dateFinEmprunt.compareTo(dateFin) >= 0) || (dateDebutEmprunt
                            .compareTo(dateDebut) > 0 && dateFinEmprunt
                            .compareTo(dateFin) < 0)));
                
    }
    
    /**
     * Methode qui retourne la liste de tous les materiels qui sont disponibles
     * en fonction des caracterstiques recherchees et des dates.
     * 
     * @param debut
     * @param fin
     * @param caracs
     * @return
     */
    
    public ArrayList<Caracteristiques> materielDispo(Date debut, Date fin,
            Caracteristiques caracs) {
        ArrayList<Caracteristiques> listeCaracs = new ArrayList<Caracteristiques>();
        int nombreMaterielDispo;
        for (Materiel mat : stockTotal) {
            nombreMaterielDispo=mat.getNombre();
            for (MaterielEmprunte matEmprunt : empruntsEtReservs) {
                if (mat.equals(matEmprunt.getMatEmprunt()) && (!(conflitDates(debut, fin, matEmprunt)))) {
                    nombreMaterielDispo-=matEmprunt.getMatEmprunt().getNombre();
                }
            }
            if (nombreMaterielDispo > 0) listeCaracs.add(mat.getCaracteristiques());
        }
        return listeCaracs;
    }

    /*
     * hash
     * personneCherchee
     * for (Personne p : hashmap.keySet()) {
     *          if personneCherchee.equals(p) 
     *                  
     * }
     *
     */
    
    
    /**
     * Methode qui gere le retour de materiel emprunte : un certain nombre de materiel
     * est rendu et la liste des reparations est mise a jour si il y a du materiel
     * defectueux.
     * Si il n'y a plus de materiel associe a l'emprunt, ce dernier est supprime de
     * la liste d'emprunts
     * 
     * @param rendu
     *          Le materiel rendu
     * @param HS
     *          Le nombre de materiel HS parmi le materiel rendu
     */
    public void renduEmprunt(String idEmprunt,int nombreRendus, int HS) {
        int indexEmprunt = rechercheIndexMaterielEmprunte(idEmprunt, empruntsEtReservs);
        Materiel mat = (Materiel) empruntsEtReservs.get(indexEmprunt).getMatEmprunt().clone();
        int indexRep = rechercheIndexMateriel(rendu.getMatEmprunt(), reparations);
        if (indexEmprunt>=0) {
            
        }
    }
    
    /**
     * Methode public permettant de retourner le type de materiel possedant le
     * plus d'exemplaires disponibles
     * 
     * @return Retourne un TypeDeMateriel correspondant au plus grand nombre
     *         d'exemplaire du type disponible.
     */
    /*
    public Materiel typeDeMaterielenPlusGrandNombre() {
        Materiel matMax = new Materiel("", -1);
        for (String id : disponible.keySet()) {
            Materiel matCourant = disponible.get(id);
            if (matMax.getNombre() < matCourant.getNombre())
                matMax = matCourant;
        }
        return matMax;
    }*/

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
            int exemplairesAAjouter, ArrayList<Materiel> liste) {

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
    private int retirerExemplairesTypeDeMateriel(Materiel aReparer) {
        
        if (li)
        /*
        if (!(liste.containsKey(type)))
            return -2;
        else {
            if (liste.get(type).getNombre() < exemplairesARetirer)
                return liste.get(type).getNombre();
            liste.get(type).decrNombre(exemplairesARetirer);
            return -1;
        }
        */

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
        /*
         * materiel existant et dispo qté dans le stock
         */
    }

    /**
     * Methode public permettant de rendre un materiel donc retirer de la liste
     * d'emprunts et mettre dans disponible
     * 
     * @return Retourne true si le rendu est valide, sinon false
     */
    public boolean rendre(String type, int nombreExemplaires,
            Personne emprunteur) {
        for (String id : emprunts.keySet()) {
            if (emprunts.get(id).getMatEmprunt().equals(type)
                    && emprunts.get(id).getMatEmprunt().getNombre() >= nombreExemplaires) {
                int nombreRetour = emprunts.get(id).getMatEmprunt().getNombre();

                if (nombreRetour == nombreExemplaires) {
                    emprunts.remove(id);
                    ajouterExemplairesTypeDeMateriel(type, nombreRetour,
                            disponible);
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
    public ArrayList<Materiel> getListeDisponible() {
        return disponible;
    }

    /**
     * Methode publique utilisee pour acceder a la liste reparations a partir
     * d'une autre classe.
     * 
     * @return Retourne la liste de type TypeDeMateriel reparations
     */
    public ArrayList<Materiel> getListeReparations() {
        return reparations;
    }

    /**
     * Methode publique utilisee pour acceder a la liste emprunts a partir d'une
     * autre classe.
     * 
     * @return Retourne la liste de type MaterielEmprunte emprunts
     */
    public ArrayList<MaterielEmprunte> getListeEmprunts() {
        return emprunts;
    }
}
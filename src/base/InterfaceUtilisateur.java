package base;

import java.io.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import Materiel.Materiel;
import Materiel.MaterielEmprunte;
import Outils.FichierData;

/**
 * Cette classe gere l'interface utilisateur du programme. Elle permet a
 * l'utilisateur de se connecter puis d'effectuer un ensemble d'actions sur le
 * stock en fonction de son statut (emprunteur ou gestionnaire).
 * 
 * @author Vincent Montalieu
 * @version 1.2 (5.Dec.2013)
 */

public class InterfaceUtilisateur {
    private Console console;
    private Gestion gestion;

    /**
     * Constructeur qui contient l'architecture de l'interface utilisateur.
     * Toutes les fonctions constituant toutes les actions possibles sont
     * appelees ici. Le constructeur permet de definir un utilisateur puis de le
     * faire naviguer a travers les differents menus. Il gere les retours aux
     * menus precedents et principal.
     */
    public InterfaceUtilisateur() {
        console = System.console();
        String menu = "Y";
        boolean premierPassage = false;
        boolean mdpCorrect;

        do {
            System.out
                    .println("\n          --------------------------------------\n          | Bienvenue dans IHM STOCK MANAGER ! |\n          --------------------------------------\n\n\nQue souhaitez-vous faire ?\n\n1. Accès emprunt\n2. Accès gestion\n");
            boolean statut = choixUtilisateur();
            String nom = nom();
            String prenom = prenom();

            if (!premierPassage) {
                gestion = new Gestion();
            }

            if (gestion.existe(nom, prenom, statut)) {
                mdpCorrect = demandeMotDePasse(statut, false);
            } else {
                if (statut) {
                    System.out
                            .printf("\nPour créer un compte gestionnaire, il faut le mot de passe administratif.");
                    if (demandeMotDePasse(statut, true)) {
                        mdpCorrect = true;
                        gestion.createNewGestionnaire(nom, prenom,
                                newMotDePasse());
                    } else
                        mdpCorrect = false;
                } else {
                    System.out
                            .println("\n Vous êtes un :\n\n1. Elève\n2. Professeur\n");
                    boolean prof = choixUtilisateur();
                    if (prof) {
                        gestion.createNewProf(nom, prenom, newMotDePasse());
                    } else {
                        gestion.createNewEleve(nom, prenom, newMotDePasse());
                    }
                    mdpCorrect = true;
                }
            }

            if (mdpCorrect) {
                // partie gestion
                if (statut) {
                    do {
                        String var = menuGestion();

                        switch (var) {
                        case "1": {
                            ajouterStock();
                            break;
                        }

                        case "2": {
                            supprimerStock();
                            break;
                        }

                        case "3": {
                            supprimerReparations();
                            break;
                        }

                        case "4": {
                            terminerReparations();
                            break;
                        }

                        case "5": {
                            modifEmpruntOuResa();
                            break;
                        }

                        case "6": {
                            afficher();
                            break;
                        }

                        case "7": {
                            FichierData f = new FichierData();
                            f.serialisationFichierLisible(
                                    gestion.getStockTotal(),
                                    gestion.getListeReparation(),
                                    gestion.getListeResa());
                            break;
                        }

                        default: {
                            break;
                        }
                        }
                    } while (menu.equals(retourMenuGestion()));
                }

                // partie emprunt
                else {
                    do {
                        String var = menuEmprunt();

                        switch (var) {
                        case "1": {
                            emprunter();
                            break;
                        }

                        case "2": {
                            rendre();
                            break;
                        }

                        default: {
                            break;
                        }
                        }
                    } while (menu.equals(retourMenuEmprunt()));
                }
                premierPassage = true;
            }
        } while (menu.equals(retourMenuPrincipal()));
    }

    /**
     * Permet a l'utilisateur choisir son statut entre celui d'emprunteur et
     * celui de gestionnaire. S'il choisit gestionnaire, il devra renseigner un
     * mot de passe. Apres 3 essais, il sera automatiquement considere comme un
     * emprunteur.
     * 
     * @return Un booleen representant le statut : false pour un emprunteur et
     *         true pour un gestionnaire.
     */
    private boolean choixUtilisateur() {
        boolean wrong = false;
        String choix = "";

        do {
            choix = console.readLine();

            if (choix.equals("1")) {
                wrong = true;
                return false;
            }

            else if (choix.equals("2")) {
                wrong = true;
                return true;
            }

            else {
                System.out.printf("\nMerci de choisir entre 1 et 2 : ");
            }
        } while (!wrong);
        return false;
    }

    /**
     * Permet de demander un mot de passe et de vérifier s'il est le même que
     * celui passer en parametre. Il y a trois essais.
     * 
     * @param gestionnaire
     *            booleen permettant de savoir si l'utilisateur courant est
     *            gestionnaire
     * @param etapeCreation
     *            booleen permettant de savoir si on est a la creation d'un
     *            gestionnaire ou a son mdp personnel
     * @return Un booleen pour savoir si le mot de passe est le bon
     */
    private boolean demandeMotDePasse(boolean gestionnaire,
            boolean etapeCreation) {
        int essais = 3;

        do {
            System.out.printf("\nSaisissez le mot de passe : ");
            String password = new String(console.readPassword());

            if (gestion.password(password, gestionnaire, etapeCreation)) {
                System.out.println("\nMot de passe correct !\n");// Redirection
                                                                 // vers le mode
                                                                 // Gestion
                return true;
            }

            else {
                essais--;
                System.out
                        .println("\nMot de passe erroné !\nEssai(s) restant(s) : "
                                + essais);
            }
        } while (essais > 0);
        return false;
    }

    /**
     * Permet a l'utilisateur de saisir son nom.
     * 
     * @return Une chaine de caracteres representant le nom saisi.
     */
    private String nom() {
        System.out.printf("\nEntrez votre nom : ");
        String retour = console.readLine();
        return retour;
    }

    /**
     * Permet a l'utilisateur de saisir son prenom.
     * 
     * @return Une chaine de caracteres representant le prenom saisi.
     */
    private String prenom() {
        System.out.printf("Entrez votre prénom : ");
        String retour = console.readLine();
        return retour;
    }

    /**
     * Permet a l'utilisateur de saisir un mot de passe.
     * 
     * @return Une chaine de caracteres representant le mot de passe saisi.
     */
    private String newMotDePasse() {
        System.out.printf("\nEntrez un mot de passe : ");
        String retour = console.readLine();
        return retour;
    }

    /**
     * Affiche le menu principal du mode emprunt.
     * 
     * @return Une chaine de caracteres representant le choix fait par
     *         l'utilisateur.
     */
    private String menuEmprunt() {
        System.out
                .println("\n\n          Menu EMPRUNT\n\nQue voulez-vous faire ?\n\n1. Emprunter du matériel\n2. Rendre du matériel\n");
        String retour = console.readLine();
        return retour;
    }

    /**
     * Affiche le menu principal du mode gestion.
     * 
     * @return Une chaine de caracteres representant le choix fait par
     *         l'utilisateur.
     */
    private String menuGestion() {
        System.out
                .println("\n          Menu GESTION\n\nQue voulez-vous faire ?\n\n1. Ajouter du matériel au stock\n2. Supprimer du matériel du stock total\n3. Supprimer du matériel en réparation\n4. Terminer une réparation de matériel\n5. Modifier un emprunt ou une réservation\n6. Affichage des données\n7. Export de toutes les données");
        String retour = console.readLine();
        return retour;
    }

    /**
     * Permet a l'utilisateur d'emprunter un materiel en saisissant le type de
     * materiel souhaite, le nombre d'exemplaires, la duree et la date du jour.
     * Affiche un message confirmant l'emprunt ou expliquant le probleme si
     * l'emprunt n'est pas possible.
     */
    private void emprunter() {
        System.out
                .printf("\nQue voulez-vous emprunter (entrer un mot clé) ? : ");
        String type = console.readLine();
        System.out
                .printf("\nA quelle date voulez vous l'emprunter ? (format JJ/MM/AAAA) : ");
        String dateDEmprunt = console.readLine();
        System.out
                .printf("\nA quelle date voulez vous le rendre ? (format JJ/MM/AAAA) : ");
        String dateRetour = console.readLine();
        System.out.printf("\nQue voulez-vous emprunter ? : ");
        ArrayList<Materiel> matEmpruntable = gestion.listeMaterielEmpruntable(
                type, formatDate(dateDEmprunt), formatDate(dateRetour));
        if (!matEmpruntable.equals(null)) {
            System.out.printf(afficherEmpruntable(matEmpruntable));
            Materiel matChoisi = matEmpruntable
                    .get(choixDansListe(matEmpruntable.size()));
            System.out.printf("Combien d'exemplaires désirez-vous ? : ");
            String test = console.readLine();
            int nombre = 0;

            if (intTest(test))
                nombre = Integer.parseInt(test);

            if (nombre > 0)
                System.out.println(gestion.emprunt(matChoisi, nombre,
                        formatDate(dateDEmprunt), formatDate(dateRetour)));
        } else
            System.out.println("\nAucun materiel de ce type.\n");
    }

    /**
     * Methode publique permettant de faire un affichage de la liste des
     * emprunts de la personne.
     * 
     * @return La chaine de caractere contenant le contenu de la liste d'emprunt
     *         de la personne
     */
    private String afficherEmpruntable(ArrayList<Materiel> matEmpruntable) {
        String retour = "\n     MATERIELS DISPONIBLES\n";

        for (int i = 0; i < matEmpruntable.size(); i++) {
            retour += i + ". " + matEmpruntable.get(i) + "\n";
        }
        return retour;
    }

    /**
     * Methode publique permettant de faire un affichage de la liste des
     * emprunts de la personne.
     * 
     * @return La chaine de caractere contenant le contenu de la liste d'emprunt
     *         de la personne
     */
    private String afficherEmpruntUtilisateur(
            ArrayList<MaterielEmprunte> matEmprunt) {
        String retour = "\n     EMPRUNTS\n";

        for (int i = 0; i < matEmprunt.size(); i++) {
            retour += i + ". " + matEmprunt.get(i) + "\n";
        }
        return retour;
    }

    /**
     * Permet a l'utilisateur choisir un élément de la liste qui lui est
     * presente.
     * 
     * @param tailleDeLaListe
     *            entier représentant la taille de la liste
     * @return int representant le choix (position) dans la liste presentee
     */
    private int choixDansListe(int tailleDeLaListe) {
        boolean wrong = false;
        String choix = "";

        do {
            choix = console.readLine();

            if (intTest(choix)) {
                if (Integer.parseInt(choix) >= 0
                        && Integer.parseInt(choix) < tailleDeLaListe) {
                    wrong = true;
                    return Integer.parseInt(choix);
                }
            } else {
                System.out.printf("\nMerci de choisir entre 0 et "
                        + (tailleDeLaListe - 1) + " : ");
            }
        } while (!wrong);
        return -1;
    }

    /**
     * Permet a l'utilisateur de rendre un materiel en saisissant le type de
     * materiel souhaite et le nombre d'exemplaires a rendre. Affiche un message
     * confirmant ou infirmant le retour.
     */
    private void rendre() {

        System.out.println(afficherEmpruntUtilisateur(gestion
                .listeEmpruntParEmprunteur()));

        System.out.printf("\nQue voulez-vous rendre ? : ");
        MaterielEmprunte matARendre = gestion.listeEmpruntParEmprunteur().get(
                choixDansListe(gestion.listeEmpruntParEmprunteur().size()));
        System.out.printf("Combien d'exemplaires désirez-vous rendre ? : ");
        String test = console.readLine();
        int nombre = 0;

        if (intTest(test))
            nombre = Integer.parseInt(test);

        System.out
                .printf("Combien d'exemplaires rendus sont hors service ? : ");
        String nbHS = console.readLine();
        int nombreHS = 0;

        if (intTest(nbHS))
            nombreHS = Integer.parseInt(test);

        boolean retour = gestion.rendre(matARendre, nombre, nombreHS);

        if (retour) {
            System.out.println("\nRetour confirmé !");
        }

        else {
            System.out.println("\nRetour impossible !");
        }
    }

    /**
     * Permet de répérer une date dans le format Date à partir d'un String
     * 
     * @param date
     *            Chaine de caractere contenant la date JJ/MM/AAAA
     * @return Date retourne la date dans le bon format
     */
    private Date formatDate(String date) {
        String[] elements = date.split("/");
        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(elements[2]), Integer.parseInt(elements[1]),
                Integer.parseInt(elements[0]));
        return c.getTime();
    }

    /**
     * Methode publique permettant de faire un affichage de la liste du stock
     * total.
     * 
     * @return La chaine de caractere contenant le contenu de la liste d'emprunt
     *         de la personne
     */
    private String afficherChoixStockTotal(ArrayList<Materiel> stock) {
        String retour = "\n     STOCK TOTAL\n";

        for (int i = 0; i < stock.size(); i++) {
            retour += i + ". " + stock.get(i) + "\n";
        }
        return retour;
    }

    /**
     * Permet a l'utilisateur d'ajouter un materiel au stock en saisissant le
     * type de materiel et le nombre d'exemplaires a ajouter
     */
    private void ajouterStock() {
        System.out.println(gestion.afficherStockTotal());
        System.out
                .printf("\nVoulez juste ajouter un nombre d'exemplaire à un materiel existant ? (Y/N): ");
        if (console.readLine().equals("Y")) {
            System.out
                    .printf("\nVeuillez choisir le materiel parmis la liste suivante\n");
            System.out
                    .println(afficherChoixStockTotal(gestion.getStockTotal()));
            Materiel matChoisi = gestion.getStockTotal().get(
                    choixDansListe(gestion.getStockTotal().size()));
            System.out.printf("Combien d'exemplaires voulez-vous ajouter ? : ");
            String nbAAjouter = console.readLine();
            int nombre = 0;

            if (intTest(nbAAjouter))
                nombre = Integer.parseInt(nbAAjouter);
            if (nombre != 0)
                gestion.ajouterExemplaire(matChoisi, nombre);
        } else {
            System.out
                    .printf("\nPour ajouter un nouveau materiel, il faut entrer toutes ses caractéristiques.\nVoici la liste des caractéristiques existante : \n");
            System.out.println(gestion.getCleMat());

            HashMap<String, String> caracDuNewMat = new HashMap<String, String>();
            do {
                System.out
                        .printf("\nQuelle caractéristique voulez vous compléter ?\n");
                String cleCarac = console.readLine();
                if (!gestion.existeCleCaracteristique(cleCarac)) {
                    System.out
                            .printf("\nVoulez vous créez cette nouvelle caractéristique "
                                    + cleCarac + " ? (Y/N)\n");
                    if (console.readLine().equals("Y")) {
                        gestion.creationCleCaracteristique(cleCarac);
                        System.out
                                .printf("\nQuelle valeur voulez vous mettre à cette caractéristique ?\n");
                        String valueCarac = console.readLine();
                        caracDuNewMat.put(cleCarac, valueCarac);
                    }
                } else {
                    System.out
                            .printf("\nQuelle valeur voulez vous mettre à cette caractéristique ?\n");
                    String valueCarac = console.readLine();
                    caracDuNewMat.put(cleCarac, valueCarac);
                }

                System.out
                        .printf("\nVoulez vous ajoutez une autre caractéristique ?(Y/N)\n");
            } while (console.readLine().equals("Y"));

            System.out.printf("Combien d'exemplaires voulez-vous ajouter ? : ");
            String nbExemp = console.readLine();
            int nombre = 0;

            if (intTest(nbExemp))
                nombre = Integer.parseInt(nbExemp);

            int duree = 0;
            System.out
                    .printf("\nVoulez vous ajoutez une durée max d'emprunt de ce materiel ?(Y/N)\n");
            if (console.readLine().equals("Y")) {
                System.out
                        .printf("Quelle est la durée max d'emprunt de ce matériel ? : ");
                String dureeMax = console.readLine();

                if (intTest(dureeMax))
                    duree = Integer.parseInt(dureeMax);
            }

            gestion.ajoutMaterielStock(caracDuNewMat, nombre, duree);
        }
    }

    /**
     * Permet a l'utilisateur de supprimer un type du stock en saisissant le nom
     * du type.
     */
    private void supprimerStock() {
        System.out.println(afficherChoixStockTotal(gestion.getStockTotal()));

        System.out.printf("\nQuel materiel voulez-vous supprimer ? : ");
        Materiel matChoisi = gestion.getStockTotal().get(
                choixDansListe(gestion.getStockTotal().size()));

        System.out.printf("Combien d'exemplaires voulez-vous supprimer ? : ");
        String nbASupprimer = console.readLine();
        int nombre = 0;

        if (intTest(nbASupprimer))
            nombre = Integer.parseInt(nbASupprimer);
        if (nombre != 0) {
            if (gestion.retirerMaterielStock(matChoisi, nombre)) {
                System.out.println("\nSuppression confirmée !");
            }

            else {
                System.out.println("\nSuppression impossible !");
            }
        }
    }

    /**
     * Methode publique permettant de faire un affichage de la liste de
     * reparation.
     * 
     * @return La chaine de caractere contenant le contenu de la liste de
     *         réparation
     */
    private String afficherChoixReparation(ArrayList<Materiel> reparation) {
        String retour = "\n     MATERIEL EN REPARATION\n";

        for (int i = 0; i < reparation.size(); i++) {
            retour += i + ". " + reparation.get(i) + "\n";
        }
        return retour;
    }

    /**
     * Permet au gestionnaire de supprimer un materiel de la liste de réparation
     * si celui-ci n'est pas réparable
     */
    private void supprimerReparations() {
        System.out
                .println(afficherChoixReparation(gestion.getListeReparation()));

        System.out
                .printf("\nQuel materiel voulez-vous supprimer des réparations ? : ");
        Materiel matChoisi = gestion.getListeReparation().get(
                choixDansListe(gestion.getListeReparation().size()));
        if (!matChoisi.equals(null)) {
            System.out
                    .println("\nCombien d'exemplaires voulez-vous supprimer ? : ");
            String supp = console.readLine();
            int nombre = 0;

            if (intTest(supp))
                nombre = Integer.parseInt(supp);
            if (nombre != 0) {
                if (gestion.supprimerReparation(matChoisi, nombre)) {
                    System.out
                            .println("\nSuppression du materiel en réparation confirmée !");
                } else {
                    System.out.println("\nImpossible de supprimer le materiel");
                }
            }

        }
    }

    /**
     * Permet a l'utilisateur de retirer un materiel de la liste des réparations
     * pour le remettre dans stock. Affiche un message confirmant ou infirmant
     * le retrait.
     */
    private void terminerReparations() {
        System.out
                .printf("\nQuel materiel voulez-vous retirer des réparations pour les remettre dans stock? : ");
        Materiel matChoisi = gestion.getListeReparation().get(
                choixDansListe(gestion.getListeReparation().size()));
        if (!matChoisi.equals(null)) {
            System.out
                    .println("\nCombien d'exemplaires voulez-vous retirer ? : ");
            String retirer = console.readLine();
            int nombre = 0;

            if (intTest(retirer))
                nombre = Integer.parseInt(retirer);
            if (nombre != 0) {
                if (gestion.terminerReparation(matChoisi, nombre)) {
                    System.out.println("\nRetrait de réparation confirmée !");
                } else {
                    System.out.println("\nRetrait de réparation impossible !");
                }
            }
        }
    }

    /**
     * Permet au gestionnaire de modifier un emprunt ou une réservation Affiche
     * un message confirmant ou infirmant le retrait.
     */
    private void modifEmpruntOuResa() {
        System.out.println(afficherChoixEmpruntEtResa(gestion.getListeResa()));

        System.out
                .printf("\nQuel emprunt ou reservation voulez vous modifier ? : ");
        MaterielEmprunte matChoisi = gestion.getListeResa().get(
                choixDansListe(gestion.getListeResa().size()));
        if (!matChoisi.equals(null)) {
            System.out
                    .printf("\nQuelle modification voulez vous effectuer ?\n\n1. La date de début d'emprunt/réservation \n2. La date de fin d'emprunt/réservation \n3. Le nombre d'exemplaire \n4. Supprimer l'emprunt/réservation \n");
            boolean wrong = false;
            String choix = "";

            do {
                choix = console.readLine();

                if (choix.equals("1")) {
                    wrong = true;
                    System.out
                            .printf("\nQuelle est la nouvelle date d'emprunt ? (JJ/MM/AAAA)");
                    String date = console.readLine();
                    gestion.modifDateDebut(matChoisi, formatDate(date));
                }

                else if (choix.equals("2")) {
                    wrong = true;
                    System.out
                            .printf("\nQuelle est la nouvelle date de fin d'emprunt ? (JJ/MM/AAAA)");
                    String date = console.readLine();
                    gestion.modifDateFin(matChoisi, formatDate(date));
                }

                else if (choix.equals("3")) {
                    wrong = true;
                    System.out
                            .printf("\nQuelle est le nouveau nombre d'exemplaire emprunte ? ");
                    String nbExemplaire = console.readLine();
                    int nombre = 0;

                    if (intTest(nbExemplaire))
                        nombre = Integer.parseInt(nbExemplaire);
                    if (nombre >= 0)
                        gestion.modifNombreExemplaire(matChoisi, nombre);

                } else if (choix.equals("4")) {
                    wrong = true;
                    gestion.modifNombreExemplaire(matChoisi, matChoisi
                            .getMatEmprunt().getNombre());
                }

                else {
                    System.out
                            .printf("\nMerci de choisir entre 1, 2, 3 et 4 : ");
                }
            } while (!wrong);

        }
    }

    /**
     * Methode publique permettant de faire un affichage de la liste des
     * emprunts.
     * 
     * @return La chaine de caractere contenant le contenu de la liste d'emprunt
     *         et reservation
     */
    private String afficherChoixEmpruntEtResa(
            ArrayList<MaterielEmprunte> listeEmpruntResa) {
        String retour = "\n     EMPRUNTS ET RESERVATIONS\n";

        for (int i = 0; i < listeEmpruntResa.size(); i++) {
            retour += i + ". " + listeEmpruntResa.get(i) + "\n";
        }
        return retour;
    }

    /**
     * Demande a l'utilisateur s'il souhaite retourner au menu principal, a
     * savoir le menu de choix de statut (emprunteur, gestionnaire).
     * 
     * @return Une chaine de caracteres representant le choix de l'utilisateur.
     */
    private String retourMenuPrincipal() {
        System.out.printf("\nVoulez-vous revenir au menu principal ? (Y/N) : ");
        String choix = console.readLine();
        return choix;
    }

    /**
     * Demande a l'utilisateur s'il souhaite retourner au menu gestion.
     * 
     * @return Une chaine de caracteres representant le choix de l'utilisateur.
     */
    private String retourMenuGestion() {
        System.out.printf("\nVoulez-vous revenir au menu GESTION ? (Y/N) : ");
        String choix = console.readLine();
        return choix;
    }

    /**
     * Demande a l'utilisateur s'il souhaite retourner au menu emprunt.
     * 
     * @return Une chaine de caracteres representant le choix de l'utilisateur.
     */
    private String retourMenuEmprunt() {
        System.out.printf("\nVoulez-vous revenir au menu EMPRUNT ? (Y/N) : ");
        String choix = console.readLine();
        return choix;
    }

    /**
     * Affiche le menu Affichage des donnees et affiche les donnees
     * correspondant au choix de l'utilisateur.
     */
    private void afficher() {
        System.out
                .println("\nQue voulez-vous afficher ? : \n1. Stock total\n2. Matériel en réparations\n3. Emprunts et Réservations en cours\n4. Emprunts et réservations refusés\n");
        String choix = console.readLine();

        switch (choix) {
        case "1": {
            System.out.println(gestion.afficherStockTotal());
            break;
        }

        case "2": {
            System.out.println(gestion.afficherReparation());
            break;
        }

        case "3": {
            System.out.println(gestion.afficherResa());
            break;
        }

        case "4": {
            System.out.println(gestion.afficherRefus());
            break;
        }

        default: {
            break;
        }
        }
    }

    /**
     * Teste si la chaine de caractere passee est un entier.
     * 
     * @param chaine
     *            La chaine de caractere a tester
     * @return Un booleen representant si la chaine est effectivement un entier
     *         (true) ou non (false).
     */
    private boolean intTest(String chaine) {
        try {
            int i = Integer.parseInt(chaine);
            return true;
        }

        catch (Exception e) {
            return false;
        }
    }

}
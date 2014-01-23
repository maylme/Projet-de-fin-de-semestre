package base ;
import java.io.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Materiel.Materiel;
import Materiel.MaterielEmprunte;

/** 
* Cette classe gere l'interface utilisateur du programme.
* Elle permet a l'utilisateur de se connecter puis d'effectuer
* un ensemble d'actions sur le stock en fonction de son statut
* (emprunteur ou gestionnaire).
* 
* @author Vincent Montalieu
* @version 1.2 (5.Dec.2013) 
*/

public class InterfaceUtilisateur
{
	private Console console ;
	private Gestion gestion ;
	private static final String MOT_DE_PASSE = "ipa2013_ihm!" ;

	/** 
	* Constructeur qui contient l'architecture
	* de l'interface utilisateur. Toutes les fonctions
	* constituant toutes les actions possibles sont appelees ici.
	* Le constructeur permet de definir un utilisateur puis de le 
	* faire naviguer a travers les differents menus. Il gere les retours
	* aux menus precedents et principal.
	*/ 
	public InterfaceUtilisateur()
	{
		console = System.console();
		String menu = "Y" ;
		boolean premierPassage = false ;
		boolean mdpCorrect;
		
		do
		{
			System.out.println("\n          --------------------------------------\n          | Bienvenue dans IHM STOCK MANAGER ! |\n          --------------------------------------\n\n\nQue souhaitez-vous faire ?\n\n1. Accès emprunt\n2. Accès gestion\n") ;
			boolean statut = choixUtilisateur();
			String nom = nom() ;
			String prenom = prenom() ;

			if(!premierPassage)
			{
				gestion = new Gestion() ;
			}
			
			if (gestion.existe(nom, prenom, statut))
			{
				mdpCorrect=demandeMotDePasse(statut, false);
			}
			else
			{
				if (statut)
				{
					System.out.printf("\nPour créer un compte gestionnaire, il faut le mot de passe administratif.") ;
					if (demandeMotDePasse(statut, true))
					{
						mdpCorrect=true;
						gestion.createNewGestionnaire(nom, prenom, newMotDePasse());
					}
					else
						mdpCorrect=false;
				}
				else
				{
					System.out.println("\n Vous êtes un :\n\n1. Elève\n2. Professeur\n") ;
					boolean prof = choixUtilisateur();
					if(prof)
					{
						gestion.createNewProf(nom,prenom, newMotDePasse());
					}
					else
					{
						gestion.createNewEleve(nom, prenom, newMotDePasse());
					}
					mdpCorrect=true;
				}
			}

			if (mdpCorrect)
			{
				//partie gestion
				if(statut)
				{
					do
					{
						String var = menuGestion();
						
						switch(var)
						{
							case "1":
							{
								ajouterStock();
								break;
							}

							case "2":
							{
								supprimerStock();
								break;
							}

							case "3":
							{
								ajouterReparations();
								break;
							}

							case "4":
							{
								retirerReparations();
								break;
							}

							case "5":
							{
								consulterStatistiques();
								break;
							}

							case "6":
							{
								afficher();
								break;
							}

							case "7":
							{
								gestion.serialisationFichierLisible();
								break;
							}

							default:
							{
								break;
							}
						}
					}
					while(menu.equals(retourMenuGestion()));
				}

				//partie emprunt
				else
				{			
					do
					{
						String var = menuEmprunt();
					
						switch(var)
						{
							case "1":
							{
								emprunter();
								break;
							}

							case "2":
							{
								rendre();
								break;
							}

							default:
							{
								break;
							}
						}
					}
					while(menu.equals(retourMenuEmprunt()));
				}
				premierPassage = true ;
			}
		}
		while(menu.equals(retourMenuPrincipal()));
	}

	/** 
	* Permet a l'utilisateur choisir son statut
	* entre celui d'emprunteur et celui de
	* gestionnaire. S'il choisit gestionnaire,
	* il devra renseigner un mot de passe. Apres
	* 3 essais, il sera automatiquement considere
	* comme un emprunteur.
	* 
	* @return Un booleen representant le statut :
	* false pour un emprunteur et true pour un
	* gestionnaire. 
	*/ 
	private boolean choixUtilisateur()
	{
		boolean wrong = false ;
		String choix = "" ;

		do
		{
			choix = console.readLine() ;

			if(choix.equals("1"))
			{
				wrong = true ;
				return false;
			}

			else if(choix.equals("2"))
			{
				wrong = true ;
				return true;
			}

			else
			{
				System.out.printf("\nMerci de choisir entre 1 et 2 : ") ;
			}
		}
		while(!wrong);
	}

	/** 
	* Permet de demander un mot de passe et de vérifier s'il est
	* le même que celui passer en parametre. Il y a trois essais.
	* 
	* @param gestionnaire booleen permettant de savoir si l'utilisateur courant est
	* gestionnaire
	* @param etapeCreation booleen permettant de savoir si on est a la creation
	* d'un gestionnaire ou a son mdp personnel
	* @return Un booleen pour savoir si le mot de passe est le bon
	*/
	private boolean demandeMotDePasse(boolean gestionnaire, boolean etapeCreation)
	{
		int essais = 3 ;

		do
		{				
			System.out.printf("\nSaisissez le mot de passe : ") ;
			String password = new String(console.readPassword()) ;

			if(gestion.password(password, gestionnaire, etapeCreation))
			{
				System.out.println("\nMot de passe correct !\n") ;//Redirection vers le mode Gestion
				return true ;
			}

			else
			{
				essais -- ;
				System.out.println("\nMot de passe erroné !\nEssai(s) restant(s) : " + essais) ;
			}
		}
		while(essais>0);
		return false;
	}
	/** 
	* Permet a l'utilisateur de saisir son nom. 
	* 
	* @return Une chaine de caracteres representant le nom saisi.
	*/ 
	private String nom()
	{
		System.out.printf("\nEntrez votre nom : ") ;
		String retour = console.readLine() ;
		return retour ;
	}

	/** 
	* Permet a l'utilisateur de saisir son prenom. 
	* 
	* @return Une chaine de caracteres representant le prenom saisi.
	*/ 
	private String prenom()
	{
		System.out.printf("Entrez votre prénom : ") ;
		String retour = console.readLine() ;
		return retour ;
	}

	/** 
	* Permet a l'utilisateur de saisir un mot de passe. 
	* 
	* @return Une chaine de caracteres representant le mot de passe saisi.
	*/ 
	private String newMotDePasse()
	{
		System.out.printf("\nEntrez un mot de passe : ") ;
		String retour = console.readLine() ;
		return retour ;
	}
	
	/** 
	* Affiche le menu principal du mode emprunt. 
	* 
	* @return Une chaine de caracteres representant le choix
	* fait par l'utilisateur.
	*/ 
	private String menuEmprunt()
	{
		System.out.println("\n\n          Menu EMPRUNT\n\nQue voulez-vous faire ?\n\n1. Emprunter du matériel\n2. Rendre du matériel\n") ;
		String retour = console.readLine() ;
		return retour ;
	}

	/** 
	* Affiche le menu principal du mode gestion. 
	* 
	* @return Une chaine de caracteres representant le choix
	* fait par l'utilisateur.
	*/ 
	private String menuGestion()
	{
		System.out.println("\n          Menu GESTION\n\nQue voulez-vous faire ?\n\n1. Ajouter du matériel au stock\n2. Supprimer du matériel du stock total\n3. Supprimer du matériel en réparation\n4. Terminer une réparation de matériel\n5. Modifier ou annuler un emprunt ou une réservation\n6. Affichage des données\n7. Export de toutes les données") ;
		String retour = console.readLine() ;
		return retour ;
	}

	/** 
	* Permet a l'utilisateur d'emprunter
	* un materiel en saisissant le type de materiel
	* souhaite, le nombre d'exemplaires, la duree et
	* la date du jour. Affiche un message confirmant l'emprunt ou
	* expliquant le probleme si l'emprunt n'est pas possible. 
	*/ 
	private void emprunter()
	{
		System.out.printf("\nQue voulez-vous emprunter (entrer un mot clé) ? : ") ;
		String type = console.readLine() ;
		System.out.printf("\nA quelle date voulez vous l'emprunter ? (format JJ/MM/AAAA) : ") ;
		String dateDEmprunt = console.readLine() ;
		System.out.printf("\nA quelle date voulez vous le rendre ? (format JJ/MM/AAAA) : ") ;
		String dateRetour = console.readLine() ;
		ArrayList<Materiel> matEmpruntable = gestion.listeMaterielEmpruntable(type, formatDate(dateDEmprunt), formatDate(dateRetour));
		if (!matEmpruntable.equals(null))
		{
			System.out.printf(afficherEmpruntable(matEmpruntable)) ;
			Materiel matChoisi = matEmpruntable.get(choixDansListe(matEmpruntable.size()));
			System.out.printf("Combien d'exemplaires désirez-vous ? : ") ;
			String test = console.readLine() ;
			int nombre = 0 ;
		
			if(intTest(test))
				nombre = Integer.parseInt(test) ;
		

			int retour = -2 ;

			if(nombre>0)
				retour=gestion.emprunt(matChoisi,nombre,formatDate(dateDEmprunt),formatDate(dateRetour));

			if(retour==-1)
				System.out.println("\nEmprunt validé !") ;

			else if(retour!=0 && retour!=-2)
				System.out.println("\nEmprunt impossible... Seulement " + retour + " exemplaire(s) disponible(s).\n") ;

			else if(retour==0)
				System.out.println("\nAucun exemplaire disponible !\n") ;

			else if(retour==-2)
				System.out.println("\nErreur de saisie !\n") ;
		}
		else
			System.out.println("\nAucun materiel de ce type.\n") ;
	}

	/** 
	* Methode publique permettant de faire un affichage de
	* la liste des emprunts de la personne.
	* 
	* @return La chaine de caractere contenant le contenu
	* de la liste d'emprunt de la personne
	*/
	private String afficherEmpruntable(ArrayList<Materiel> matEmpruntable)
	{
		String retour = "\n     MATERIELS DISPONIBLES\n" ;

		for(int i = 0 ; i<matEmpruntable.size() ; i++)
		{
			retour += i+". " + matEmpruntable.get(i) + "\n" ;
		}
		return retour ;
	}
	
	/** 
	* Methode publique permettant de faire un affichage de
	* la liste des emprunts de la personne.
	* 
	* @return La chaine de caractere contenant le contenu
	* de la liste d'emprunt de la personne
	*/
	private String afficherEmpruntUtilisateur(ArrayList<MaterielEmprunte> matEmprunt)
	{
		String retour = "\n     EMPRUNTS\n" ;

		for(int i = 0 ; i<matEmprunt.size() ; i++)
		{
			retour += i+". " + matEmprunt.get(i) + "\n" ;
		}
		return retour ;
	}
	
	/** 
	* Permet a l'utilisateur choisir un élément de la liste qui lui est presente.
	* 
	* @param tailleDeLaListe entier représentant la taille de la liste
	* @return int representant le choix (position) dans la liste presentee
	*/ 
	private int choixDansListe(int tailleDeLaListe)
	{
		boolean wrong = false ;
		String choix = "" ;

		do
		{
			choix = console.readLine() ;

			if (intTest(choix))
			{
				if(Integer.parseInt(choix)>=0 && Integer.parseInt(choix)< tailleDeLaListe)
				{
					wrong = true ;
					return Integer.parseInt(choix);
				}
			}
			else
			{
				System.out.printf("\nMerci de choisir entre 0 et " + (tailleDeLaListe - 1)  + " : ") ;
			}
		}
		while(!wrong);
		return -1;
	}

	
	/**  
	* Permet a l'utilisateur de rendre
	* un materiel en saisissant le type de materiel
	* souhaite et le nombre d'exemplaires a rendre.
	* Affiche un message confirmant ou infirmant le retour. 
	*/ 
	private void rendre()
	{
		
		System.out.println(afficherEmpruntUtilisateur(gestion.listeEmpruntsParEmprunteur());
		
		System.out.printf("\nQue voulez-vous rendre ? : ") ;
		MaterielEmprunte matARendre = gestion.listeEmpruntsParEmprunteur().get(choixDansListe(gestion.listeEmpruntsParEmprunteur().size()));
		System.out.printf("Combien d'exemplaires désirez-vous rendre ? : ") ;
		String test = console.readLine() ;
		int nombre = 0 ;
		
		if(intTest(test))
			nombre = Integer.parseInt(test) ;

		System.out.printf("Combien d'exemplaires rendus sont hors service ? : ") ;
		String nbHS = console.readLine() ;
		int nombreHS = 0 ;
		
		if(intTest(nbHS))
			nombreHS = Integer.parseInt(test) ;
		
		boolean retour = gestion.rendre(matARendre, nombre, nombreHS);

		if(retour)
		{
			System.out.println("\nRetour confirmé !") ;
		}

		else
		{
			System.out.println("\nRetour impossible !") ;
		}
	}

	/** 
	* Permet de répérer une date dans le format Date à partir d'un String
	* 
	* @param date Chaine de caractere contenant la date JJ/MM/AAAA
	* @return Date retourne la date dans le bon format
	*/ 
	private Date formatDate (String date)
	{
		String [] elements = date.split("/");
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(elements[2]), Integer.parseInt(elements[1]), Integer.parseInt(elements[0]));
		return c.getTime();		
	}
	/** 
	* Permet a l'utilisateur d'ajouter
	* un materiel au stock en saisissant le type de materiel et
	* le nombre d'exemplaires a ajouter 
	*/ 
	private void ajouterStock()
	{
		System.out.println(gestion.afficherStockTotal());

		System.out.printf("\nQuel type voulez-vous ajouter ? : ") ;
		String type = console.readLine() ;
		System.out.printf("Combien d'exemplaires voulez-vous ajouter ? : ") ;
		String test = console.readLine() ;
		int nombre = 0 ;
		
		if(intTest(test))
			nombre = Integer.parseInt(test) ;

		gestion.ajoutMaterielStock(type,nombre);
	}

	/** 
	* Permet a l'utilisateur de supprimer un type du stock
	* en saisissant le nom du type. 
	*/ 
	private void supprimerStock()
	{
		System.out.println(gestion.afficherStock());

		System.out.printf("\nQuel type voulez-vous supprimer ? : ") ;
		String type = console.readLine() ;

		int retour = gestion.retirerMaterielStock(type);

		if(retour>-2)
		{
			System.out.println("\nSuppression confirmé !") ;
		}

		else
		{
			System.out.println("\nSuppression impossible !") ;
		}
	}

	/** 
	* Permet a l'utilisateur d'ajouter
	* un materiel a la liste en saisissant le type de materiel et
	* le nombre d'exemplaires a ajouter.
	*/  
	private void ajouterReparations()
	{
		System.out.println(gestion.afficherStock());

		System.out.printf("\nQuel type voulez-vous faire réparer ? : ") ;
		String type = console.readLine() ;
		System.out.println("\nCombien d'exemplaires voulez-vous faire réparer ? : ") ;
		String test = console.readLine() ;
		int nombre = 0 ;
		
		if(intTest(test))
			nombre = Integer.parseInt(test) ;

		int retour = gestion.aReparer(type,nombre);

		if(retour==-1)
		{
			System.out.println("\nMise en réparation confirmée !") ;
		}

		else if(retour!=0)
		{
			System.out.println("\nMise en réparation impossible... Seulement " + retour + " exemplaires disponibles.") ;
		}

		else
		{
			System.out.println("\nMise en réparation impossible") ;
		}
	}

	/** 
	* Permet a l'utilisateur de retirer
	* un materiel de la liste des réparations
	* en saisissant le type de materiel et le nombre
	* d'exemplaires a retirer.
	* Affiche un message confirmant ou infirmant le retrait. 
	*/ 
	private void retirerReparations()
	{
		System.out.println(gestion.afficherReparations());

		System.out.printf("\nQuel type voulez-vous retirer des réparations ? : ") ;
		String type = console.readLine() ;
		System.out.printf("\nCombien d'exemplaires voulez-vous retirer des réparations ? : ") ;
		String test = console.readLine() ;
		int nombre = 0 ;
		
		if(intTest(test))
			nombre = Integer.parseInt(test) ;

		int retour = gestion.retourReparation(type, nombre);

		if(retour>0)
		{
			System.out.println("\nRetrait confirmé !") ;
		}

		else
		{
			System.out.println("\nRetrait impossible !") ;
		}
	}

	/** 
	* Demande a l'utilisateur s'il souhaite
	* retourner au menu principal, a savoir le menu de
	* choix de statut (emprunteur, gestionnaire).
	* 
	* @return Une chaine de caracteres representant le choix
	* de l'utilisateur.
	*/ 
	private String retourMenuPrincipal()
	{
		System.out.printf("\nVoulez-vous revenir au menu principal ? (Y/N) : ") ;
		String choix = console.readLine();
		return choix ;
	}

	/** 
	* Demande a l'utilisateur s'il souhaite
	* retourner au menu gestion.
	* 
	* @return Une chaine de caracteres representant le choix
	* de l'utilisateur.
	*/
	private String retourMenuGestion()
	{
		System.out.printf("\nVoulez-vous revenir au menu GESTION ? (Y/N) : ") ;
		String choix = console.readLine();
		return choix ;
	}

	/** 
	* Demande a l'utilisateur s'il souhaite
	* retourner au menu emprunt.
	* 
	* @return Une chaine de caracteres representant le choix
	* de l'utilisateur.
	*/
	private String retourMenuEmprunt()
	{
		System.out.printf("\nVoulez-vous revenir au menu EMPRUNT ? (Y/N) : ") ;
		String choix = console.readLine();
		return choix ;
	}

	/** 
	* Affiche le menu statistiques et affiche
	* la statistique correspondant au choix de
	* l'utilisateur.
	*/ 
	private void consulterStatistiques()
	{
		System.out.println("\nQue voulez-vous savoir ? : \n1. Matériel le plus emprunté\n2. Matériel le plus réparé\n3. Plus gros emprunteur\n") ;
		String choix = console.readLine();

		switch(choix)
		{
			case "1":
			{
				if(gestion.materielPlusEmprunte()!=null)
					System.out.println("\nType de matériel : " + gestion.materielPlusEmprunte().getNom() + "\nIdentifiation : " + gestion.materielPlusEmprunte().getIdent() + "\nNombre d'emprunts : " + gestion.materielPlusEmprunte().getNombre()) ;

				else
					System.out.println("Aucun emprunt !") ;

				break;
			}

			case "2":
			{
				if(gestion.materielPlusRepare()!=null)
					System.out.println("\nType de matériel : " + gestion.materielPlusRepare().getNom() + "\nIdentifiation : " + gestion.materielPlusRepare().getIdent() + "\nNombre de réparations : " + gestion.materielPlusRepare().getNombre()) ;

				else
					System.out.println("Aucune réparation !") ;

				break;
			}

			case "3":
			{
				if(gestion.plusGrosEmprunteur()!=null)
					System.out.println("\n" + gestion.plusGrosEmprunteur().getNom().toUpperCase() + " " +gestion.plusGrosEmprunteur().getPrenom() + "\nNombre d'emprunts : " + gestion.plusGrosEmprunteur().getNbrEmprunt()) ;

				else
					System.out.println("Aucun emprunteur !") ;

				break;
			}

			default:
			{
				break;
			}
		}
	}

	/** 
	* Affiche le menu Affichage des donnees et affiche
	* les donnees correspondant au choix de
	* l'utilisateur.
	*/ 
	private void afficher()
	{
		System.out.println("\nQue voulez-vous afficher ? : \n1. Matériel disponible\n2. Matériel en réparations\n3. Emprunts et Réservation en cours\n") ;
		String choix = console.readLine();

		switch(choix)
		{
			case "1":
			{
				System.out.println(gestion.afficherStock()) ;
				break;
			}

			case "2":
			{
				System.out.println(gestion.afficherReparations()) ;
				break;
			}

			case "3":
			{
				System.out.println(gestion.afficherEmprunts()) ;
				break;
			}

			default:
			{
				break;
			}
		}
	}

	/** 
	* Teste si la chaine de caractere passee est un entier. 
	* 
	* @param chaine La chaine de caractere a tester 
	* @return Un booleen representant si la chaine est
	* effectivement un entier (true) ou non (false).
	*/ 
	private boolean intTest(String chaine)
	{
		try
		{ 
			int i = Integer.parseInt(chaine); 
			return true ; 
		}

		catch (Exception e)
		{ 
			return false ;
		}
	}

}
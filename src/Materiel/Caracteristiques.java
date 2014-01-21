package Materiel;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Contient les caracteristiques pour construire un Materiel
 * Un objet Caracteristiques est representé par un hash map dont les 
 * possibilités de cles sont restreinte. 
 * Quand le gestionnaire rentrera les caracteristique, il devra utiliser 
 * les cles existentes ou en creer de nouvelle pour les utiliser. 
 * @author lyameina
 *
 */
public class Caracteristiques {
	private ArrayList<String> clePossible;
	private HashMap<String, String> resultat;
	
	//TODO : serialisation à la place
	public Caracteristiques(){
		clePossible = new ArrayList<String>();
		clePossible.add("type");
		clePossible.add("nom");
		clePossible.add("marque");
		clePossible.add("écran");
		clePossible.add("son");

		resultat = new HashMap<String, String>();
		
		}
	
	public void addSpecification (String cle, String valeur) throws CleInexistanteException {
		if (!(clePossible.contains(cle))){
			throw new CleInexistanteException();
		}
		else{
			resultat.put(cle, valeur);
		}
	}

	public void  addCle(String cle){
		clePossible.add(cle);
	}
	
	public HashMap<String, String> getResultat(){
		return resultat;
	}
	
}
/**
GEstion ou dans l'interface
------------------------------
Caracteristiques c = new Caracteristique() ;

String cle = Cle que le gars veut utiliser;
String valeur = valeur que le gars veut utiliser


try{
	c.addSpecification (cle, valeur);
}
catch (CleInexistanteEcxeption e){
	demander si il veut creer la cle
	si oui:
		c.addCle(String cle)
		c.addSpecification (cle, valeur)
}

}
*/

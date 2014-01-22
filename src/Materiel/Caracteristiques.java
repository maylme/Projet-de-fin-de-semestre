package Materiel;

import java.util.ArrayList;
import java.util.HashMap;

import Outils.FichierData;
/**
 * Contient les caracteristiques pour construire un Materiel
 * Un objet Caracteristiques est representé par un hash map dont les 
 * possibilités de cles sont restreinte. 
 * Quand le gestionnaire rentrera les caracteristique, il devra utiliser 
 * les cles existentes ou en creer de nouvelle pour les utiliser. 
 * @author lyameina
 *
 */
@SuppressWarnings("serial")
public class Caracteristiques implements java.io.Serializable{
	public ArrayList<String> clePossible;
	private final String NOMFICHIER = "listeCaracteristiques";
	private HashMap<String, String> resultat;
	
	public Caracteristiques(){
		FichierData f = new FichierData();
		clePossible = f.deserialisationListeString(NOMFICHIER);
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
		FichierData f = new FichierData();
		f.serialisationListeString(clePossible, NOMFICHIER);
	}
	
	public HashMap<String, String> getResultat(){
		return resultat;
	}
	
	public boolean  equals(Caracteristiques c){
		if (c.getResultat().equals(resultat))
			return true;
		return false;
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

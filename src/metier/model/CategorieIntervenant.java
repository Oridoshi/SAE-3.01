package metier.model;

import metier.DB;
import metier.IModifiable;
import metier.repo.CategorieIntervenantDB;
import metier.repo.CategorieModuleDB;

/**
 * Categorie
 * Classe servant a la gestion des types d'intervenants 
 */
public class CategorieIntervenant implements IModifiable
{
	private String codeOrigine;
	private String code;
	private String nom;
	private int    minH;
	private int    maxH;
	private double coefTp;

	public CategorieIntervenant(String code, String nom, int minH, int maxH, double coefTp )
	{
		this.codeOrigine = code;
		this.code   = code;
		this.nom    = nom;
		this.minH   = minH;
		this.maxH   = maxH;
		this.coefTp = coefTp;
	}

	public String getCode  () { return code;   }
	public String getNom   () { return nom;    }
	public int    getMinH  () { return minH;   }
	public int    getMaxH  () { return maxH;   }
	public double getCoefTp() { return coefTp; }
	public String getCodeOrigine() { return this.codeOrigine; }


	public boolean setCode  ( String code   ) { 
		if ( CategorieIntervenantDB.getParCode(code) != null ) return false;
		this.code   = code;
		return true;
	}
	public void setCodeOrigine(String code) { this.codeOrigine = code; }
	public void setNom   ( String nom    ) {
		this.nom    = nom;
	}
	public boolean setMinH  ( int minH      ) {
		if ( minH < 0 ) return false;
		if ( minH > this.maxH ) return false;
		this.minH   = minH;
		return true;
	}
	public boolean setMaxH  ( int maxH      ) {
		if ( maxH < 0 ) return false;
		if ( maxH < this.minH ) return false;
		this.maxH   = maxH;
		return true;
	}
	public boolean setCoefTp( double coefTp ) {
		if ( coefTp > 0 ){
			this.coefTp = coefTp;
			return true;
		}
		return false;
	}

	public boolean sauvegarder(){
        return CategorieIntervenantDB.save(this);
    }

    public boolean supprimer(){
        return CategorieIntervenantDB.delete(this);
    }

}
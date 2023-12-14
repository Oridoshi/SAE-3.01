package metier.model;

import metier.DB;
import metier.repo.CategorieIntervenantDB;
import metier.repo.CategorieModuleDB;

/**
 * Categorie
 * Classe servant a la gestion des types d'intervenants 
 */
public class CategorieIntervenant
{
	private String code;
	private String nom;
	private int    minH;
	private int    maxH;
	private double coefTp;

	public CategorieIntervenant(String code, String nom, int minH, int maxH, double coefTp )
	{
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


	public void setCode  ( String code   ) { this.code   = code;   }
	public void setNom   ( String nom    ) { this.nom    = nom;    }
	public void setMinH  ( int minH      ) { this.minH   = minH;   }
	public void setMaxH  ( int maxH      ) { this.maxH   = maxH;   }
	public void setCoefTp( double coefTp ) { this.coefTp = coefTp; }

	public boolean sauvegarder(){
        return CategorieIntervenantDB.save(this);
    }

    public boolean supprimer(){
        return CategorieIntervenantDB.delete(this);
    }

}
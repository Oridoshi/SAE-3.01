package metier;

/**
 * Categorie
 * Classe servant a la gestion des types d'intervenants 
 */
public class Categorie
{
	private int    id;
	private String code;
	private String nom;
	private int    minH;
	private int    maxH;
	private double coefTp;

	private DB db;

	public Categorie(String code, String nom, int minH, int maxH, double coefTp )
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

}
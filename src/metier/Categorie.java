package metier;

/**
 * Categorie
 */
public class Categorie
{
	private String code;
	private String nom;
	private int service;
	private int hMax;
	private double rationTP;

	public Categorie(String code, String nom, int service, int hMax, double rationTP)
	{
		this.code     = code;
		this.nom      = nom;
		this.service  = service;
		this.hMax     = hMax;
		this.rationTP = rationTP;
	}

	public String getCode()     {return code;}
	public String getNom()      {return nom;}
	public int getService()  {return service;}
	public int    gethMax()     {return hMax;}
	public double getRationTP() {return rationTP;}

	public void setRationTP(double rationTP) {this.rationTP = rationTP;}
	public void sethMax(int hMax)            {this.hMax = hMax;}
	public void setService(int service)      {this.service = service;}
	public void setNom(String nom)           {this.nom = nom;}
	public void setCode(String code)         {this.code = code;}

	
}
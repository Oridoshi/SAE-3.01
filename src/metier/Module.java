package metier;

/**
 * Module
 */
public class Module
{
	private String  nom;
	private String  typeModule;
	private int     semestre;
	private boolean valider;

	public Module(String typeModule, String nom, boolean valider)
	{
		this.nom = nom;
		this.typeModule = typeModule;
		this.semestre = Integer.parseInt(nom.charAt(1) + "");
		this.valider = valider;
	}

	public String getNom(){return nom;}
	public String getTypeModule(){return typeModule;}
	public int getSemestre(){return semestre;}
	public boolean isValider(){return valider;}
}

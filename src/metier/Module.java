package metier;

/**
 * Module
 * Classe qui permet de creer un module (classe mère des classes ModuleX)
 */
public class Module
{
	private String  nom;
	private String  typeModule;
	private String  code;
	private int     semestre;
	private boolean valider;

	public Module(String typeModule, String code, String nom, boolean valider)
	{
		this.nom = nom;
		this.typeModule = typeModule;
		this.code = code;
		this.semestre = Integer.parseInt(code.charAt(1) + "");
		this.valider = valider;
	}

	public String  getNom(){return nom;}
	public String  getTypeModule(){return typeModule;}
	public int     getSemestre(){return semestre;}
	public boolean getValider(){return valider;}
	public String  getCode(){return code;}
}

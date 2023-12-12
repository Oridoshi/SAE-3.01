package metier.model;

import java.util.Map;

/**
 * Module
 * Classe qui permet de creer un module (classe mère des classes ModuleX)
 */
public class Module
{
	private String   code;
	private Semestre semestre;
	private CategorieModule categorieModule;
	private boolean  valider;
	private String libelleCourt;
	private String libelleLong;
	private Map<String, ProgrammeItem> programme;

	public Module(String code, Semestre semestre, CategorieModule categorieModule, boolean valider, String libelleCourt, String libelleLong, Map<String, ProgrammeItem> programme) {
		this.code = code;
		this.semestre = semestre;
		this.categorieModule = categorieModule;
		this.valider = valider;
		this.libelleCourt = libelleCourt;
		this.libelleLong = libelleLong;
		this.programme = programme;
	}

	public ProgrammeItem getProgrammeItem(String key) {
		return programme.get(key);
	}
	public CategorieModule getCategorieModule(){return categorieModule;}
	public Semestre getSemestre(){return semestre;}
	public boolean getValider(){return valider;}
	public String  getCode(){return code;}

	public boolean isValider() {
		return valider;
	}

	public String getLibelleCourt() {
		return libelleCourt;
	}

	public String getLibelleLong() {return libelleLong;}
}

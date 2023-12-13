package metier.model;

import java.util.List;
import java.util.Map;

import metier.repo.AffectationDB;
import metier.repo.CategorieHeureDB;

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



	public int getTotalAffecteEqTd()
	{
		List<Affectation> affectations = new AffectationDB().getAffectationsParCodeModule(this.code);
		int totalHeure = 0;
		for ( Affectation affectation : affectations ){
			totalHeure += affectation.getNbEqTd();
		}
		return totalHeure;
	}

	public int getTotalPromoEqTd()
	{
		int total = 0;
		for ( String key : programme.keySet() ){
			ProgrammeItem programmeItem = programme.get(key);
			CategorieHeure categorieHeure = new CategorieHeureDB().getCategorieHeureParId(key);
			int h = programmeItem.getNbHeure();
			if ( programmeItem.getNbSemaine() != null ){
				h = h * programmeItem.getNbSemaine();
			}
			if ( key.equals("TD")){
				h = h * semestre.getNbGroupeTd();
			}
			if ( key.equals("TP")){
				h = h * semestre.getNbGroupeTp();
			}
			h = (int) ( h * categorieHeure.getCoef() );
			total += h;
		}
		return total;
	}
}

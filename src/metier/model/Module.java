package metier.model;

import java.util.List;
import java.util.Map;

import metier.repo.AffectationDB;
import metier.repo.CategorieHeureDB;
import metier.repo.CategorieModuleDB;
import metier.repo.ModuleDB;
import metier.repo.ProgrammeItemDB;
import metier.repo.SemestreDB;

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
	private Programme programme;

	public Module(String code, Semestre semestre, CategorieModule categorieModule, boolean valider, String libelleCourt, String libelleLong) {
		this.code = code;
		this.semestre = semestre;
		this.categorieModule = categorieModule;
		this.valider = valider;
		this.libelleCourt = libelleCourt;
		this.libelleLong = libelleLong;
		Programme programme = new Programme();
		if ( ProgrammeItemDB.listParCodeModule(this.code) == null ) {
			for ( PatternCategorieModuleItem pattern : this.categorieModule.getCategorieHeures() ){
				programme.addItem(new ProgrammeItem(this.categorieModule, pattern.getCategorieHeure(), this.code, 0, 0, 0));
			}
		} else {
			for ( ProgrammeItem item : ProgrammeItemDB.listParCodeModule(this.code)){
				programme.addItem(item);
			}
		}
		this.programme = programme;
	}

	

	public boolean setCode(String code) {
		if ( ModuleDB.getParCode(code) != null ) return false;
		this.code = code;
		return true;
	}

	public boolean setCategorieModule(CategorieModule categorieModule) {
		if ( !CategorieModuleDB.list().contains(categorieModule) ) return false;
		this.categorieModule = categorieModule;
		return true;
	}

	public void setValider(boolean valider) {
		this.valider = valider;
	}

	public void setLibelleCourt(String libelleCourt) {
		this.libelleCourt = libelleCourt;
	}

	public void setLibelleLong(String libelleLong) {
		this.libelleLong = libelleLong;
	}

	public Programme getProgramme(){
		return programme;
	}

	public CategorieModule getCategorieModule(){return this.categorieModule;}
	public Semestre getSemestre(){return this.semestre;}
	public boolean getValider(){return valider;}
	public String  getCode(){return code;}

	public boolean isValider() {
		return valider;
	}

	public String getLibelleCourt() {
		return libelleCourt;
	}

	public String getLibelleLong() {
		return libelleLong;
	}

	public Integer getTotalPromoEqTd()
	{
		int h = 0;
		if ( this.getProgramme() == null ) return 0;
		for ( PatternCategorieModuleItem item : this.getCategorieModule().getCategorieHeures() ){
			h += this.getProgramme().getItem(item.getCategorieHeure().getNom()).getPromoEqTd();
		}
		return h;
	}

	public boolean sauvegarder()
	{
		for ( ProgrammeItem item : programme.listProgrammeItems() ){
			item.sauvegarder();
		}
		return ModuleDB.save(this);
	}

	public boolean supprimer()
	{
		for ( ProgrammeItem item : programme.listProgrammeItems() ){
			item.sauvegarder();
		}
		return ModuleDB.delete(this);
	}

	public List<Affectation> getLstAffectation()
	{
		return AffectationDB.getAffectationsParModule(code);
	}


	public int getNbHeureProgramme(String string)
	{
		return this.getProgramme().getItem(string).getNbHPn();
	}



	public int getNbHeureSemaine(String string)
	{
		return this.getProgramme().getItem(string).getNbHeure();
	}



	public int getNbSemaine(String string) 
	{
		return this.getProgramme().getItem(string).getNbSemaine();
	}
}

package metier.model;

import java.util.List;

import metier.IModifiable;
import metier.repo.AffectationDB;
import metier.repo.CategorieModuleDB;
import metier.repo.ModuleDB;
import metier.repo.ProgrammeItemDB;

/**
 * Module
 * Classe qui permet de creer un module (classe mère des classes ModuleX)
 */
public class Module implements IModifiable
{
	private int id;
	private String codeOrigine;
	private String   code;
	private Semestre semestre;
	private CategorieModule categorieModule;
	private boolean  valider;
	private String libelleCourt;
	private String libelleLong;
	private Programme programme;

	public Module(String code, Semestre semestre, CategorieModule categorieModule, boolean valider, String libelleCourt, String libelleLong) {
		this.codeOrigine = code;
		this.code = code;
		this.semestre = semestre;
		this.categorieModule = categorieModule;
		this.valider = valider;
		this.libelleCourt = libelleCourt;
		this.libelleLong = libelleLong;
	}

	public Module setId(int id){
		this.id = id;
		return this;
	}
	public int getId() {
		return this.id;
	}

	public String getCodeOrigine() {
		return codeOrigine;
	}

	public void setCodeOrigine(String codeOrigine) {
		this.codeOrigine = codeOrigine;
	}

	public boolean setCode(String code) {
		if ( ModuleDB.getParCode(code) != null && !code.equals(this.code)) return false;
		this.code = code;
		// this.codeOrigine = code;
		for ( ProgrammeItem item : this.programme.listProgrammeItems()){
			item.setModule(this);
		}
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
		if ( this.programme != null ) return this.programme;
		Programme programme = new Programme();
		this.programme = programme;
		if ( ProgrammeItemDB.listParIdModule(this.id) == null ) {
			for ( PatternCategorieModuleItem pattern : this.categorieModule.getCategorieHeures() ){
				programme.addItem(new ProgrammeItem(this.categorieModule, pattern.getCategorieHeure(), this, 0, 0, 0));
			}
		} else {
			for ( ProgrammeItem item : ProgrammeItemDB.listParIdModule(this.id)){
				programme.addItem(item);
			}
		}
		return programme;
	}

	public CategorieModule getCategorieModule(){return this.categorieModule;}
	public Semestre getSemestre(){return this.semestre;}
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

	public Integer getTotalAffecteEqTd(){
		int ret = 0;
		List<Affectation> affectations = getLstAffectation();
		if(affectations == null) return 0;
		for ( Affectation affectation : affectations ){
			ret += affectation.getNbEqTd();
		}
		return ret;
	}

	public boolean sauvegarder()
	{
		ModuleDB.save(this);
		for ( ProgrammeItem item : programme.listProgrammeItems() ){
			item.sauvegarder();
		}
		return true;
	}

	public int getTotalAffecteParTypeHeureEqTd(String typeHeure){
		int h = 0;
		List<Affectation> affectations = getLstAffectation();
		if ( affectations == null ) return 0;
		for ( Affectation affectation : affectations ){
			if ( typeHeure.equals(affectation.getCategorieHeure().getNom())){
				h+= affectation.getNbEqTd();
			}
		}
		return h;
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

package metier.model;

import metier.IModifiable;
import metier.repo.ProgrammeItemDB;

public class ProgrammeItem implements IModifiable{

    private int id;
    private Integer nbHPn;
    private Integer nbSemaine;
    private Integer nbHeure;
    private CategorieModule categorieModule;
    private CategorieHeure categorieHeure;
    private Module module;

    public ProgrammeItem(CategorieModule categorieModule, CategorieHeure categorieHeure, Module module, Integer nbHPn, Integer nbSemaine, Integer nbHeure) {
        this.nbHPn = nbHPn;
        this.nbHeure = nbHeure;
        this.nbSemaine = nbSemaine;
        this.categorieHeure = categorieHeure;
        this.categorieModule = categorieModule;
        this.module = module;
    }

    public int getId() {
		return this.id;
	}

    public void setModule(Module module) {
        this.module = module;
    }

    public Module getModule() {
        return module;
    }

	public ProgrammeItem setId(int id){
		this.id = id;
		return this;
	}

    public void setNbHPn(int i){
        this.nbHPn = i;
    }

    public void setNbSemaine(Integer nbSemaine) {
        this.nbSemaine = nbSemaine;
    }

    public void setNbHeure(Integer nbHeure) {
        this.nbHeure = nbHeure;
    }
    
    public CategorieModule getCategorieModule() {
        return categorieModule;
    }

    public CategorieHeure getCategorieHeure() {
        return categorieHeure;
    }

    public String getCodeModule() {
        return this.module.getCode();
    }

    public Integer getNbHPn() {
        return nbHPn;
    }

    public Integer getEqTdNbHPn(){
        return (int) (nbHPn * getCategorieHeure().getCoef());
    }

    public Integer getNbSemaine() {
        return nbSemaine;
    }

    public Integer getNbHeure() {
        return nbHeure;
    }

    public Integer getPromoEqTd(){
        int h = this.nbHeure;
        if ( this.nbSemaine > 0 ) h = h * this.nbSemaine;
        h = (int) (getCategorieHeure().getCoef() * h);
        if ( this.getCategorieHeure().getNom().equals("TP") ){
            h = h * module.getSemestre().getNbGroupeTp();
        } 
        if ( this.getCategorieHeure().getNom().equals("TD")){
            h = h * module.getSemestre().getNbGroupeTd();
        }
        if ( this.getCategorieHeure().getNom().equals("HP")){
            h = h * module.getSemestre().getNbGroupeTd();
        }
        return h;
    }

    public boolean sauvegarder(){
        return ProgrammeItemDB.save(this);
    }

    public boolean supprimer(){
        return ProgrammeItemDB.delete(this);
    }

}

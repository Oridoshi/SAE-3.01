package metier.model;

import metier.IModifiable;
import metier.repo.ProgrammeItemDB;

public class ProgrammeItem implements IModifiable{

    private Integer nbHPn;
    private Integer nbSemaine;
    private Integer nbHeure;
    private CategorieModule categorieModule;
    private CategorieHeure categorieHeure;
    private String codeModule;

    public ProgrammeItem(CategorieModule categorieModule, CategorieHeure categorieHeure, String codeModule, Integer nbHPn, Integer nbSemaine, Integer nbHeure) {
        this.nbHPn = nbHPn;
        this.nbHeure = nbHeure;
        this.nbSemaine = nbSemaine;
        this.categorieHeure = categorieHeure;
        this.categorieModule = categorieModule;
        this.codeModule = codeModule;
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

    void setCodeModule(String code){
        this.codeModule = code;
    } 

    public CategorieModule getCategorieModule() {
        return categorieModule;
    }

    public CategorieHeure getCategorieHeure() {
        return categorieHeure;
    }

    public String getCodeModule() {
        return codeModule;
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
        return 0;
        /* int h = this.nbHeure;
        if ( this.nbSemaine > 0 ) h = h * this.nbSemaine;
        h = (int) (getCategorieHeure().getCoef() * h);
        if ( this.getCategorieHeure().getNom().equals("TP") ){
            h = h * module.getSemestre().getNbGroupeTp();
        }
        if ( this.getCategorieHeure().getNom().equals("TD") ){
            h = h * module.getSemestre().getNbGroupeTd();
        }
        return h; */
    }

    public boolean sauvegarder(){
        return ProgrammeItemDB.save(this);
    }

    public boolean supprimer(){
        return ProgrammeItemDB.delete(this);
    }

}

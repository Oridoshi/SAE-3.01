package metier.model;

import java.util.List;

import metier.repo.CategorieModuleDB;
import metier.repo.PatternCategorieModuleItemDB;

public class CategorieModule {

    private String nom;

    public CategorieModule(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<PatternCategorieModuleItem> getCategorieHeures(){
        return PatternCategorieModuleItemDB.listParNomCatModule(this.nom);
    }

    public boolean sauvegarder(){
        return CategorieModuleDB.save(this);
    }

    public boolean supprimer(){
        return CategorieModuleDB.delete(this);
    }
}

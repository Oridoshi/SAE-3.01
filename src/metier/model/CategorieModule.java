package metier.model;

import java.util.List;

import metier.IModifiable;
import metier.repo.CategorieModuleDB;
import metier.repo.PatternCategorieModuleItemDB;

public class CategorieModule implements IModifiable {

    private String nom;

    public CategorieModule(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public boolean setNom(String nom) {
        if ( CategorieModuleDB.getParNom(nom) != null ) return false;
        this.nom = nom;
        return true;
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

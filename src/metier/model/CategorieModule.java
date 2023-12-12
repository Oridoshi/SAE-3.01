package metier.model;

import java.util.List;

public class CategorieModule {

    private String nom;
    private List<CategorieHeure> lstCategorieHeure;

    public CategorieModule(String nom, List<CategorieHeure> lstCategorieHeure) {
        this.nom = nom;
        this.lstCategorieHeure = lstCategorieHeure;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

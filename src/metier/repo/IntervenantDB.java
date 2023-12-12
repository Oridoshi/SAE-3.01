package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Intervenant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IntervenantDB {

    private Connection db = DB.getInstance();

    private CategorieIntervenantDB categorieIntervenantDB;

    private PreparedStatement psGetIntervenants;
    private PreparedStatement psGetIntervenantParId;

    public IntervenantDB(){
        this.categorieIntervenantDB = new CategorieIntervenantDB();
        try{
            this.psGetIntervenants = db.prepareStatement("SELECT * FROM CategorieIntervenant");
            this.psGetIntervenantParId = db.prepareStatement("SELECT * FROM CategorieIntervenant WHERE idIntervenant = ?");
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }

    public List<Intervenant> getIntervenants(){
        DBResult result = DB.query(this.psGetIntervenants);
        List<Intervenant> intervenants = new ArrayList<>();
        for ( Map<String, String> ligne : result.getLignes() ){
            intervenants.add(ligneToIntervenant(ligne));
        }
        return intervenants;
    }

    public Intervenant getIntervenantParId(String id){
        try{
            this.psGetIntervenantParId.setString(1, id);
            DBResult result = DB.query(this.psGetIntervenantParId);
            Map<String, String> ligne = result.getLignes().get(0);
            return ligneToIntervenant(ligne);
        } catch ( Exception e ){
            return null;
        }
    }

    private Intervenant ligneToIntervenant(Map<String, String> ligne){
        return new Intervenant(
                Integer.parseInt(ligne.get("id")),
                categorieIntervenantDB.getCategorieParId(ligne.get("codeCatIntervenant")),
                ligne.get("nom"),
                ligne.get("prenom"),
                Integer.parseInt(ligne.get("hMax"))
        );
    }



}

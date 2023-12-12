package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Affectation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AffectationDB {

    private Connection db = DB.getInstance();

    private IntervenantDB intervenantDB;
    private CategorieHeureDB categorieHeureDB;
    private CategorieModuleDB categorieModuleDB;
    private ModuleDB moduleDB;

    private PreparedStatement psGetAffectations;

    public AffectationDB(){
        this.moduleDB = new ModuleDB();
        this.intervenantDB = new IntervenantDB();
        this.categorieHeureDB = new CategorieHeureDB();
        this.categorieModuleDB = new CategorieModuleDB();
        try{
            db.prepareStatement("SELECT * FROM Affectation");
        } catch ( SQLException e ){
            e.printStackTrace();
        }
    }

    public List<Affectation> getAffectations(){
        DBResult result = DB.query(this.psGetAffectations);
        List<Affectation> affectations = new ArrayList<>();
        for ( Map<String, String> ligne : result.getLignes() ){
            affectations.add(new Affectation(
                    intervenantDB.getIntervenantParId(ligne.get("idIntervenant")),
                    categorieHeureDB.getCategorieHeureParId(ligne.get("idCatHeure")),
                    new Integer(ligne.get("nbGrp")),
                    new Integer(ligne.get("nbSemaine")),
                    new Integer(ligne.get("nbH")),
                    ligne.get("commentaire"),
                    moduleDB.get
                ));
        }
        return null;
    }

        /*
    CREATE TABLE Affectation (
	idAffectation SERIAL PRIMARY KEY,
	idIntervenant INT NOT NULL,
	idCatHeure INT NOT NULL,
	nbH INT default 0,
	nbGrp INT default 0,
	idModule INT NOT NULL,
	commentaire TEXT,
	nbSemaine INT,
	FOREIGN KEY (idIntervenant) REFERENCES Intervenant(idIntervenant),
	FOREIGN KEY (idCatHeure) REFERENCES CategorieHeure(idCatHeure),
	FOREIGN KEY (idModule) REFERENCES Module(idModule)
);
     */


}

package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModuleDB {

    private Connection db = DB.getInstance();

    private SemestreDB semestreDB;
    private CategorieModuleDB categorieModuleDB;

    private PreparedStatement psGetModules;
    private PreparedStatement psGetModuleParCode;

    public ModuleDB(){
        this.semestreDB = new SemestreDB();
        this.categorieModuleDB = new CategorieModuleDB();
        try{
            this.psGetModules = db.prepareStatement("SELECT * FROM Module");
            this.psGetModuleParCode = db.prepareStatement("SELECT * FROM Module WHERE code = ?")
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }

    public List<Module> getModules(){
        DBResult result = DB.query(this.psGetModules);
        List<Module> modules = new ArrayList<>();
        for ( Map<String, String> ligne : result.getLignes() )
            modules.add(ligneToModule(ligne));
        return modules;
    }

    public Module getModuleParCode(String code){
        try{ this.psGetModuleParCode.setString(1, code); }
        catch ( Exception e ) { e.printStackTrace(); }
        DBResult result = DB.query(this.psGetModuleParCode);
        return ligneToModule(result.getLignes().get(0));
    }

    private Module ligneToModule(Map<String, String> ligne){
        return new Module(
                ligne.get("code"),
                semestreDB.getSemestreParId(Integer.parseInt(ligne.get("idSemestre"))),
                categorieModuleDB.getCategorieModuleParId(ligne.get("idCatModule")),
                Boolean.getBoolean(ligne.get("forceValider")),
                ligne.get("libLong"),
                ligne.get("libCourt"),
                null
        );
    }

    /*
    CREATE TABLE Module (
	idModule SERIAL PRIMARY KEY,
	forceValider boolean NOT NULL,
	idSemestre INT NOT NULL,
	idCatModule INT NOT NULL,
	code VARCHAR(30) NOT NULL,
	libLong VARCHAR(255) NOT NULL,
	libCourt VARCHAR(50) NOT NULL,
	FOREIGN KEY (idSemestre) REFERENCES Semestre(idSemestre),
	FOREIGN KEY (idCatModeul) REFERENCES CategorieModule(idCatModule)
       );
     */
}

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

    private PreparedStatement psGetModules;


    public ModuleDB(){
        try{
            this.psGetModules = db.prepareStatement("SELECT * FROM Module");
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }

    public List<Module> getModules(){
        DBResult result = DB.query(this.psGetModules);
        List<Module> modules = new ArrayList<>();
        for ( Map<String, String> ligne : result.getLignes() ){
            modules.add(new Module(
                    Integer.parseInt(ligne.get("idModule")),
                    ligne.get("nomModule"),
                    ligne.get("idCatModule"),
                    Integer.parseInt(ligne.get("nbHeures"))
            ));
        }
        return modules;
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
